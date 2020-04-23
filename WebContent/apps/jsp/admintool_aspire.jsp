<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
function saveAspiredValues() {
	var result = document.getElementById('company').value;
	if (result == "-1") {
		alertify.error("Please select Company");
	}else {
		document.getElementById("saveAspireValues").action = "saveAspireValues";
		document.getElementById("saveAspireValues").submit();
	}
}

</script>
<script type="text/javascript">
$(function() {
	$( "#company" ).attr( "required", "required" );
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
					<li class="active">Admin Tool</li>
					<li class="active">Save Aspire Value</li>
				</ol>			
			</section>
				<br>
		<div class="main-content">
		<div class="panel panel-primary">
		<h3 class="panel-heading">Enter FS details</h3>
		<form id="saveAspireValues" action="saveAspireValues" method="post" style="100%">
						<div  class="form-group-custom">
						<table style="width: 100%;">
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 20%"><label>Select Company</label></td>
								<td class="form-control-table" style="width: 40%">
								<s:select headerKey="" headerValue="Select Company" id="company"
							cssClass="form-control-customize" list="objCompanyGroup" style="width:80%;"
							name="company" value="" required="required"/>
									</td>
							</tr>
						</table>
						</div>
						<table style="width: 100%;">
							<tr  style="width: 100%;"><td></br></br></td>
							</tr>
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 3%;font-size: 14px;"><label >Innovators</label></td>
								<td class="form-control-table" style="width: 5%"><input name="innovatorsValue"
									class="form-control-customize" type="number" placeholder="" 
									id="innovatorsValue" required="" title="innovatorsValue" value="" min="0"
									size="15" tabindex="1"/></td>
								<td style="width: 5%"></td>
							</tr>
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 3%;font-size: 14px;"><label >Innovator starters</label></td>
								<td class="form-control-table" style="width: 5%"><input name="innovatorStartersValue"
									class="form-control-customize" type="number" placeholder="" 
									id="innovatorStartersValue" required="" title="innovatorStartersValue" value="" min="0"
									size="15" tabindex="1"/></td>
								<td style="width: 5%"></td>
							</tr>
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 3%;font-size: 14px;"><label >Innovation Budies</label></td>
								<td class="form-control-table" style="width: 5%"><input name="innovatorsBuddiesValue"
									class="form-control-customize" type="number" placeholder="" 
									id="innovatorsBuddiesValue" required="" title="innovatorsBuddiesValue" value="" min="0"
									size="15" tabindex="1"/></td>
								<td style="width: 5%"></td>
							</tr>
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 3%;font-size: 14px;"><label >F1</label></td>
								<td class="form-control-table" style="width: 5%"><input name="fs1Value"
									class="form-control-customize" type="number" placeholder="" 
									id="fs1Value" required="" title="fs1Value" value="" min="0"
									size="15" tabindex="1"/></td>
								<td style="width: 5%"></td>
							</tr>
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 3%;font-size: 14px;"><label >F2</label></td>
								<td class="form-control-table" style="width: 5%"><input name="fs2Value"
									class="form-control-customize" type="number" placeholder="" 
									id="fs2Value" required="" title="fs2Value" value="" min="0"
									size="15" tabindex="1"/></td>
								<td style="width: 5%"></td>
							</tr>
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 3%;font-size: 14px;"><label >F3</label></td>
								<td class="form-control-table" style="width: 5%"><input name="fs3Value"
									class="form-control-customize" type="number" placeholder="" 
									id="fs3Value" required="" title="fs3Value" value="" min="0"
									size="15" tabindex="1"/></td>
								<td style="width: 5%"></td>
							</tr>
						</table>
						
						<div class="form-group">
						<input type="submit" value="Update"
							class=" btn btn-primary center-block"
							name="Update" />
					</div>
					</form>
					<div class="clearfix"></div>
					</div>
				</div>
				</div>
		<%@include file="../uni/footer.jsp"%>
</div>
</body>
</html>

