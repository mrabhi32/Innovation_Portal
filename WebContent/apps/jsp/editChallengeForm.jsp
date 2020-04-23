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
