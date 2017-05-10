<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
<title> TypeInRoomPW </title>
	<head>
	
	</head>
	
	<body>
	
	<!--Where should the information get redirected to? Chatroom currently-->
	<form action = "${pageContext.servletContext.contextPath}/typeInRoomPW" method="post">
	
	  Chatroom Password: <br>
	  <input type="text" name="password" name="box">
	<input type = "submit" value = "Enter" name="submit">
	
	</form>
	
	</body>
	</html>
	