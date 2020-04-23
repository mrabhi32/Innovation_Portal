<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
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
					<li class="active">Add Plant</li>
				</ol>			
			</section>
				<br>
		<div class="main-content">
			<div class="panel panel-primary">
						<h3 class="panel-heading">Enter Plant details</h3>
						<div  class="form-group-custom">
						<form id="savePlant" action="savePlant" method="post">
						<div  class="form-group-custom">
						<table style="width: 100%;">
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 20%"><label>Select Company</label></td>
								<td class="form-control-table" style="width: 40%"><table style="width: 100%;margin-top: 0px;"><s:select headerKey="" headerValue="Select Company"
							cssClass="form-control-customize" list="objCompanyGroup" style="width:40%;"
							name="company" value="" id="company" required=""/></table>
									</td>
							</tr>
						</table>
						</div>
						<table style="width: 100%;">
							<tr  style="width: 100%;"><td></br></br></td>
							</tr>
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 10%;font-size: 14px;"><label >Enter Plant</label></td>
								<td class="form-control-table" style="width: 10%"><input name="plant"
									class="form-control-customize" type="text" placeholder="" 
									id="plant" required="" title="plant" value=""
									size="25" tabindex="1"/></td>
								<td style="width: 5%"></td>
								<td class="form-control-table" style="width: 10%; font-size: 14px;"><label>Plant Description</label></td>
								<td class="form-control-table" style="width: 10%"><input name="plantDesc"
									class="form-control-customize" type="text" placeholder="" 
									id="plantDesc" required="" title="plantDesc" value=""
									size="25" tabindex="2"/></td>
									<td style="width: 5%"></td>
							</tr>
						</table>
						
						<div class="form-group">
						<input type="submit" value="Create"
							class=" btn btn-primary center-block"
							name="" />
					</div>
					<div class="clearfix"></div>
					</form>
					</div>
				</div>
			</div>
			</div>
				<%@include file="../uni/footer.jsp"%>
		</div>
</body>
</html>

