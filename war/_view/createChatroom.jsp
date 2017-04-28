<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create Chatroom</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<style>
			.form-control{
			width: 25%;
		
			}
		</style>
	</head>

	<body>
	
			<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>	
	
		<form action="${pageContext.servletContext.contextPath}/createChatroom" method="post">
		
					
		
			  <div class="form-group">
			    <label>Chat Room Name</label>
			    <input type="username" name = "roomName" class="form-control"
			    			placeholder="Name - 32 max characters" value="${model.first}" >
			  </div>
			  <div class="form-group">
			    <label>Password</label>
			    <input type="password" name = "roomPassword" class="form-control" 
			    			placeholder="Password - 32 max characters" value="${model.second}">
			  </div>
				
			<input class="btn btn-warning" type="Submit" name="create" value="Create">
			
			

		</form>
	</body>
</html>