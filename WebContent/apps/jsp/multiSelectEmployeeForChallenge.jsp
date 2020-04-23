<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@include file="../uni/admiltess.jsp"%>
<script type="text/javascript">
	var dataIds = [ "serial_no","challenge_id", "company_name", "plant_name","challenge_type", "challenge_category", "challenge","priority","quant_meas_success","challenge_status" ];
	var tableHeaders = [ "S.No.","Challenge ID","Company Name","Plant Name","Challenge Type","Challenge Category","Challenge","Priority","Quantitative Measure of Success","Status"];
	
	function refreshGrid(){
		ajaxServerCall("ReportAction?method:getOpenChallenges", "GET",null, loadTable);
	}
	function loadTable(jsonData) {
		var datatable = createTable(jsonData.gridModel, dataIds, tableHeaders);
		datatable.makeEditable({
			//sUpdateURL:"EditChallengeStatus.action",
			sUpdateURL:function(value, settings)
            {
				var id = document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[0].innerText;
        		var status = document.getElementsByName("value")[0].value;
			    ajaxServerCall("EditChallengeStatus.action?challengeVo.id="+id+"&challengeVo.status="+status, "GET",null, refreshGrid);
            },
			fnOnEdited: function(status)
			{ 	
				
			},
			
			"aoColumns":[
			    null,
			    null,
			    null,
			    null,
			    null,
			    {
			        type:'select',
			        onblur:'cancel',
			        submit: 'Save',
			        data : "{'':'Please select...','Pursued':'Pursue'}",
			        event:'click',
			        "submitdata" : function(){
			        	/* return {
			        		"challengeVo.id": document.getElementById("jsonTable").rows[$(this).parent().index()+1].cells[0].innerText 
			        		,"challengeVo.status":"Pursued"
			        		} */
			        }

			    }
			]
			});
	}
	$(document).ready(
			function() {
				ajaxServerCall("ReportAction?method:getOpenChallenges", "GET",
						null, loadTable);
			});
</script>
</head>
<body class="skin-blue">
<%@include file="../uni/userConfig.jsp"%>
	<%@include file="../uni/sideNavigator.jsp"%>
	<div class="content">
	
	  <div class="wrapper">
    <div class="content-wrapper">
        <!-- Content Header (Page header) -->
        <section class="content-header">
          <ol class="breadcrumb">
            <br><li><a href="dashboard.jsp"><i class="fa fa-dashboard"></i> Home</a></li>
            <li class="active">Dashboard</li>
			<li class="active">Pick Challenge</li>
          </ol>
        </section>
		<br>
		<div class="main-content">
			<div class="panel panel-primary">
				<h3 class="panel-heading">Pick Challenge </h3>	
				<div class="container">
					<div class="form-group">
						<label for="coachId" class="col-sm-3 control-label">Select Coach </label>
							<div class="col-sm-5">
                                 <select id="sc" name="coachId" class="form-control" onchange="selectCoach(coachId)">
                                     <option value="-1">Select</option>
                                     <option value="2">coach coach</option><option value="25">Amitabh  Srivastava</option><option value="26">Anand  Sontakke</option><option value="27">Atul  Jaggi</option><option value="28">Kawal  Jaggi</option><option value="29">MS  Shankar</option><option value="30">Mahendra  Goyal</option><option value="31">Maya  Chaudhari</option><option value="32">Pooja  Malik</option><option value="33">Raghu  Muttige</option><option value="34">Rajendra  Abhange</option><option value="35">Ramneek  Jain</option><option value="36">Sampada  Inamdar</option><option value="37">V  Madhavan</option><option value="38">Sumit  Bhatnagar</option><option value="39">KS  Bhullar</option><option value="40">R  Vasudevan</option><option value="41">Ravi  Sinha</option><option value="82">Umesh Shah</option><option value="83">Rajesh Kakkar</option><option value="84">Rajeev Gera</option><option value="86">Arul Kumar</option><option value="57">P Arul Kumar</option><option value="42">Somnath Adhya</option>
                                 </select>                                
							</div>
                   </div>
                </div>
					<div class="jumbotron" >
						<div class="row">
							<form>
							  <fieldset>
								<div class="col-sm-3" style="margin-left:21%;">
								<select name="selectfrom" id="select-from" multiple size="5">
								  <option value="1">Item 1</option>
								  <option value="2">Item 2</option>
								  <option value="3">Item 3</option>
								  <option value="4">Item 4</option>
								</select>
								</div>
								<div class="col-sm-3" style="margin-left:-12%;">
								<a href="JavaScript:void(0);" id="btn-add" >Add &raquo;</a>
								</div>
								<div class="col-sm-3" style="margin-right:-12%;">
								<a href="JavaScript:void(0);" id="btn-remove">&laquo; Remove</a>
								</div>
								<div class="col-sm-3">
								<select name="selectto" id="select-to" multiple size="5">
								  <option value="5">Item 5</option>
								  <option value="6">Item 6</option>
								  <option value="7">Item 7</option>
								</select>
								</div> 					
							  </fieldset>
							</form>	
						</div>
					</div>					
	     <!-- /PAGE CONTENT  -->
            </div>					
		</div>
	</div><!-- /.content-wrapper -->			
    </div><!-- ./wrapper -->	   
    <!-- jQuery 2.1.3 -->
    <script src="plugins/jQuery/jQuery-2.1.3.min.js"></script>
			<script>
			$(document).ready(function() {
			 
				$('#btn-add').click(function(){
					$('#select-from option:selected').each( function() {
							$('#select-to').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
						$(this).remove();
					});
				});
				$('#btn-remove').click(function(){
					$('#select-to option:selected').each( function() {
						$('#select-from').append("<option value='"+$(this).val()+"'>"+$(this).text()+"</option>");
						$(this).remove();
					});
				});
			 
			});
		</script>
	
	<form id="pickchallenge" action="getChallengesToChooseTeam.action" method="post" enctype="multipart/form-data">
	
		<div class="main-content">
			<div class="panel panel-default">
			<a href="#widget1container" class="panel-heading"
					data-toggle="collapse">Pick Challenge </a>
					
				<%@include file="report.jsp"%>
				
			</div>
			
			
			    <p align=center><b>Pick a Challenge from the Below displayed Challenge and Click on the Below Button to Pursue the challenge.</b></p> 
				<p align=center><s:select headerKey="" headerValue="Select Challenge"
							cssClass="form-control-customize" list="UserchallengeList" style="width:65%;" tabindex="7" required="required" title="Select Challenge" 
							id = "challengeId" name="challenge.challenge_id"/></p>
				
				<p align=center><input type="button" onclick="submit();" value="Pick challenge"/></p>
				
			<!-- container -->
		</div>
		</form>
			<%@include file="../uni/footer.jsp"%>
	</div>
</body>
</html>

