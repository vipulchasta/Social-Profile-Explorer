<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rest APIs</title>
</head>
<body>
	Email-ID : 
	<input type="text" id="email_id">
	<button id='btn_1' onclick='clickedBtn(1)'> Get GitHub Data </button>
	
	<br>
	<hr>
	
	Username : 
	<input type="text" id="username">
	<button id='btn_2' onclick='clickedBtn(2)'> Get HackerRank Data </button>
	
	<h3>GitHub</h3>: <a href="http://localhost:8080/rest/srv/GitHub/email/">http://localhost:8080/rest/srv/GitHub/</a>
	<h3>HackerRank</h3>: <a href="http://localhost:8080/rest/srv/HackerRank/username/">http://localhost:8080/rest/srv/HackerRank/</a>
	<br>
	*Hacker Rank: Currently following usernames are working(yet to integrate the python script to obtain data)
	<br>
	vipulchasta, ishan_pahare, raghavendramj123, shubham18rastogi, utkarsh_mathur_1  
</body>
	<script>
	function clickedBtn(btnId) {

		var emailId = document.getElementById("email_id").value;
		var username = document.getElementById("username").value;
		if(btnId==1) {
			window.location.href = "http://localhost:8080/rest/srv/GitHub/email/" + emailId;
		} else if(btnId==2) {
			window.location.href = "http://localhost:8080/rest/srv/HackerRank/username/" + username;
		}
	}
	</script>
</html>