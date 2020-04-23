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


//This is for Getting the Available Challenge types as per the User Login
var challengeType = '<%=session.getAttribute("ChallengeTypeList")%>';

challengeType = challengeType.split(",");

var replaceItem = challengeType[0];
var challengeTypeList ="{";
challengeType[0] = replaceItem.replace("[","");

replaceItem = challengeType[challengeType.length-1]; 
challengeType[challengeType.length-1] = replaceItem.replace("]","");

for(var i = 0; i < challengeType.length; i++){
	   var item = challengeType[i];
	   challengeTypeList = challengeTypeList + "'"+item+"':'"+item+"',";
	   
}
challengeTypeList = challengeTypeList + "}";



//This is for Getting the Available Challenge Categories as per the User Login
var challengeCategory = '<%=session.getAttribute("ChallengeCategoryList")%>';

challengeCategory = challengeCategory.split(",");

var replaceItem = challengeCategory[0];
var challengeCategoryList ="{";
challengeCategory[0] = replaceItem.replace("[","");

replaceItem = challengeCategory[challengeCategory.length-1]; 
challengeCategory[challengeCategory.length-1] = replaceItem.replace("]","");

for(var i = 0; i < challengeCategory.length; i++){
	   var item = challengeCategory[i];
	   challengeCategoryList = challengeCategoryList + "'"+item+"':'"+item+"',";
	   
}
challengeCategoryList = challengeCategoryList + "}";





//This is for Getting the Available Challenge Status as per the User Login
var challengeStatus = '<%=session.getAttribute("ChallengeStatusList")%>';

challengeStatus = challengeStatus.split(",");

var replaceItem = challengeStatus[0];
var challengeStatusList ="{";
challengeStatus[0] = replaceItem.replace("[","");

replaceItem = challengeStatus[challengeStatus.length-1]; 
challengeStatus[challengeStatus.length-1] = replaceItem.replace("]","");

for(var i = 0; i < challengeStatus.length; i++){
	   var item = challengeStatus[i];
	   challengeStatusList = challengeStatusList + "'"+item+"':'"+item+"',";
	   
}
challengeStatusList = challengeStatusList + "}";




//This is for Getting the Available Challenge Categories as per the User Login
var challengePriority = '<%=session.getAttribute("ChallengePriorityList")%>';

challengePriority = challengePriority.split(",");

var replaceItem = challengePriority[0];
var challengePriorityList ="{";
challengePriority[0] = replaceItem.replace("[","");

replaceItem = challengePriority[challengePriority.length-1]; 
challengePriority[challengePriority.length-1] = replaceItem.replace("]","");

for(var i = 0; i < challengePriority.length; i++){
	   var item = challengePriority[i];
	   challengePriorityList = challengePriorityList + "'"+item+"':'"+item+"',";
	   
}
challengePriorityList = challengePriorityList + "}";





var dataIds = [ "serial_no","challenge_id", "company_name", "plant_name","challenge_type", "challenge_category", "challenge","priority","quant_meas_success","challenge_start_date","challenge_end_date","challenge_status","reason_for_drop" ];
var tableHeaders = [ "S.No.","Challenge ID","Company Name","Plant Name","Challenge Type","Challenge Category","Challenge","Priority","Quantitative Measure of Success","Challenge Start Date","Challenge End Date","Status","Reason for Drop(If Applicable)"]; 
	var datatable;
	function loadTable(jsonData) {
		
		var plants;
		var tableString = JSON.stringify(jsonData);
		var companyName = tableString.substring(tableString.indexOf("company_name") + 15,	tableString.indexOf("company_plant") - 3);
		var allPlants = '<%=session.getAttribute("UserAccesiblePlants")%>';

		allPlants = allPlants.split("],");

		for (var i = 0; i < allPlants.length; i++) {

			if (allPlants[i].includes(companyName)) {
				plants = allPlants[i].substring(allPlants[i].indexOf(companyName)
						+ companyName.length + 2, allPlants[i].length - 1);
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
					sUpdateURL : "EditChallengeStatus.action",
					fnOnEdited : function(status) {
						ajaxServerCall("TrackChallenges.action?",
								"GET", null, loadTable);
					},
					"aoColumns" : [
							null,
							null,
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								data : companyList,
								"submitdata" : function() {
									return {
										"challengeId" : document
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
								data : plantList,
								event : 'click',
								"submitdata" : function() {
									return {

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"plantName" : document.getElementsByName("value")[0].value

									}


								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								data : challengeTypeList,
								event : 'click',
								"submitdata" : function() {
									return {

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"challengeType" : document.getElementsByName("value")[0].value

									}


								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								data : challengeCategoryList,
								event : 'click',
								"submitdata" : function() {
									return	{

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"challengeCategory" : document.getElementsByName("value")[0].value

									}


								}
							},
							{
								type : 'text',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								"submitdata" : function() {
									return	{

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"challenge" : document.getElementsByName("value")[0].value

									}


								}
							},
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								data : challengePriorityList,
								event : 'click',
								"submitdata" : function() {
									return	{

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"challengePriority" : document.getElementsByName("value")[0].value

									}


								}
							},
							{
								type : 'text',
								onblur : 'cancel',
								submit : 'Update',
								event : 'click',
								"submitdata" : function() {
									return	{

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"quantMeasSuccess" : document.getElementsByName("value")[0].value

									}

								}
							},
							null,
							null,
							{
								type : 'select',
								onblur : 'cancel',
								submit : 'Update',
								data : challengeStatusList,
								event : 'click',
								"submitdata" : function() {
									return	{

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"challengeStatus" : document.getElementsByName("value")[0].value

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

										"challengeId" : document.getElementById("jsonTable").rows[$(this).parent().index() + 1].cells[1].innerText
										,"reasonForDrop" : document.getElementsByName("value")[0].value

									}


								}
							}
							
							]

				});
	}
	$(document).ready(
			function() {
				ajaxServerCall("TrackChallenges", "GET", null,
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
					<li class="active">Edit Challenge</li>
				</ol>			
			</section>
				<br>
		<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Upload Multiple Employee data </h3>
		
			
				<form id="challengeForm" name="challengeForm" action="dashboardAction" style="height: 100%;">
					<div  class="form-group">
					<label style="text-align: left;margin-top: .5em;">Id</label>
					<input id="Id" name="Id" type="text" cssClass="form-control span12"
							value="-99" style="size: 32" readonly="readonly"/>
					</div>
					<div class="form-group">
						<label style="text-align: left;">CHALLENGE - Crystalize
							the Challenge in 4W1H Language of Report</label>
						<textarea id= "challenge" name="challenge.challenge" class="form-control span12"
							style="size: 35;" tabindex=placeholder= "" rows="5"
							id="additionalinformation"></textarea>
					</div>
					<div class="form-group">
						<label>Challenge Type</label> <select
							name="challenge.challengeType" id="challengeType"
							class="form-control span12" tabindex=>
							<option selected="selected">Select Challenge Type</option>
							<option>Industrial</option>
							<option>Organizational</option>
							<option>Functional</option>
							<option>Individual</option>
						</select>
					</div>
					<div class="form-group">
						<label>Category</label>
						<select headerKey="-1" cssClass="form-control span12"
							headerValue="Select Category" list="categoryGroup"
							name="challenge.category" id="category" value="defaultCategory" />
					</div>
					<div class="form-group">
						<label>Quantitative Measure of Success</label> <input
							name="challenge.potentialRsMil" class="form-control span12"
							type="text" id="potentialRsMil" tabindex=2 placeholder="" value="" size="35">
					</div>
					<div class="form-group">
						<label>Status</label> <select class="form-control span12"
							name="challenge.status" id ="status" tabindex=>
							<option selected="selected">Select status</option>
							<option>Open-Priority1</option>
							<option>Open-Priority2</option>
							<option>Open-Priority3</option>
							<option>Pursued</option>
							<option>Closed</option>
						</select>
					</div>
					<div class="form-group">
					<input type="submit" name="method:submitChallenge" class=" btn btn-primary center-block" value="Update" />
					</div>
					<div class="clearfix"></div>
				</form>
			</div>
		</div>
      </div>
	  	<%@include file="../uni/footer.jsp"%>
    </div>
</body>
</html>

