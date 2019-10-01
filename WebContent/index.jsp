

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Rest APIs</title>
</head>
<body>
	User-ID : 
	<input type="text" id="username_github">
	<button id='btn_1' onclick='clickedBtn(0)'> Get GitHub Data </button>
	
	<br><br>

	Email-ID : 
	<input type="text" id="emailid_github">
	<button id='btn_1' onclick='clickedBtn(1)'> Get GitHub Data </button>
	
	<br>
	<hr>
	
	Username : 
	<input type="text" id="username_hackerrank">
	<button id='btn_2' onclick='clickedBtn(2)'> Get HackerRank Data </button>
	
	<h3>GitHub</h3>: <a href="/rest/srv/GitHub/username/vipulchasta">/rest/srv/GitHub/username/{userName}</a>
	<h3>GitHub</h3>: <a href="/rest/srv/GitHub/email/chasta.vipul@gmail.com">/rest/srv/GitHub/email/{emailId}</a>
	<br>
	*GitHub: Example e-mail => chasta.vipul@gmail.com</a>
        <br>To search user on the basis of email user must have set his email to public, otherwise we can use username based search of user on GitHub
        <br> Analysis will take time and depends upon the no. of repositories user have and also on no. of commits on each repository.
	<br>
	<h3>HackerRank</h3>: <a href="/rest/srv/HackerRank/username/vipulchasta">/rest/srv/HackerRank/username/{userName}</a>
	<br>
	*Hacker Rank: Currently following usernames are working(yet to integrate the python script to obtain data)
	<br>
	vipulchasta, ishan_pahare, raghavendramj123, shubham18rastogi, utkarsh_mathur_1 
        <br>
        <br>
        <h5 style="color:red;">Open This Web Page in FireFox To View API Response in Pretty Format</h5>
<script data-cfasync="false" src="/cdn-cgi/scripts/5c5dd728/cloudflare-static/email-decode.min.js"></script></body>
	<script>
	function clickedBtn(btnId) {

		var username_github = document.getElementById("username_github").value;
		var emailid_github = document.getElementById("emailid_github").value;
		var username_hackerrank = document.getElementById("username_hackerrank").value;
		if(btnId==0) {
			window.location.href = "/rest/srv/GitHub/username/" + username_github;
		} else if(btnId==1) {
			window.location.href = "/rest/srv/GitHub/email/" + emailid_github;
		} else if(btnId==2) {
			window.location.href = "/rest/srv/HackerRank/username/" + username_hackerrank;
		}
	}
	</script>
</html>
