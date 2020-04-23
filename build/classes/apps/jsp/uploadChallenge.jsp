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
 <div class="wrapper">
      <div class="content-wrapper">
		<section class="content-header">
				<br><ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Dashboard</li>
					<li class="active">Bulk Upload Challenge</li>
				</ol>			
			</section>
				<br>
		<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Upload Multiple Employee data </h3>
				<form id="uploadEmployees"
					action="uploadEmployee.action?method:uploadEmployee" method="post"
					enctype="multipart/form-data">

					<center style="margin-top: 1em;">
					<input type="file" name="fileupload" value="fileupload" id="fileupload" Class="btn btn-primary center-block">
						<table class="center-block"
							style="padding-top: 10px;">
						
								<tr class="actionmsg" id="errorMsg"style="color: red; align: center;"></tr>
								<tr align="center"><a href="../../apps/files/employee_upload.csv" download>Click here to download a CSV Template</a></tr>
								<br>
								<tr align="center"><a href = "../../apps/files/error_log.txt" download> Click here to view the Error Log File</a></tr>
							
						</table>
					</center>
					<center class="form-group">
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
								<td class=""><br>3) The file being uploaded
									is expected to be in the below format <br> Company name<b>,
								</b> Plant name<b>, </b>Challenge Descrption<b>, </b> Challenge Type
									(Industrial/Organizational/Functional/Individual)<b>, </b>
									Category<b>, </b>Potential (in Rs)<b>,  </b>Status
									(Open-Priority1/Open-Priority2/Open-Priority3)
									with a single challenge on each line.</td></td>
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
					</center>

					<div style="float: center;">
						<input type="button" value="Upload"
							class=" btn btn-primary center-block"
							style="margin-bottom: .6em;" onclick="javascript:validation();" />
					</div>
				</form>
			</div>
		</div>
      </div>
	 	<%@include file="../uni/footer.jsp"%>
    </div>

</body>
</html>
