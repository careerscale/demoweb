package com.pec.demo.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.log4j.chainsaw.Main;

import com.pec.demo.exceptions.ApplicationException;
import com.pec.demo.model.Mail;
import com.pec.demo.model.Schedule;
import com.pec.log.LogFactory;

public class SchedulerDAO {
	private static Logger logger = LogFactory.getLogger();

	public List<Integer> getCurrentSchedules() {

		List<Integer> mailIdsList = new ArrayList<Integer>();

		Connection con = null;
		try {
			con = ConnectionManager.getDbConnection();
			Calendar calendar = Calendar.getInstance();
			Statement stmt = con.createStatement();
			String query = "select mail_id from mail_schedule where"
					+ " minute ='*' or  minute = '"
					+ calendar.get(Calendar.MINUTE)
					+ "' and  hour ='*' or hour ='"
					+ calendar.get(Calendar.HOUR_OF_DAY) + "' "
					+ "and  day ='*' or  day = '"
					+ calendar.get(Calendar.DAY_OF_MONTH)
					+ "' and  month ='*' or month ='"
					+ calendar.get(Calendar.MONTH) + "'"
					+ "and  year ='*' or  year ='"
					+ calendar.get(Calendar.YEAR) + "'";
			System.out.println(query);
			ResultSet resultSet = stmt.executeQuery(query);

			while (resultSet.next()) {
				mailIdsList.add(resultSet.getInt(1));
			}

		} catch (Exception e) {
			logger.error("Unable to get scheduled mails", e);

		}

		ConnectionManager.close(con);

		return mailIdsList;

	}

	public Mail getEmailById(Integer id) {
		Mail mail = null;

		Connection con = null;
		try {
			con = ConnectionManager.getDbConnection();
			Statement stmt = con.createStatement();
			ResultSet resultSet = stmt
					.executeQuery("select to_email, subject, mail_body from mail where id= "
							+ id);
			while (resultSet.next()) {
				mail = new Mail(resultSet.getString(1), resultSet.getString(2),
						resultSet.getString(3));
			}
		} catch (Exception e) {
			logger.error("Unable to fetch the mail details ", e);
		}

		return mail;

	}

	public static void main(String[] args) {

		SchedulerDAO dao = new SchedulerDAO();
		dao.getCurrentSchedules();

	}

	public void createMail(Mail mail) throws ApplicationException {

		try {
			Connection con = ConnectionManager.getDbConnection();
			// Statement stmt = con.createStatement();
			// stmt.execute("insert into  mail(to_email, subject, mail_body) values (' "
			// + mail.getToEmail() + "'  ,  '" + mail.getSubject() + "', ' " +
			// mail.getMessage() + "'" );

			PreparedStatement pStmt = con
					.prepareStatement(
							"insert into mail(to_email, subject, mail_body) values (?,?,?)",
							PreparedStatement.RETURN_GENERATED_KEYS);
			pStmt.setString(1, mail.getToEmail());
			pStmt.setString(2, mail.getSubject());
			pStmt.setString(3, mail.getMessage());

			pStmt.executeUpdate();

			// Let us retrieve the auto generated key value.
			ResultSet rs = pStmt.getGeneratedKeys();
			int mailId = rs.next() ? rs.getInt(1) : -1;
			rs.close();
			
			if(mailId >=1 && mail.getSchedule()!= null){
				Schedule schedule = mail.getSchedule();
				String insertScheduleQuery ="insert into mail_schedule(mail_id, minute, hour, day, month, year) values (?,?,?,?,?,?)";
				PreparedStatement pStmtSchedule = con.prepareStatement(insertScheduleQuery);
				pStmtSchedule.setInt(1, mailId);
				pStmtSchedule.setString(2, schedule.getMinutes());
				pStmtSchedule.setString(3,schedule.getHours());
				pStmtSchedule.setString(4,schedule.getDays());
				pStmtSchedule.setString(5,schedule.getMonths());
				pStmtSchedule.setString(6,"*");
				pStmtSchedule.execute();
				
			}
		} catch (Exception e) {
			logger.error("Unable to create mail schedule", e);
			throw new ApplicationException(
					"Unable to insert mail content into mail table)");
		}

	}

}
