<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Chatrooms List</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">			
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/chatroomList" method="post">
			
		<input type="Submit" name="logout" value="Logout">
		<input type="Submit" name="createChatroom" value="Create New Chatroom">
		
		<div class="list-group">
			<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 1 </a>
			<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 2 </a>
			<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 3 </a>
		</div>

		
		
		</form>

		
	</body>
</html>