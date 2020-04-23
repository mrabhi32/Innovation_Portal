<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
function validateForm(){
	var password = document.getElementById("password");
	console.log(password);
	var confirm_password = document.getElementById("confirmPassword");
	console.log(confirm_password);
	var role = document.getElementById("role");
	console.log(role);
	if(role == -1){
		alertify.error('Invalid Role. Please Select a Valida role.');
		return;
	}
	if(password != confirm_password){
		alertify.error('Password not matched. Please make sure password and confirm password should match');
		return;
	}
	document.getElementById("signup").action = "registerUser?method:registerUser";
	document.getElementById("signup").submit();
}
</script>
</head>
<body class="skin-blue">
	<div class="dialog">
		<div class="panel panel-default">
		<a href="#widget1container" class="panel-heading"
   							data-toggle="collapse">SIGN UP </a>
						<div id="widget1container" class="panel-body collapse in">
			<div class="panel-body">
				<form method="post" action="" id = "signup" style="height: 100%;">
					<%@include file="signUpBody.jsp"%>
					<div class="form-group">
						<label>Contact No</label> <input name="employee.user.contactNo"
							class="form-control span12" type="text" id="mobileNo"
							placeholder="" required="" value="" size="25" />
					</div>
					<div class="form-group">
						<label>Company</label>
						<s:select headerKey="-1" headerValue="Select Company"
							cssClass="form-control span12" list="objCompanyGroup"
							name="employee.user.companyName" value="defaultCompany" />
					</div>
					<div class="form-group">
						<label>Select Plant</label>
						<s:select headerKey="-1" cssClass="form-control span12"
							style="margin-top:1.5%;" headerValue="Select Plant"
							list="objPlantGroup" name="employee.plant" value="defaultplant" />
					</div>
					<div class="form-group">
						<label>Department</label> <input name="employee.department"
							type="text" class="form-control span12" id="mobileNo"
							placeholder="" required="" value="" size="25" />
					</div>
					<div class="form-group">
						<label>Buddy</label> <input name="employee.buddy"
							class="form-control span12" type="text" id=""
							placeholder="" required="" value="true" size="25"
							readonly="readonly" />
					</div>
					<div class="form-group">
						<label>Responsibility Level</label> <select
							name="employee.responsiblityLevel" id="role"
							class="form-control span12">
							<option selected="selected">Select User Type</option>
							<option value="TEAMLEAD">Team Lead</option>
							<option value="ASSOCIATE">Associate</option>
						</select>
					</div>
					<div class="form-group">
						<label>Role</label>
						<s:select headerKey="-1" headerValue="Select Role"
							cssClass="form-control span12" list="objRole"
							name="employee.user.role" value="defaultRole" />
					</div>
					<div class="form-group">
						<!-- <a href="index.html" class="btn btn-primary pull-right">Sign
							Up!</a>  -->
							<input type="button" value="Sign Up!" class="btn btn-primary pull-right" onclick="validateForm();"/>
					</div>
				</form>
			</div>
		</div>
		</div>
	<%@include file="../uni/footer.jsp"%>
	</div>
</body>
</html>