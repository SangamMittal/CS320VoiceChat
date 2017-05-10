<!DOCTYPE html>
<!-- -->
<html>
	<head>
		<title>Login/Sign Up</title>
		<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" 
		integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
		
		<style>
			.form-control{
				width: 25%;
				margin: auto;
				text-align: center;
				vertical-align: middle;
				
			}
			
			.center{
				margin: auto;
				text-align: center;
				background-color: darkslategrey;
				vertical-align: middle;
			}
			.label{
				text-color: white;
				font-size: 20px;
			}
			.error {
				color: red;
			}
			
		</style>
	
	
	</head>

	<body class = "center">
		<c:if test="${! empty errorMessage}">
			<div class="error">${errorMessage}</div>
		</c:if>	
	
		<form  action="${pageContext.servletContext.contextPath}/login" method="post" >
	
			  <div class="form-group">
			    <label class = "form-group label" >Username</label>
			    <input type="username" name = "username" class="form-control"
			    			placeholder="Username - 32 max characters" value="${model.name}" >
			  </div>
			  <div class="form-group">
			    <label class = "form-group label">Password</label>
			    <input type="password" name = "password" class="form-control" 
			    			placeholder="Password - 32 max characters" value="${model.password}">
			  </div>
			  <div class="form-group">
			    <label class = "form-group label">Confirm Password</label>
			    <input type="password" name = "password2" class="form-control" 
			    			placeholder="Confirm Password for Signup" value="${model.password2}">
			  </div>
				
			<input class="btn btn-primary" type="Submit" name="login" value="Login">
			<input class="btn btn-success" type="Submit" name="signUp" value="Sign Up">

		</form>
	</body>
</html>
