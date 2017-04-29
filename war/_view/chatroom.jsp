<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!-- Cited: http://www.mediacollege.com/internet/javascript/form/add-text.html -->

<html>
	<head>
	
<script language="javascript" type="text/javascript">
function addtext() {
	var newtext = document.MyForm.source.value;
	document.MyForm.allmessages.value += newtext+"\n";
	
	document.MyForm.source.value= ""  ;
	
}
</script>
	
		<!-- Fix chatroom title -->
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
	
		
		<form action="${pageContext.servletContext.contextPath}/chatroom" method="post" name="MyForm">
			
			<textarea class="boxSizeAllM" id="top" type="text" name = "allmessages" readonly>${messages}</textarea>
			
			<textarea class="boxSize form-group" id="source" input type="text"  name = "source" value =  "${source}"> </textarea>
			
			
			<!-- May need to change this back to type Submit
			
			Do a for loop that prints things into the messages box. model.messages
			-->
			
			<input class="btn btn-success" type="Submit" name="send" value="Send">
			
			<input class="btn btn-danger" type="Submit" name="logout" value="Logout">
			<input class="btn btn-warning" type="Submit" name="exitP" value="Exit Chat Room Permanently">
			<input class="btn btn-warning" type="Submit" name="Refresh" value="Refresh">
		
	<!--	
		<c:forEach items="${messages}" >
				
			<p> ${messages} </p>		
					
			</c:forEach>
-->	


		</form>
	</body>
	
	
</html>