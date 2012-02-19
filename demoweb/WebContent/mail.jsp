<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create mail scheduler</title>
<%@include file="common.jsp"%>


<script type="text/javascript">
	$()
			.ready(
					function() {
						// validate signup form on keyup and submit
						$("#mailConfigurationForm")
								.validate(
										{
											rules : {
												toEmail : {
													required : true,
													minlength : 10
												},
												subject : {
													required : true,
													minlength : 2
												},
												message : {
													required : true,
													minlength : 50
												},
												messages : {
													toEmail : {
														required : "Please enter a  valid email Id",
														minlength : "Your email id must consist of at least 10 characters"
													},
													subject : {
														required : "Please provide a subject",
														minlength : "Your subject must be at least 2  characters long"
													},
													message : {
														required : "Please provide valid message",
														minlength : "Your message must be at least 50  characters long"
													}
												}
											}
										});
					});
</script>

</head>
<body>

	<table width="960px" align="center">
		<tr valign='top'>
			<td><%@include file='header.jsp'%></td>
		</tr>


		<tr>
			<td>

				<form action="scheduler" class="cmxform"
					name="mailConfigurationForm" id="mailConfigurationForm"
					method="post">
					<fieldset>
						<legend>Configure scheduled emails</legend>

						<!--  Let us show errors here -->
						<div class="errorMessage" align="center">
							<%
								if (request.getAttribute("error") != null) {
									out.print(request.getAttribute("error"));
								}
							%>
						</div>
						<p>
							<label for="toEmail"> To Email</label> <input type="text"
								id="toEmail" name="toEmail" />
						</p>
						<p>
							<label for="subject">Subject</label> <input type="text"
								id="subject" name="subject" />
						</p>

						<p>
							<label for="message"> Message</label>
							<textarea rows="5" cols="50" id="message" name="message"></textarea>
						</p>
						<p>
							Schedule: <br />
							</p>
						<p>
							<label for="minutes">Select Minute value</label> <select
								name="minutes">
								<c:forEach items="${minutes}" var="option">
									<option value="${option.key}">${option.value}</option>
								</c:forEach>
							</select>

						</p>

						<p>
							<label for="hours">Select hour of the day</label> <select
								name="hours">
								<c:forEach items="${hours}" var="option">
									<option value="${option.key}">${option.value}</option>
								</c:forEach>
							</select>

						</p>

						<p>
							<label for="days">Select Day in the month</label> <select
								name="days">
								<c:forEach items="${days}" var="option">
									<option value="${option.key}">${option.value}</option>
								</c:forEach>
							</select>
						</p>

						<p>
							<label for="months">Select month of the year</label> <select
								name="months">
								<c:forEach items="${months}" var="option">
									<option value="${option.key}">${option.value}</option>
								</c:forEach>
							</select>
						</p>
						<p>
							<input class="submit" type="submit" value="Submit" name="submit" />
						</p>

						
					</fieldset>
				</form>

			</td>
		</tr>
		<tr>
			<td><%@include file="footer.jsp"%></td>
		</tr>
	</table>
</body>
</html>