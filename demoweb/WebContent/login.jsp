<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo Login Page</title>
<%@include file='common.jsp'%>

<script type="text/javascript">
	$()
			.ready(
					function() {
						// validate signup form on keyup and submit
						$("#loginForm")
								.validate(
										{
											rules : {
												username : {
													required : true,
													minlength : 2
												},
												password : {
													required : true,
													minlength : 5
												},
												messages : {
													username : {
														required : "Please enter a username",
														minlength : "Your username must consist of at least 2 characters"
													},
													password : {
														required : "Please provide a password",
														minlength : "Your password must be at least 5 characters long"
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
				<div class="headerDiv">Welcome to Demo Login</div>
				<form class="cmxform" id="loginForm" method="post" action="login">
					<fieldset>
						<legend>Welcome to Demo login form</legend>

						<!--  Let us show errors here -->
						<div class="errorMessage" align="center">
							<%
								if (request.getAttribute("error") != null) {
									out.print(request.getAttribute("error"));
								}
							%>
						</div>
						<p>
							<label for="username">Username</label> <input id="username"
								name="username"
								value=<%=request.getParameter("username") == null ? "''" : "'"
					+ request.getParameter("username") + "'"%> />
						</p>
						<p>
							<label for="password">Password</label> <input id="password"
								name="password" type="password" />
						</p>
						<p>
							<input class="submit" type="submit" value="Submit" name="submit" />
							&nbsp; &nbsp;&nbsp; <input class="cancel" type="submit"
								name="forgot" value="Forgot Password" />
						</p>
						<p>
							Do not have account? <a href="register">Click Here</a> to create
							one.
						</p>
					</fieldset>
				</form>

			</td>
		</tr>
		<tr>
			<td><%@include file='footer.jsp'%></td>
	</table>


</body>
</html>