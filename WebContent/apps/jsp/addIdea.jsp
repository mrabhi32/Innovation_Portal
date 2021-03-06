<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
var selectedValue;
$(document).ready(function(){
	$(function() {
		$( "#picker" ).datepicker();
		$( "#challengeNo" ).attr( "required", "required" );
		
	});
	/* document.getElementById("ideaForm").action = "saveIdea?method:saveIdeaForChallenge";
	if(<s:property value='challengeNo'/> == 0){
		//document.getElementById("challengeNo").value = "Select Challenge No";
	} else{
		document.getElementById("challengeNo").value =<s:property value='challengeNo'/>
	} */
	});
	function getChallengeDetails() {
		document.getElementById("ideaForm").action = "getSelectedChallenge.action";
		document.getElementById("ideaForm").submit();
	}
	function saveIdea() {
		var text = document.getElementById("picker").value
		var comp = text.split('/');
		var m = parseInt(comp[0], 10);
		var d = parseInt(comp[1], 10);
		var y = parseInt(comp[2], 10);
		var date = new Date(y,m-1,d);
		/* if (date.getFullYear() == y && date.getMonth() + 1 == m && date.getDate() == d) {
			//document.getElementById("picker").setCustomValidity("Passwords Don't Match");
		} else {
			document.getElementById("picker").setCustomValidity("Invalid Date");
			return false;
		} */
		document.getElementById("ideaForm").action = "saveIdea?method:saveIdeaForChallenge";
		document.getElementById("ideaForm").submit();
	}

	function displayPriorityVal() {
		var unknownd = document.getElementById("unknownd").value;
		var impact = document.getElementById("impact").value;
		var investment = document.getElementById("investment").value;

		/* if (impact == 'Low') {
			document.getElementById('priorityId').value = 'D';
		}
		if (impact == 'High') {
			if (investment == 'High' && unknownd == 'High') {
				document.getElementById('priorityId').value = 'C';
			} else if (investment == 'Low' && unknownd == 'Low') {
				document.getElementById('priorityId').value = 'A';
			} else {
				document.getElementById('priorityId').value = 'B';
			}
		} */
		document.getElementById("Text1").value = document
				.getElementById('priorityId').value;
	}

	function validateDate(text){
		var comp = text.split('/');
		var m = parseInt(comp[0], 10);
		var d = parseInt(comp[1], 10);
		var y = parseInt(comp[2], 10);
		var date = new Date(y,m-1,d);
		if (date.getFullYear() == y && date.getMonth() + 1 == m && date.getDate() == d) {
		  alert('Valid date');
		} else {
		  alert('Invalid date');
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
					<li class="active">Add Idea</li>
				</ol>			
			</section>
				<br>

		<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Add New Idea </h3>
				<form id="ideaForm" action="saveIdea" method="post"
					style="height: 100%;">
					<div class="form-group-custom">
						<table style="width: 100%;" border=1>
							<tr style="width: 100%;">
								<td class="form-control-table" style="width: 15%"><label>Select
										Challenge No</label></td>
								<td style="width: 10%"></td>
								<td class="form-control-table" style="width: 15%;" align="left"><label>Challenger</label></td>
								<td style="width: 10%"></td>
								<td class="form-control-table" style="width: 10%"><label>Challenge
										Type</label></td>
							</tr>
							<tr style="width: 100%;">
								<!-- <td class="form-control-table" style="width: 15%"><label >Select Challenge No</label></td> -->
								<td class="form-control-table" style="width: 20%;">
									<table>
										<input name="challenge.company" type="hidden"
											value="<property value='compName'/>" style="size: 32" />
										<select headerKey="" Class="form-control-customize"
											style="width:100%; margin-left: 3em;"
											headerValue="Select Challenge No" list="selectChallenge"
											id="challengeNo" name="challengeNo"
											onchange="getChallengeDetails()" value="selectIndex"
											tabindex="1" />
									</table>
								</td>
								<td style="width: 10%"></td>
								<!-- <td class="form-control-table" style="width: 15%"><label >Challenger</label></td> -->
								<td class="form-control-table" style="width: 20%"><input
									name="ideaBank.challenger" class="form-control-customize"
									type="text" readonly="readonly" placeholder="" id="userId"
									required="" title="Challenger"
									value="<s:property value='challenge.userId'/>" size="35" /></td>
								<td style="width: 10%"></td>
								<!-- <td class="form-control-table" style="width: 10%"><label>Challenge Type</label></td> -->
								<td class="form-control-table" style="width: 30%"><input
									name="ideaBank.challengeType" class="form-control-customize"
									type="text" readonly="readonly" style="width: 65%;"
									placeholder="" id="challengeType" required=""
									title="challengeType"
									value="<s:property value='challenge.challengeType'/>" size="35" /></td>

							</tr>
						</table>
					</div>

					<div class="form-group" style="margin-left: 0px;">
						<label>Challenge</label>
						<textarea name="ideaBank.challenge" class="form-control span12"
							readonly="readonly" rows="4" id="challenge" tabindex="2">
 					<property value='challenge.challenge' />
 					</textarea>
					</div>


					<div class="form-group">
						<label>Frame - Enter the Frame of Sub- Frame which
							demonstrates Intent of the idea in generic way</label> <input
							class="form-control span12" name="ideaBank.frame" type="text"
							placeholder="" id="frame" required="" title="frame" value=""
							tabindex="3" size="69" />

					</div>
					<div class="form-group">
						<label>Idea - Enter the Idea in the form of a sentence
							using 4W1H format</label>
						<textarea class="form-control span12" name="ideaBank.idea"
							required="" placeholder="" rows="4" id="idea" tabindex="4"></textarea>
					</div>

					<div class="form-group-custom">
						<table style="width: 100%;">
							<tr style="width: 100%;">
								<td class="form-control-table" style="width: 20%"><label>Impact
										Value Rs</label></td>
								<td class="form-control-table" style="width: 20%"><input
									name="ideaBank.impactValue" class="form-control-customize"
									type="number" min="0" placeholder="" id="impactValue"
									required="" title="impactValue" value="" size="35" tabindex="5" /></td>
								<td style="width: 10%"></td>
								<td class="form-control-table" style="width: 10%"><label>Investment
										value Rs</label></td>
								<td class="form-control-table" style="width: 30%"><input
									name="" type="number" min="0" class="form-control-customize"
									placeholder="" style="width: 65%;" id="impactValue" required=""
									title="impactValue" value="" size="35" tabindex="6" /></td>
							</tr>
						</table>
					</div>

					<div class="form-group-custom">
						<table style="width: 100%;">
							<tr style="width: 100%;">
								<td class="form-control-table" style="width: 20%"><label>Implementation
										Time</label></td>
								<td class="form-control-table" style="width: 20%"><input
									type="text" id="picker" data-provide="datepicker" required=""
									class="form-control-customize"
									name="ideaBank.implementationTimeString" value="" size="35"
									tabindex="7"></td>
								<td style="width: 10%"></td>
								<td class="form-control-table" style="width: 10%"><label>Investment</label></td>
								<td class="form-control-table" style="width: 30%"><select
									name="ideaBank.investment" class="form-control-customize"
									id="investment" style="width: 65%;" required=""
									onchange="displayPriorityVal()" tabindex="8">
										<option value="">Select Investment</option>
										<option value="High">High</option>
										<option value="Low">Low</option>
								</select></td>
							</tr>
						</table>
					</div>
					<div class="form-group">
						<div style="float: left;">
							<label
								style="text-align: left; font-size: 11px; font-family: Arial, Helvetica, sans-serif; width: 100%;">
								<b>High Impact</b>- Idea meeting 20- 25% and above of the
								Aspiration</br> <b>Low Impact </b>- Idea meeting less than 20% and
								below Aspiration.
							</label>
						</div>
						<div style="float: left; margin-left: 25%;">
							<label
								style="text-align: left; font-size: 11px; font-family: Arial, Helvetica, sans-serif; width: 100%;">
								<b>High Unknown </b>- Many thing are not Known to Implement the
								Idea.</br> <b>Low Unknown </b>- Almost everything are known to
								Implement the Idea.
							</label>
						</div>
					</div>
					<br>
					<div class="form-group-custom">
						<table style="width: 100%;">
							<tr style="width: 100%;">
								<td class="form-control-table" style="width: 20%"><label>Impact</label></td>
								<td class="form-control-table" style="width: 20%"><select
									name="ideaBank.impact" id="impact"
									class="form-control-customize" onchange="displayPriorityVal()"
									tabindex="12" required="">
										<option value="">Select Impact</option>
										<option value="High">High</option>
										<option value="Low">Low</option>
								</select></td>
								<td style="width: 10%"></td>
								<td class="form-control-table" style="width: 10%"><label>Unknown</label></td>
								<td class="form-control-table" style="width: 30%"><select
									name="ideaBank.unknownd" headerValue="Select Unknown"
									class="form-control-customize" id="unknownd"
									style="width: 65%;" onchange="displayPriorityVal()"
									tabindex="13" required="">
										<option value="">Select Unknown</option>
										<option value="High">High</option>
										<option value="Low">Low</option>
								</select></td>
							</tr>
						</table>
					</div>
					<div class="form-group-custom">
						<table style="width: 100%;">
							<tr style="width: 100%;">

								<form action="fileupload.jsp" method="post" enctype="multipart/form-data">
									<input type="file" name="file" size="50" /> <br /> 
								</form>
							</tr>
						</table>
					</div>

					<div class="form-group">
						<input type="submit" value="Create" tabindex="18"
							class=" btn btn-primary center-block"
							name="method:saveIdeaForChallenge" />

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
