<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Chatrooms List</title>
		<style type="text/css">
	
	#chatbox {
	text-align: left;
	margin: 0 auto;
	margin-bottom: 25px;
	padding: 10px;
	background: #fff;
	height: 270px;
	width: 430px;
	border: 1px solid #ACD8F0;
	overflow: auto;
}
					
					
		</style>
	</head>

	<body>
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/chatroomList" method="post">
			
		<input type="Submit" name="logout" value="Logout">
		<input type="Submit" name="createChatroom" value="Create New Chatroom">
		
		<p>
		<a href = http://localhost:8081/groupProject/chatroom > Chatroom 1 </a>
		<p>
		<a href = http://localhost:8081/groupProject/chatroom > Chatroom 2 </a>
		<p>
		<a href = http://localhost:8081/groupProject/chatroom > Chatroom 3 </a>
		

		
		
		</form>

		
	</body>
</html>