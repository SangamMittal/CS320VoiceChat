<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Chatroom: </title>
		<style type="text/css">
		.error {
			color: red;
		}
		.chatbox {
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
	
		<form action="${pageContext.servletContext.contextPath}/chatroom" method="post">
			
			<textarea class = "chatbox" readonly> This is a Test.
			</textarea>
			
			<table>

				<tr>
					<td><input type="text" name="second" size="50" value="${model.second}" /></td>
				</tr>
				
			</table>
			<input type="Submit" name="send" value="Send">
			<input type="Submit" name="logout" value="Logout">
			
		</form>
	</body>
</html>