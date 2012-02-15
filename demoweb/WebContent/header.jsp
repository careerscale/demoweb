<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <table>
    <tr>
    <td>
   <a href="index.jsp"> <img alt="Demo Web Logo" src="images/logo.png" /> </a>
    </td>
    </tr>
    <tr>
    <td align="right">
    <%
    String username =(String)session.getAttribute("username");
    if(username ==null  ||  username.length() ==0){
    	out.print("<a href='login'> Login </a>");
    }
    else{
    	out.println("Welcome " +username);
    	out.println("  "+ "<a href='logout'> Logout </a>");
    }
    
    %>
    
    </td>
    </tr>
    </table>