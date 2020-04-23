<script type="text/javascript">
$(function() {
	$( "#challengeType" ).attr( "required", "required" );
	$( "#plant" ).attr( "required", "required" );
	$( "#category" ).attr( "required", "required" );
	$( "#priority" ).attr( "required", "required" );
});

</script>

<form id="challengeForm" name="challengeForm"
	action="challengeFormAction" style="height: 100%;">
	<div class="form-group">
		<br />
		<br /> <label style="text-align: left;">Select the Company and
			Plant Combination</label> <select Class="form-control-customize"
			style="width: 300px;" name="challenge.company_plant"
			required="required" list="companyPlant" id="company" headerKey="0"
			headerValue="Select Company Plant Combination" />

	</div>
	<div class="form-group">
		<label style="text-align: left;">CHALLENGE - Crystalize the
			Challenge in 4W1H Language of Report</label>
		<textarea name="challenge.challenge" class="form-control span12"
			required="required" style="width: 100%;" placeholder="" rows="5"
			id="additionalinformation"></textarea>
	</div>
	<div class="form-group-custom">

		<label>Challenge Type</label> <select Class="form-control-customize"
			list="challengeTypeList" style="width: 200px;"
			headerValue="Select Challenge Type" headerKey="" required="required"
			id="challengeType" name="challenge.challenge_type"
			value="defaultChallengeType" /> <br />
		<br />
		<br />
		<br /> <label>Category</label> <select Class="form-control-customize"
			list="categoryList" style="width: 200px;"
			headerValue="Select Category" headerKey="" required="required"
			id="category" name="challenge.challenge_category"
			value="defaultCategory" /> <br />
		<br />
		<br />
		<br /> <label>Quantitative Measure of Success(In Millions)</label> <input
			style="width: 200px;" id="quantmeassuccess"
			name="challenge.quant_meas_success" class="form-control-customize"
			required="required" type="text" min="0" placeholder="" value=""
			size=""> <br />
		<br />
		<br />
		<br /> <label>Priority</label> <select Class="form-control-customize"
			list="priorityList" style="width: 200px;"
			headerValue="Select Priority" headerKey="" required="required"
			id="priority" name="challenge.priority" value="defaultPriority" />

		<!-- <p class="actionmsg" id="errorMsg" style="color: red; align: center;"/> -->
	</div>
	<div class="form-group">
		<input type="submit" name="method:submitChallenge"
			class=" btn btn-primary center-block" value="Create"
			onclick="javascript:validation();" />
	</div>
	<div class="clearfix"></div>
</form>