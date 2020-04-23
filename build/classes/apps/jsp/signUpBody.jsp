
<script type="text/javascript">
$(function() {

	document.getElementById("password").onchange = validatePassword;
	document.getElementById("confirmPassword").onchange = validatePassword;
	document.getElementById("firstName").onchange = validateUsername;

});
function validatePassword(){
	var pass2=document.getElementById("confirmPassword").value;
	var pass1=document.getElementById("password").value;
	if(pass1!=pass2)
		document.getElementById("confirmPassword").setCustomValidity("Passwords Don't Match");
	else
		document.getElementById("confirmPassword").setCustomValidity('');	 
	//empty string means no validation error
	}

function validateUsername(){
	var username = document.getElementById("firstName").value;
	if(!isNaN(username))
		
	{
		
		document.getElementById("firstName").setCustomValidity("Username cannot contain Numbers");
		
	}	
	
}
</script>
<div class="form-group-custom">
	<table style="width: 100%;">
		<tr style="width: 100%;">
			<td class="form-control-table" style="width: 20%"><label>
					First Name</label></td>
			<td class="form-control-table" style="width: 20%"><input name="employee.firstName" class="form-control-customize"
		type="text" tabindex=1 placeholder="" id="firstName" required title="First Name" value="" size="25" /></td>
			<td style="width: 10%"></td>
			<td class="form-control-table" style="width: 20%"><label>
					Last Name</label></td>
			<td class="form-control-table" style="width: 40%"><input name="employee.lastName" class="form-control-customize"
		type="text" tabindex=2 placeholder="" id="lastname" required title="Last Name" value="" size="25" /></td>
			
		</tr>
	</table>
</div>
<div class="form-group-custom">
	<table style="width: 100%;">
		<tr style="width: 100%;">
			<td class="form-control-table" style="width: 20%"><label>
					Email Address</label></td>
			<td class="form-control-table" style="width: 20%"><input
				name="employee.emailID" class="form-control-customize"
				type="email" tabindex=3 id="emailId" placeholder="" required  title="Email Address" 
				value="" size="25" /></td>
			<td style="width: 10%"></td>
			<td class="form-control-table" style="width: 20%"><label>
					Select Role</label></td>
			<td class="form-control-table" style="width: 40%">
				<table style="width: 100%; margin-top: 0px;">
							<table style="width: 100%;margin-top: 0px;"><s:select headerKey="" cssClass="form-control-customize"  class="validate[required]" required="required"
							style="margin-top:1.5%;width:65%;" headerValue="Select Role" list="roleList" name="employee.userRole" value="" id="role"  tabindex="4" /></table>
				</table>
			</td>
		</tr>
	</table>
</div>
<div class="form-group-custom">
	<table style="width: 100%;">
		<tr style="width: 100%;">
			<td class="form-control-table" style="width: 20%"><label>
					Password</label></td>
			<td class="form-control-table" style="width: 20%"><input name="employeelogininfo.password"
		class="form-control-customize" type="password" tabindex=5 id="password"
		placeholder="" required value="" size="25" /></td>
		
			<td style="width: 10%"></td>
			<td class="form-control-table" style="width: 20%"><label>
					Confirm Password</label></td>
			<td class="form-control-table" style="width: 40%">
				<table style="width: 100%; margin-top: 0px;">
					<input
		name="employeelogininfo.confirmPassword"
		class="form-control-customize" type="password" tabindex=6 placeholder=""  
		id="confirmPassword" required title="confirmPassword" value="" size="25"  style="width:65%;"/>
				</table>
			</td>
		</tr>
	</table>
</div>
<div class="clearfix"></div>
