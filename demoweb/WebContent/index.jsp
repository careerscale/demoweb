<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Welcome to DemoWeb</title>
<%@include file='common.jsp'%>

</head>
<body>
	<table width="960px" align="center">
		<tr valign='top'>
			<td width="100px"><%@include file='sidebar.jsp'%></td>
			<td><table>
					<tr>
						<td><%@include file='header.jsp'%></td>
					</tr>
					<tr>
						<td>
							<div class="headerDiv">Welcome to DemoWeb</div>
							<form class="cmxform" id="loginForm" method="post" action="login">
								<fieldset>
									<legend>Welcome to DemoWeb Home Page</legend>

									<p>
										Existing users? <a href="login">Click Here</a> to login.
									</p>
									<p>
										Do not have account? <a href="register">Click Here</a> to
										create one.
									</p>
								</fieldset>
							</form>

						</td>
					</tr>
					<tr>
						<td><%@include file='howto.html'%></td>
					</tr>
					<tr>
						<td><%@include file='footer.jsp'%></td>
					</tr>
				</table></td>
		</tr>
	</table>


</body>
</html>