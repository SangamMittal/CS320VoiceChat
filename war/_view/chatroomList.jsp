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
				width: 10%;
				margin: auto;
				text-align: center;
				vertical-align: middle;
				
			}
			
			.wholePage{
				background-color: darkslategrey;
			}
			
			td.nameCol {
				text-align: left;
				color: blue;
				font-weight: bold;
				max-width: 400px;
				padding-left: 20px;
			}			
		
		
		</style>
	
	</head>

	<body class = "wholePage">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>
	
		<form action="${pageContext.servletContext.contextPath}/chatroomList" method="post">

			    <c:forEach items="${authors}" var="author">
			        <tr class="authorRow">
			            <td class="nameCol">${author.lastname}</td>
			            <td class="nameCol">${author.firstname}</td>			            
			        </tr>
			    </c:forEach>
			
			<c:forEach items="${allChatrooms}" var = "room">
					<tr>
						<td class="nameCol">${room.chatroomName}</td> 
					</tr>
			</c:forEach>
			<div class="list-group row">
				<a href = http://localhost:8081/groupProject/chatroom class="list-group-item col-xs-3 form-horizontal"> Chatroom 2 </a>


			
			</div>
			
			<div class = "row> 	
				<div class="col-md-4 col-md-offset-10">
					<input class="btn btn-info" type="Submit" name="createChatroom" value="Create New Chatroom">
				</div>
			</div/>
			
			<div class = "row>
				<div class="col-md-4 col-md-offset-10">
					<input class="btn btn-success" type="Submit" name="refresh" value="Refresh">
				</div>
			</div/>
			
			<div class = "row">
				<div class="col-md-4 col-md-offset-10">
					<input class="btn btn-danger" type="Submit" name="logout" value="Logout">
				</div>
			</div/>
			
  				
  		
		
		
		</form>

		
	</body>
</html>