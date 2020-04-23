<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
</head>
<body class="skin-blue">
	<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="wrapper">        
      <div class="content-wrapper">
		<section class="content-header">
				<br><ol class="breadcrumb">
					<li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
					<li class="active">Addmin Tool</li>
					<li class="active">Add Company</li>
				</ol>			
			</section>
				<br>	
		<div class="main-content">
			<div class="panel panel-primary">
						<h3 class="panel-heading">Enter company details</h3>
						<div  class="form-group-custom">
						<form id="companyForm" action="saveCompany.action" method="post" style="">
						<table style="width: 100%;">
							<tr  style="width: 100%;">
								<td class="form-control-table" style="width: 10%;font-size: 14px;"><label >Company Name</label></td>
								<td class="form-control-table" style="width: 10%"><input name="company"
									class="form-control-customize" type="text" placeholder="" 
									id="company" required="" title="company" value=""
									size="25" tabindex="1"/></td>
								<td style="width: 5%"></td>
								<td class="form-control-table" style="width: 10%; font-size: 14px;"><label>Company Description</label></td>
								<td class="form-control-table" style="width: 10%"><input name="companayDesc"
									class="form-control-customize" type="text" placeholder="" 
									id="companayDesc" required="" title="companay Desc" value=""
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

