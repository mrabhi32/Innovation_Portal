<script type="text/javascript">
$(function() {
	$( "#challengeType" ).attr( "required", "required" );
	$( "#plant" ).attr( "required", "required" );
	$( "#category" ).attr( "required", "required" );
	$( "#priority" ).attr( "required", "required" );
});



submitForm()
{
	var companyName = document.challengeForm.company.val;
	session.setAttribute("COMPANYNAME",companyName);
	document.challengeForm.submit();
	
	}

</script>
<style>

</style>
		
				<form id="challengeForm" name="challengeForm" action="challengeFormAction?method=setPlantsfromcompany" style="height: 100%;">
				<div  class="form-group-custom">
				<table style="width: 100%;">
					<tr  style="width: 100%;">
					
						
						<td class="form-control-table" style="width: 20%"><label >Company</label></td>
						<td class="form-control-table" style="width: 20%"><p style="color: #357ebd;font-weight: bolder;"></p>
					<%-- 	<input name="" type="hidden"  value="<s:property value="companyName"/>" onchange = "getLoad()"/> --%>
						 <s:select name="challenge.company" list="companyNames" id="company" onchange="submitForm()" listValue="companyNames" headerKey="0" headerValue="--Select Company--" />				
						</td>
				</tr>
				</table>
				</div>
				</form>
				
