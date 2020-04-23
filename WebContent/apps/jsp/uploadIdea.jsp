<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var allowedFileTypes = "csv::txt::dat";
	window.onload = function() {
		try {
			uploadChallenges.selectedIndex = 0;
			uploadChallenges.file.value = "";
		} catch (e) {
		}
	}

	function validation() {
		try {
			var olddiv = document.getElementById('messages');
			if (olddiv != null) {
				olddiv.parentNode.removeChild(olddiv);
			}
		} catch (e) {
		}
		if ("" == uploadChallenges.file.value) {
			uploadChallenges.file.focus();
			document.getElementById("errorMsg").innerHTML = "";
			document.getElementById("errorMsg").innerHTML = "Please select a file to upload";
			document.getElementById("error").style.display = "block";
			return (false);
		}

		try {
			var fileTypeMismach = false;
			var fileTypes = allowedFileTypes.split("::");
			for ( var i = 0; i < fileTypes.length; i++) {
				if (fileTypes[i] == uploadChallenges.file.value.split('.')
						.pop().toString()) {
					fileTypeMismach = true;
				}
			}
			if (!fileTypeMismach) {
				uploadChallenges.file.value = "";
				uploadChallenges.file.focus();
				document.getElementById("errorMsg").innerHTML = "";
				document.getElementById("errorMsg").innerHTML = "This File extension is not allowed. Please select only .txt , .csv or .dat file type";
				document.getElementById("error").style.display = "block";
				return (false);
			}
		} catch (e) {
		}

		document.getElementById("errorMsg").innerHTML = "";
		document.getElementById("error").style.display = "none";
		uploadChallenges.submit();
	}
</script>
</head>
<body class="skin-blue">
<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="wrapper">
      <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <br><ol class="breadcrumb">
            <li><a href="#"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
			<li class="active">Bulk Uplode Ideas</li>
          </ol>
        </section>
        <br>
		<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Upload Multiple Ideas </h3>
				<form id="uploadEmployees"
					action="uploadEmployee.action?method:uploadEmployee" method="post"
					enctype="multipart/form-data">

					<center style="margin-top: 1em;">
					<input type="file" name="fileupload" value="fileupload" id="fileupload" Class="btn btn-primary center-block">
						<table class="center-block"
							style="padding-top: 10px;">
							
								<tr class="actionmsg" id="errorMsg" style="color: red; align: center;"></tr>
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
