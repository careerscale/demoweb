<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo Login Page</title>
<%@include file='common.jsp'%>

<script type="text/javascript">

</head>
<body>
	<table width="960px" align="center">
		<tr valign='top'>
			<td width="100px" ><%@include file='sidebar.jsp'%></td>
			<td><table>
					<tr>
						<td><%@include file='header.jsp'%></td>
					</tr>
					<tr>
						<td>
							<div class="headerDiv">Welcome to Demo Login</div>
							<form id="loginForm" action="login" method="post">
								<div class="loginForm" align="center">

									<table>
										<tr>
											<td colspan="2">
												<!--  Let us show errors here -->
												<div class="errorMessage" align="center">
												<%
												 if(request.getAttribute("error") != null){
													 out.print(request.getAttribute("error"));
												 }
												%>													
													<br />
												</div>
											</td>
										<tr>
											<td>User name</td>
											<td><input type="text" id="userId" name="userId" /></td>
										</tr>

										<tr>
											<td>Password</td>

											<td><input type="password" name="password" /></td>
										</tr>

										<tr>
											<td colspan="2" abbr="right"><input type="submit"
												name="login" value="Login" /></td>
										</tr>
									</table>
								</div>
							</form>
						</td>
					</tr>
					<tr>
					<td>
					<%@include file='howto.html'%>
					</td>
					</tr>
					<tr>
						<td><%@include file='footer.jsp'%></td>
					</tr>
				</table></td>
		</tr>
	</table>


</body>
</html>