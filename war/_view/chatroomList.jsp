<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Chatrooms List</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">			
	
		<style>
			.listBox{
				border-style: solid;
   				border-width: medium;
			}
			
			.form-control{
				width: 75%;
				margin: auto;
				text-align: center;
				vertical-align: middle;
				
			}
			
			.wholePage{
				background-color: darkslategrey;
			}
			
		
		
		</style>
	
	</head>

	<body class = "wholePage">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/chatroomList" method="post">

			
			<div class="list-group">
				<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 1 </a>
				<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 2 </a>
				<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 3 </a>
				<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 4 </a>
				<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 5 </a>
				<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 6 </a>

			
			</div>
			<input class="form-control btn btn-info" type="Submit" name="createChatroom" value="Create New Chatroom">
			<input class="form-control btn btn-success" type="Submit" name="refresh" value="Refresh">
			<input class="form-control btn btn-danger" type="Submit" name="logout" value="Logout">
		
		
		</form>

		
	</body>
</html>