<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Chatroom: </title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
			integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<style>
			.form-control{
				width: 50%;
				height: 500px;
				row: 100;
			}
			
			.boxSize{
				width: 50%;
				max-width: 50%;
				height: 100px;
				max-height: 100px;
				background-color: darkslategrey;
				color: white;
				border-style: solid;
    			border-width: 5px;	
    			line-height: 0.8;
    			font-size: 20px;
    			
    						
			}
			.boxSizeAllM{
				width: 50%;
				max-width: 50%;
				height: 500px;
				max-height: 500px;
				background-color: darkslategrey;
				color: white;
				border-style: solid;
    			border-width: 5px;		
    			line-height: 0.8;
    			font-size: 20px;
    			
    					
			}
			.all{
				background-color: darkslategrey;
			}
			
			
		</style>
	</head>

	<body class = "all">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if >
	
		
		<form action="${pageContext.servletContext.contextPath}/chatroom" method="post">
			
			<textarea class="boxSizeAllM"  type="text" name = "allmessages" readonly>${send}</textarea>
			
			<textarea class="boxSize form-group"  type="text" name = "usermessage" ></textarea>
			
			<input class="btn btn-success" type="Submit" name="send" value="Send">
			<input class="btn btn-danger" type="Submit" name="logout" value="Logout">
			<input class="btn btn-warning" type="Submit" name="exitP" value="Exit Chat Room Permanently">
			
			
			
			
		</form>
	</body>
</html>