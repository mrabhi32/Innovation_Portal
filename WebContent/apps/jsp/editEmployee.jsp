<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<%@ page import="com.vgil.model.ApplicationConstants"%>
<script type="text/javascript">

//This is for getting the Available Roles as per the user Login.
var roles = '<%=session.getAttribute("UserAccessibleRole")%>';
roles = roles.split(",");

var replaceItem = roles[0];
var rolesList ="{";
roles[0] = replaceItem.replace("[","");

replaceItem = roles[roles.length-1]; 
roles[roles.length-1] = replaceItem.replace("]","");

for(var i = 0; i < roles.length; i++){
	   var item = roles[i];
	   rolesList = rolesList + "'"+item+"':'"+item+"',";
	   
}
rolesList = rolesList + "}";


//This is for Getting the Available Company Names as per the User Login
var companies = '<%=session.getAttribute("UserAccesibleCompany")%>';
companies = companies.split(",");

var replaceItem = companies[0];
var companyList ="{";
companies[0] = replaceItem.replace("[","");

replaceItem = companies[companies.length-1]; 
companies[companies.length-1] = replaceItem.replace("]","");

for(var i = 0; i < companies.length; i++){
	   var item = companies[i];
	   companyList = companyList + "'"+item+"':'"+item+"',";
	   
}
companyList = companyList + "}";



//This is for Getting the Available Department Names as per the User Login
var departments = '<%=session.getAttribute("departmentData")%>';
departments = departments.split(",");

var replaceItem = departments[0];
var departmentList ="{";
departments[0] = replaceItem.replace("[","");

replaceItem = departments[departments.length-1]; 
departments[departments.length-1] = replaceItem.replace("]","");

for(var i = 0; i < departments.length; i++){
	   var item = departments[i];
	   departmentList = departmentList + "'"+item+"':'"+item+"',";
	   
}
departmentList = departmentList + "}";


//This is for Getting the Available Designation Names as per the User Login
var designations = '<%=session.getAttribute("designationData")%>';
designations = designations.split(",");

var replaceItem = designations[0];
var designationList ="{";
designations[0] = replaceItem.replace("[","");

replaceItem = designations[designations.length-1]; 
designations[designations.length-1] = replaceItem.replace("]","");

for(var i = 0; i < designations.length; i++){
	   var item = designations[i];
	   designationList = designationList + "'"+item+"':'"+item+"',";
	   
}
designationList = designationList + "}";




var dataIds = [ "serial_no","user_id","firstName",  "lastName", "emailID","userRole", "contactNo", "companyName", "plantName","departmentName","designationName","userStatus","reasonForInactivation"];
var tableHeaders = [ "S.No", "User ID","First Name","Last Name","Email Address","Role","Contact No.","Company","Plant","Department","Designation","User Status","Reason for Inactivation(If applicable)"]; 
	var datatable;
	function loadTable(jsonData) {
		
		var plants;
		var tableString = JSON.stringify(jsonData);
		var company = tableString.substring(tableString.indexOf("companyName")+14,tableString.indexOf("contactNo")-3);
		
		

		var allPlants = '<%=session.getAttribute("UserAccesiblePlants")%>';

		allPlants = allPlants.split("],");

		for (var i = 0; i < allPlants.length; i++) {

			if (allPlants[i].includes(company)) {
				plants = allPlants[i].substring(allPlants[i].indexOf(company)
						+ company.length + 3, allPlants[i].length - 1);
			}

		}
		plants = plants.split(",");

		var replaceItem = plants[0];
		var plantList = "{";
		plants[0] = replaceItem.replace("[", "");

		replaceItem = plants[plants.length - 1];
		plants[plants.length - 1] = replaceItem.replace("]", "");

		for (var i = 0; i < plants.length; i++) {
			var item = plants[i];
			plantList = plantList + "'" + item + "':'" + item + "',";

		}
		plantList = plantList + "}";

		datatable = createTable(jsonData.gridModel, dataIds, tableHeaders);
		datatable
				.makeEditable({
					sUpdateURL : "EditIEmployeeCapability.action",
					fnOnEdited : function(status) {
						ajaxServerCall("ReportAction?method:getUserList",
								"GET", null, loadTable);
					},
					"aoColumns" : [
							null,
							null,
							null,
							null,
							{
								type : 'text',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								"submitdata" : function() {
									return {
										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"emailID" : document
												.getElementsByName("value")[0].value
									}
								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								data : rolesList,
								"submitdata" : function() {
									return {
										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"userRole" : document
												.getElementsByName("value")[0].value

									}
								}
							},
							{
								type : 'text',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								"submitdata" : function() {
									return {
										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"contactNo" : document
												.getElementsByName("value")[0].value
									}
								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								data : companyList,
								"submitdata" : function() {
									return {
										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"companyName" : document
												.getElementsByName("value")[0].value
									}
								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								data : plantList,
								"submitdata" : function() {
									return {

										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"plantName" : document
												.getElementsByName("value")[0].value

									}
								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								data : departmentList,
								"submitdata" : function() {
									return {

										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"departmentName" : document
												.getElementsByName("value")[0].value

									}
								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								data : designationList,
								"submitdata" : function() {
									return {

										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"designationName" : document
												.getElementsByName("value")[0].value

									}
								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								data : "{'Active':'Active','InActive':'InActive'}",
								"submitdata" : function() {
									return {

										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"userStatus" : document
												.getElementsByName("value")[0].value

									}
								}
							},
							{
								type : 'text',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								"submitdata" : function() {
									return {

										"userId" : document
												.getElementById("jsonTable").rows[$(
												this).parent().index() + 1].cells[1].innerText,
										"reasonForInactivation" : document
												.getElementsByName("value")[0].value

									}
								}
							} ]

				});
	}
	$(document).ready(
			function() {
				ajaxServerCall("ReportAction?method:getUserList", "GET", null,
						loadTable);
			});
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
					<li class="active">Edit Employee</li>
				</ol>			
			</section>
				<br>		
		<div class="content">	
		<div class="main-content">
		<br/>
		<div class="alert alert-custom alert-dismissible" role="alert" style="width: 50%;text-align: left;margin-left: 1%">
		  <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">×</span></button>
		 To De-Activate a User, Please Add edit the <strong>Reason for Inactivation</strong> Column first and then choose Inactivate from the Status menu.	<br/>
		 For Re-Activation, Edie the <strong>Reason for Inactivation</strong> Column first and type <strong>NA</strong> and save the field. Now  choose Active from the Status menu.
		</div>
			<div class="panel panel-default">			    			   
			</div>
		</div>
		</div>
      </div>
	  	<%@include file="../uni/footer.jsp"%>
    </div>
	<div id="editForm" style="display: none;">
		<%@include file="editChallengeForm.jsp"%>
	</div>
  </body>
</html>

