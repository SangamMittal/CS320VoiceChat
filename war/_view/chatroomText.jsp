<!DOCTYPE html>

<html>
	<head>
	</head>
	
<body class = "all">
<form action="${pageContext.servletContext.contextPath}/chatroom" method="post" name="MyForm">
			
<textarea class="boxSizeAllM" id="top" type="text" name = "allmessages" readonly><c:forEach items="${messages}" var="post">${post}</c:forEach></textarea>

		</form>
	</body>
</html>