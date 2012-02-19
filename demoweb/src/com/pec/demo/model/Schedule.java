package com.pec.demo.model;

public class Schedule {

	private String minutes;
	private String hours;
	private String days;
	private String months;

	public Schedule(String minutes, String hours, String days, String months) {
		this.minutes = minutes;
		this.hours = hours;
		this.days = days;
		this.months = months;

	}

	public String getMinutes() {
		return minutes;
	}

	public void setMinutes(String minutes) {
		this.minutes = minutes;
	}

	public String getHours() {
		return hours;
	}

	public void setHours(String hours) {
		this.hours = hours;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getMonths() {
		return months;
	}

	public void setMonths(String months) {
		this.months = months;
	}

}
