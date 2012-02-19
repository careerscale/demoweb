<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Create mail scheduler</title>
</head>
<body>


<form action="scheduler" name="mailConfigurationForm" id ="mailConfigurationForm"  method ="post">

<table>

<tr>
<td>
To Email Id
</td>
<td>
<input type="text" name="toEmail" />
</td>
</tr>
<tr>
<td>
Subject
</td>

<td>
<input type="text" name="subject" width="250" />

</td>
</tr>

<tr>
<td>
Message
</td>

<td>
<input type="text" name="message" />
</td>
</tr>

<tr>
<td colspan="2" align="right">
<input type="submit" name="submt" />
</td>
</tr>
</table>

</form>



</body>
</html>