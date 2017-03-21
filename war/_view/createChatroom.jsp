<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<html>
	<head>
		<title>Create Chatroom</title>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/createChatroom" method="post">
			<table>
				<tr>
					<td class="label">Chatroom Name:</td>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
				<tr>
					<td class="label">Chatroom Password:</td>
					<td><input type="password" name="second" size="12" value="${model.second}" /></td>
				</tr>

			</table>
			<input type="Submit" name="create" value="Create">

		</form>
	</body>
</html>