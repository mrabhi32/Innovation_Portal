<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Innovation Portal-BBulk Upload Errors.</title>
</head>


<body class="skin-blue">
<% String errorMessage = (String)session.getAttribute("ErrorMessage"); 

   String[] errorMessages = errorMessage.split(";");
   %>
   <div id="tableContainer-1">
  <div id="tableContainer-2">
  
  <p align="center"><u><b>Following are the Errors Reported in your File</u></b></p>
    <table id="errorTable" border=1 align="center">
    <th>S.No</th>
    <th>Error Message</th>
    <th>Line Number</th>
    	
   <%
   for(int i=0;i<errorMessages.length-1;i++)
   {
	  
	 
	  %>
	
	<tr>
		<td><%=i+1 %></td>
		<td><%=errorMessages[i].substring(0,errorMessages[i].indexOf(". E")+1) %></td>
		<td><%=errorMessages[i].substring(errorMessages[i].indexOf(". E")+1,errorMessages[i].length()) %></td>
    </tr>	
	
	  
	  
<% 	  
	 
   
   }
%>   
    </table>
    <p align="center"><u>Close the Current Tab to return back to the Portal.</u></p>
  </div>
</div>
     
</body>
</html>