<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var dataIds = ["id","challenge","idea","challenger","challengeType","frame","impactValue","impact","investment","implementationTime","priority","ideaStatus"];
	var tableHeaders = ["ID","<div style='padding-left:4em;padding-right:5em;'>CHALLENGE</div>","<div style='padding-left:6em;padding-right:8em;'>IDEA</div>","CHALLENGER","CHALLENGE TYPE","FRAME","IMPACT VALUE","IMPACT","INVESTMENT","IMPLEMENTATION TIME","PRIORITY","STATUS"];
	var datatable;
	function loadTable(jsonData) {
		datatable = createTable(jsonData.gridModel,dataIds,tableHeaders);
		datatable.makeEditable({
			sUpdateURL:"editIdea.action",
			fnOnEdited: function(status)
			{ 	
				//alertify.set({ delay: 30000 });
				//alertify.log(status);
				ajaxServerCall("ChallengeIdeas", "GET",null, loadTable);
			},
			"aoColumns":[
			    null,
			    null,
			    {
			        type:'textarea',
			        onblur:'cancel',
			        submit: 'Update',
			        event:'click',
			        "submitdata" : function(){
			        	return {
			        		"ideaVo.id": document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[0].innerText 
			        		,"ideaVo.idea":document.getElementsByName("value")[0].value
			        	}
			        }
			    },
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    null,
			    null
			]
			});
	}
	$(document).ready(
			function() {
				ajaxServerCall("ChallengeIdeas", "GET",
						null, loadTable);
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
					<li class="active">Idea List</li>
				</ol>			
			</section>
				<br>
			<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Idea List</h3>
				</div>
			</div>
      </div>
	  	<%@include file="../uni/footer.jsp"%>
    </div>
</body>
</html>

