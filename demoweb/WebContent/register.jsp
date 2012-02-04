<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Demo Registration Page</title>
<%@include file='common.jsp'%>

<script type="text/javascript">
	$(document).ready(function() {
		$("#dob").datepicker({
			showOn : "button",
			buttonImage : "images/ico_calendar.gif",
			buttonImageOnly : true,
			appendText : ' DD/MM/YYYY ',
			buttonText : 'Date selector 1',
			align : "middle",
			alt : 'Date Selector 1',
			cursor : 'pointer',
			dateFormat : 'dd/mm/yy',
			beforeShow : function(input, inst) {
				$('ui-widget-header').css({
					"color" : 'red',
					"width" : "100%"
				});
			}

		});

	});

	$()
			.ready(
					function() {
						// validate signup form on keyup and submit
						$("#signupForm")
								.validate(
										{
											rules : {
												firstname : "required",
												lastname : "required",
												username : {
													required : true,
													minlength : 2
												},
												password : {
													required : true,
													minlength : 5
												},
												confirm_password : {
													required : true,
													minlength : 5,
													equalTo : "#password"
												},
												email : {
													required : true,
													email : true
												},
												agree : "required"
											},

											
											dob : {
												required : true,
												minlength : 10,
												
											},

											
											messages : {
												firstname : "Please enter your firstname",
												lastname : "Please enter your lastname",
												username : {
													required : "Please enter a username",
													minlength : "Your username must consist of at least 2 characters"
												},
												password : {
													required : "Please provide a password",
													minlength : "Your password must be at least 5 characters long"
												},
												confirm_password : {
													required : "Please provide a password",
													minlength : "Your password must be at least 5 characters long",
													equalTo : "Please enter the same password as above"
												},
												dob : {
													required : "Please provide your birth date",
													minlength : "Your birth date must be 10 characters and in dd/mm/yyyy format"													
												},
												email : "Please enter a valid email address",
												agree : "Please accept our policy"
											}
										});

						// propose username by combining first- and lastname
						$("#username").focus(function() {
							var firstname = $("#firstname").val();
							var lastname = $("#lastname").val();
							if (firstname && lastname && !this.value) {
								this.value = firstname + "." + lastname;
							}
						});
					});
</script>
</head>
<body>
	<table width="960px" align="center">

		<tr>
			<td><%@include file='header.jsp'%></td>
		</tr>
		<tr>
			<td>

				<form class="cmxform" id="signupForm" method="post"
					action="register">
					<fieldset>
						<legend>Welcome to Demo registration form</legend>

						<p>
							<label for="firstname">Firstname</label> <input id="firstname"
								name="firstname" />
						</p>
						<p>
							<label for="lastname">Lastname</label> <input id="lastname"
								name="lastname" />
						</p>

						<p>
							<label for="username">Username</label> <input id="username"
								name="username" />
						</p>
						<p>
							<label for="password">Password</label> <input id="password"
								name="password" type="password" />
						</p>

						<p>
							<label for="confirm_password">Confirm password</label> <input
								id="confirm_password" name="confirm_password" type="password" />
						</p>
						<p>
							<label for="email">Email</label> <input id="email" name="email"
								type="email" />
						</p>

						<p>
							<label for="dob">Birth Date</label> <input id="dob" name="dob"
								type="date" maxlength="10" />
						</p>

						<p>
							<label for="agree">Please agree to our policy</label> <input
								type="checkbox" class="checkbox" id="agree" name="agree" />
						</p>
						<p>
							<input class="submit" type="submit" value="Submit" />
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
	</table>


</body>
</html>