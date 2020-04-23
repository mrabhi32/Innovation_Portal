<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
$(function() {
	$( "#role" ).attr( "required", "required" );
	$( "#plant" ).attr( "required", "required" );
	$( "#companyName" ).attr( "required", "required" );
});

function loadPlants(company){
	console.log(company);
	$("#plant").empty();
	$('#plant').append(
	        $("<option></option>")
	          .attr("value", "")
	          .attr("selected", "selected")
	          .text("Select Plant")
	 );
	ajaxServerCall("ReportAction?method:getPlants&company="+company, "GET",null, addPlants);
	
}

function addPlants(jsonData){
	console.log(jsonData);

	$.each(jsonData.gridModel, function (key, value) {
	    $('#plant').append(
	        $("<option></option>")
	          .attr("value", value)
	          .text(value)
	    );
	});
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
					<li class="active">Add New Employee</li>
				</ol>			
			</section>
				<br>
					<div class="main-content">
						<div class="panel panel-primary">
							<h3  class="panel-heading">Add New Employee </h3>
									<div class="panel-body">
										<form method="post" action="employeeUserRegistration.action" id="signup" style="height: 100%;">
												<div class="form-group-custom">
													<table style="width: 100%;margin-top:4%;">
														<tr style="width: 100%;">
															<td class="form-control-table" style="width: 20%"><label>First Name</label></td>
															<td class="form-control-table" style="width: 20%"><input name="employee.firstName" class="form-control-customize"
																type="text" tabindex=1 placeholder="" id="firstName" required title="First Name" value="" size="25" /></td>
															<td style="width: 10%"></td>
															
															<td class="form-control-table" style="width: 20%"><label>Last Name</label></td>
															<td class="form-control-table" style="width: 20%"><input name="employee.lastName" class="form-control-customize"
																type="text" tabindex=2 placeholder="" id="lastname" required title="Last Name" value="" size="25" /></td>
															<td style="width: 10%"></td>																
														</tr>
													</table>
												</div>
										
												<div class="form-group-custom">
													<table style="width: 100%;margin-top:4%;">
														<tr style="width: 100%;">
															<td class="form-control-table" style="width: 20%"><label>Password</label></td>
															<td class="form-control-table" style="width: 20%">
															<input name="employeelogininfo.password"
																class="form-control-customize" type="password" tabindex=5 id="password"
																placeholder="" required value="" size="25" /></td>
															<td style="width: 10%"></td>
															<td class="form-control-table" style="width: 20%"><label>Confirm Password</label></td>
															<td class="form-control-table" style="width: 20%">
															<input name="employeelogininfo.confirmPassword"
																class="form-control-customize" type="password" tabindex=6 placeholder=""  
																id="confirmPassword" required title="confirmPassword" value="" size="25"/>
															</td>
															<td style="width: 10%"></td>
														</tr>
													</table>
												</div>
												<div class="form-group-custom">
													<table style="width: 100%;margin-top:4%;">
														<tr style="width: 100%;">
															<td class="form-control-table" style="width: 20%"><label>Email Address</label></td>
															<td class="form-control-table" style="width: 20%"><input name="employee.emailID" class="form-control-customize"
																type="email" tabindex=3 id="emailId" placeholder="" required  title="Email Address" value="" size="25" /></td>
															<td style="width: 10%"></td>
															<td class="form-control-table" style="width: 20%"><label>Select Role</label></td>
															<td class="form-control-table" style="width: 20%">
																<table style="width: 100%;margin-top: 0px;">
																	<select name="employee.plantName" id="plant"
																	class="form-control-customize" tabindex=9 style="width:100%;" required="required">
																	<option selected="selected" value="">Select Plant</option>
																	</select>
																</table>
															</td>
															<td style="width: 10%"></td>
														</tr>
													</table>
												</div>
												<div  class="form-group-custom">
													<table style="width: 100%;margin-top:4%;">
														<tr  style="width: 100%;">
															<td class="form-control-table" style="width: 20%"><label >Contact No</label></td>
															<td class="form-control-table" style="width: 20%">
															<input name="employee.contactNo" class="form-control-customize" type="text" placeholder="" 
																id="mobileNo" required  title="Contact No" value="" min="0"
																size="25" tabindex="6"/></td>
															<td style="width: 10%"></td>
															<td class="form-control-table" style="width: 20%"><label>Company</label></td>
															<td class="form-control-table" style="width: 20%">
																<table style="width: 100%;margin-top: 0px;">
																	<select name="employee.plantName" id="plant"
																	class="form-control-customize" tabindex=9 style="width:100%;" required="required">
																	<option selected="selected" value="">Select Plant</option>
																	</select>
																</table>
															</td>
															<td style="width: 10%"></td>
														</tr>
													</table>
												</div>
												<div  class="form-group-custom">
													<table style="width: 100%;margin-top:4%;">
														<tr  style="width: 100%;">
															<td class="form-control-table" style="width: 20%"><label>Department</label></td>
															<td class="form-control-table" style="width: 20%">
																<table style="width: 100%;margin-top: 0px;">
																	<select name="employee.plantName" id="plant"
																	class="form-control-customize" tabindex=9 style="width:100%;" required="required">
																	<option selected="selected" value="">Select Plant</option>
																	</select>
																</table>
															</td>
															<td style="width: 10%"></td>
															<td class="form-control-table" style="width: 20%"><label>Plant</label></td>
															<td class="form-control-table" style="width: 20%">
																<table style="width: 100%;margin-top: 0px;">
																	<select name="employee.plantName" id="plant"
																	class="form-control-customize" tabindex=9 style="width:100%;" required="required">
																	<option selected="selected" value="">Select Plant</option>
																	</select>
																</table>
															</td>
															<td style="width: 10%"></td>															
														</tr>	
													</table>					
												</div>
												<div  class="form-group-custom">												
													<table style="width: 50%;margin-top:4%;">
														<tr  style="width: 100%;">
															<td class="form-control-table" style="width: 20%"><label >Designation</label></td>				
																<td class="form-control-table" style="width: 20%">
																	<table style="width: 100%;margin-top: 0px;">
																		<select name="employee.plantName" id="plant"
																		class="form-control-customize" tabindex=9 style="width:100%;" required="required">
																		<option selected="selected" value="">Select Plant</option>
																		</select>
																	</table>
																</td>
																<td style="width: 10%"></td>
														</tr>														
													</table>
												</div>
												<div class="form-group">
													<input type="submit" tabindex="12"
														class=" btn btn-primary center-block"
														value="Sign Up!"/>
												</div>
										</form>
									</div>
						</div>
					</div>
		</div>
			<%@include file="../uni/footer.jsp"%>
    </div>
</body>
</html>