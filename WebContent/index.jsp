<!DOCTYPE html>
<html>
<head>
<title>Social Profile Explorer</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>

<link rel="apple-touch-icon" sizes="57x57"
	href="img/favicon/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60"
	href="img/favicon/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72"
	href="img/favicon/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76"
	href="img/favicon/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114"
	href="img/favicon/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120"
	href="img/favicon/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144"
	href="img/favicon/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152"
	href="img/favicon/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180"
	href="img/favicon/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192"
	href="img/favicon/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32"
	href="img/favicon/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96"
	href="img/favicon/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16"
	href="img/favicon/favicon-16x16.png">
<link rel="manifest" href="img/favicon/manifest.json">
<meta name="msapplication-TileColor" content="#ffffff">
<meta name="msapplication-TileImage"
	content="img/favicon/ms-icon-144x144.png">
<meta name="theme-color" content="#ffffff">
</head>

<body background="img/background/abyeti.jpg">

	<!-- HackerRank Modal -->
	<div class="modal fade" id="img_hackerrank_modal" role="dialog">
		<div class="modal-dialog">
			<form id="form_hackerrank" onsubmit="onFormSubmit('hackerrank')"
				onchange="onFormUpdate('hackerrank')" action="javascript:void(0)">
				<div class="modal-content">
					<div class="modal-header">
						<button type="button" class="close" data-dismiss="modal">&times;</button>
						<h4 class="modal-title">Hacker Rank API Explorer</h4>
					</div>
					<div class="modal-body">
						<div class="form-group">
							<label for="api_data">API Data</label> <input type="text"
								class="form-control" placeholder="API Data"
								oninput="onFormUpdate('hackerrank')" name="api_data">
						</div>

						<div class="form-group">
							<label class="radio-inline"><input type="radio"
								name="api_type" value='profile' checked>Profile</label> <label
								class="radio-inline"><input type="radio" name="api_type"
								value='badges'>Badges</label> <label class="radio-inline"><input
								type="radio" name="api_type" value="submissionHistory">Submission
								History</label>
						</div>
						<div class="form-group">
							<label for="api_output">API</label> <input type="text"
								class="form-control" name="api_output" readonly
								value="/rest/srv/HackerRank/{apiType}/{apiData}">
						</div>
						<hr>
							<h5>Sample HackerRank Usernames: </h5>
						<p>
							vipulchasta, ishan_pahare, raghavendramj123, shubham18rastogi, utkarsh_mathur_1 
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success">Submit</button>
					</div>
				</div>
			</form>
		</div>
	</div>

	<div class="modal fade" id="img_github_modal" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">GitHub API Explorer</h4>
				</div>
				<form id="form_github" onsubmit="onFormSubmit('github')"
					action="javascript:void(0);" onchange="onFormUpdate('github')">
					<div class="modal-body">
						<div class="form-group">
							<label for="api_data">API Data</label> <input type="text"
								class="form-control" name="api_data" placeholder="API Data"
								oninput="onFormUpdate('github')">
						</div>

						<div class="form-group">
							<label class="radio-inline"><input type="radio"
								name="api_type" value="username" checked>Username</label> <label
								class="radio-inline"><input type="radio" name="api_type"
								value="email">Email-ID</label>
						</div>

						<div class="form-group">
							<label for="api_output">API</label> <input type="text"
								class="form-control" name="api_output" readonly
								value="rest/srv/GitHub/{apiType}/{apiData}">
						</div>
						<hr>
							<h5>Sample GitHub Username: </h5>
							vipulchasta
							<h5>Sample GitHub emailID: </h5>
							chasta.vipul@gmail.com

						<p>
							<br><span style="color:red;"> -> To search user on the basis of email, user must have set his email to public.
							<br> -> GitHub Analysis will take time and depends upon the no. of repositories user have and also on no. of commits on each repository.</span>
						</p>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-info" data-dismiss="modal">Close</button>
						<button type="submit" class="btn btn-success">Submit</button>
					</div>
				</form>
			</div>

		</div>
	</div>

	<!-- API Respionse Modal -->
	<div class="modal fade" id="modal_api_response" role="dialog">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<h4 class="modal-title">API Explorer</h4>
				</div>
				<div class="modal-body" align="center" style="text-align: center;">
					<img id='api_response_img_load' src="https://shop.bulltronics.com/loading.gif" alt='Loading Image' />
					<p style='width:100%; text-align: left;'>
						<br><span style="color:red;"> -> Please wait... It may take a while.</span>
					</p>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-info" disabled="disabled">Close</button>
				</div>
			</div>
		</div>
	</div>

	<div class="container">
		<h1 style="color:white;">I Can Look Into Following Profiles:</h1>
		<div class="row" style="min-height: 50px;"></div>
		<div class="row">
			<div class="col-md-3"></div>
			<div class="col-md-3" align="center">
				<div style="padding: 10px;">
					<img id="img_hackerrank" src="img/hackerrank.png" alt="Avatar"
						class="img-thumbnail"
						onmouseover="onHoverInImage('img_hackerrank')"
						onmouseout="onHoverOutImage('img_hackerrank')"
						onclick="onClickImage('img_hackerrank')">
				</div>
			</div>
			<div class="col-md-3" align="center">
				<div style="padding: 10px;">
					<img id="img_github" src="img/github.png" alt="Avatar"
						class="img-thumbnail" onmouseover="onHoverInImage('img_github')"
						onmouseout="onHoverOutImage('img_github')"
						onclick="onClickImage('img_github')">
				</div>
			</div>
			<div class="col-md-3"></div>
		</div>
		<div id='api_response_block' class="row" hidden="">
			<br>
			<hr>
			<h2 style="color:white;">Look What I Found For You: </h2>
			<br>
			<pre id='api_response_data' style="text-align: left; white-space: pre-wrap; word-break: break-word; overflow: auto;">
			
			</pre>
		</div>
	</div>
	
	<script type="text/javascript">
		function onHoverInImage(imageId) {
			var eleImage = document.getElementById(imageId);
			eleImage.style = "background-color: rgba(255,255,255,0.8);";
		}
		function onHoverOutImage(imageId) {
			var eleImage = document.getElementById(imageId);
			eleImage.style = "";
		}
		function onClickImage(imageId) {
			$("#"+imageId+"_modal").modal('show');
		}
		function onClickImage(imageId) {
			$("#"+imageId+"_modal").modal('show');
		}
		function onFormUpdate(formId) {
			if(formId == "github") {
				var eleGitHubForm = document.getElementById("form_github");
				var gitapiData = eleGitHubForm.elements.namedItem("api_data").value;
				var gitapiType = eleGitHubForm.elements.namedItem("api_type").value;
				var eleGitApiOutput = eleGitHubForm.elements.namedItem("api_output");

				if(gitapiType != "username" && gitapiType != "email") {
					alert("Invalid API Type");
					return;
				}
				var linkToRedirect = "";
				linkToRedirect = "/rest/srv/GitHub/";
				linkToRedirect += gitapiType + "/";
				linkToRedirect += gitapiData;
				//window.location.href = ""
				eleGitApiOutput.value = linkToRedirect;

			} else if(formId == "hackerrank") {
				var eleHackerRankForm = document.getElementById("form_hackerrank");
				var hackerapiData = eleHackerRankForm.elements.namedItem("api_data").value;
				var hackerapiType = eleHackerRankForm.elements.namedItem("api_type").value;
				var eleHackerRankApiOutput = eleHackerRankForm.elements.namedItem("api_output");
				if(hackerapiType != "profile" && hackerapiType != "badges" && hackerapiType != 'submissionHistory') {
					alert("Invalid API Type");
					return;
				}
				var linkToRedirect = "";
				linkToRedirect = "/rest/srv/HackerRank/";
				linkToRedirect += hackerapiType + "/";
				linkToRedirect += hackerapiData;
				//window.location.href = ""
				eleHackerRankApiOutput.value = linkToRedirect;
				
			} else {
				alert("Invalid Form ID");
			}
		}

		function onFormSubmit(formId) {
			$("#img_"+formId+"_modal").modal('hide');

			/* Set Login Modal As Permanent */
			$('#modal_api_response').modal({
			    backdrop: 'static',
			    keyboard: false
			})
			$("#modal_api_response").modal('show');

			var linkToRedirect;
			if(formId == "github") {
				var eleGitHubForm = document.getElementById("form_github");		
				var eleGitapiOutput = eleGitHubForm.elements.namedItem("api_output");
				linkToRedirect = eleGitapiOutput.value;
			} else if(formId == "hackerrank") {
				var eleHackerrankForm = document.getElementById("form_hackerrank");
				var eleHackerRankApiOutput = eleHackerrankForm.elements.namedItem("api_output");
				linkToRedirect = eleHackerRankApiOutput.value;
			} else {
				alert("Invalid Form ID");
				return;
			}

			var xhttp = new XMLHttpRequest();
			xhttp.onreadystatechange = function() {
				if (this.readyState === 4 && this.status === 200) {
					var response = JSON.parse(this.responseText);
					var eleApiRespData = document.getElementById("api_response_data");
					eleApiRespData.innerHTML = JSON.stringify(response, undefined, 2);
					$("#modal_api_response").modal('hide');
					document.getElementById('api_response_block').removeAttribute("hidden");
				}
			}

			xhttp.open("GET", linkToRedirect, true);
			xhttp.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
			xhttp.send();
		}

	</script>
</body>
</html>