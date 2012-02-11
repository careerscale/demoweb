<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>DemoWeb - Forgot Password Page</title>
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
							<div class="headerDiv">Welcome to Demo Login</div>
							 	<!--  Let us show errors here -->
									<div class="errorMessage" align="center">
										<%
											if (request.getAttribute("error") != null) {
												out.print(request.getAttribute("error"));
											}
											else{
												out.println("Your password reset details have been sent to your email");
											}
										%>
									</div>									
								
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