<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var allowedFileTypes = "csv::txt";
	window.onload = function() {
		try {
			uploadChallenges.selectedIndex = 0;
			uploadChallenges.file.value = "";
		} catch (e) {
		}
	}

	function validation() {

		var error = "";
		var olddiv = document.getElementById('messages');
		if (olddiv != null) {
			olddiv.parentNode.removeChild(olddiv);
		}

		if ("" == uploadChallenges.file.value) {
			uploadChallenges.file.focus();
			document.getElementById("errorMsg").innerHTML = "";
			document.getElementById("errorMsg").innerHTML = "Please select a file to upload";
			error = "ERROR";
			return (false);
		}

		var fileTypeMismach = false;
		var fileTypes = allowedFileTypes.split("::");
		for (var i = 0; i < fileTypes.length; i++) {
			if (fileTypes[i] == uploadChallenges.file.value.split('.').pop()
					.toString()) {
				fileTypeMismach = true;
			}
		}
		if (!fileTypeMismach) {
			uploadChallenges.file.value = "";
			uploadChallenges.file.focus();
			document.getElementById("errorMsg").innerHTML = "";
			document.getElementById("errorMsg").innerHTML = "This File extension is not allowed. Please select only .txt or  .csv file type";
			error = "ERROR";
			return (false);

		}
		document.getElementById("errorMsg").innerHTML = "";
		
	

	

	if (error == "") {

		uploadChallenges.submit();
	}
	}
</script>
</head>
<body class="skin-blue">
<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="content">
		<!-- <div class="header">
			<ul class="breadcrumb">
				<li><a href="dashboardAction?method:setUpDashboard">DashBoard</a></li>
				<li><a href="#">Employee</a></li>
				<li class="active">Upload</li>
			</ul>

		</div> -->
		<div class="main-content">
			<div class="panel panel-default">
				<a href="#widget1container" class="panel-heading"
					data-toggle="collapse">Upload Multiple Employee data </a>
				<form id="uploadChallenges"
					action="uploadEmployee.action?method:uploadEmployee" method="post"
					enctype="multipart/form-data">

					<center style="margin-top: 1em;">
						<s:file cssClass="btn btn-primary center-block" name="file" />
						<table class="center-block"
							style="padding-left: 500px; padding-top: 10px;">
							<tr>
								<td class="actionmsg" id="errorMsg"
									style="color: red; align: center;"></td>
							</tr>
							<tr>
								<td align="center"><a
									href="../../apps/files/employee_upload.csv" download>Click
										here to download a CSV Template</a></td>
							</tr>
						</table>
					</center>
					<div class="form-group">
						<br> <br> <br> <br>

						<table border=1>

							<tr>
								<td align="left" class="">Instructions:</td>
							</tr>
							<tr>
								<td class="">1) Click 'Browse' to choose the file you wish
									to upload.</td>
							</tr>

							<tr>
								<td class=""><br> 2) File extension must be either
									.csv or .txt</td>
							</tr>
							<tr>
								<td class=""><br>3) Single employee entry on each
									line.</td>
							</tr>
							<tr>
								<td class=""><br>4) User name for each Employee should
									be the Email Id of the User.</td>
							</tr>
							<tr>
								<td class=""><br>5) The users will be uploaded only if
									all the records in the File match the Standard Validations.</td>
							</tr>
						</table>
					</div>

					<div style="float: center;">
						<input type="button" value="Upload"
							class=" btn btn-primary center-block"
							style="margin-bottom: .6em;" onclick="javascript:validation();" />
					</div>
				</form>
				<!-- form -->
			</div>
			<!-- container -->
		</div>
		<%@include file="../uni/footer.jsp"%>
	</div>
</body>
</html>
