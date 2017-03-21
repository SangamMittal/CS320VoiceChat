<!DOCTYPE html>

<html>
	<head>
		<title>Login/Sign Up</title>
		<style type="text/css">
		.error {
			color: red;
		}
		
		td.label {
			text-align: right;
		}
		</style>
	</head>

	<body>
		<form action="${pageContext.servletContext.contextPath}/login" method="post">
		
			<table>
				<tr>
					<td class="label">Username:</td>
					<td><input type="text" name="first" size="12" value="${model.first}" /></td>
				</tr>
				<tr>
					<td class="label">Password:</td>
					<td><input type="password" name="first" size="12" value="${model.first}" /></td>
				</tr>

			</table>
			<input type="Submit" name="login" value="Login">
			<input type="Submit" name="signUp" value="Sign Up">
			
		</form>
	</body>
</html>
