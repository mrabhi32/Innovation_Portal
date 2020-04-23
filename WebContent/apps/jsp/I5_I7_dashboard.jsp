	<div class="">
		<div class="main-content">
			<div class="row">
				<div class="col-sm-6 col-md-6">
					<div class="panel panel-default">
						<a href="#widget1container" class="panel-heading"
							
							data-toggle="collapse">PROGRESS OF CHALLENGES </a>
						<div id="widget1container" class="panel-body collapse in">
							<table class="table list" style="margin: 0px;">
								<tbody>
									<tr>
										<td>
											<p class="text-info  pull-left" style="margin-top: 1px;">Total
												Challenges in Bank</p></td>
										<td></td>
										<td>
											<p class="stat text-danger h3 pull-left"
												style="margin-top: 1px;">
												<span class="label label-primary"><s:property
														value="%{chaMatrixVO.totalChaInBank}" /> </span>
										</td>
									</tr>
									<tr>
										<td>
											<p class="text-info  pull-left" style="margin-top: 1px;">Challenges
												being Pursued</p></td>
										<td></td>
										<td>
											<p class="stat text-danger h3 pull-left"
												style="margin-top: 1px;">
												<span class="label label-primary"> <s:property
														value="%{chaMatrixVO.totalChaPersured}" />
											</p>
										</td>
									</tr>
									</span>
									</td>
									</tr>
									<tr>
										<td>
											<p class="text-info  pull-left" style="margin-top: 1px;">Saving
												Potential(From Idea Generation) in Million</p></td>
										<td></td>
										<td>
											<p class="stat text-danger h3 pull-left"
												style="margin-top: 1px;">
												<span class="label label-primary"><s:property
														value="%{chaMatrixVO.savingPotential}" />
														
												</span>
											</p>
										</td>
									</tr>
									<tr>
										<td>
											<p class="text-info  pull-left" style="margin-top: 1px;">Challenge
												Closed</p></td>
										<td></td>
										<td>
											<p class="stat text-info h3 pull-left"
												style="margin-top: 1px;">
												<span class="label label-primary"><s:property
														value="%{chaMatrixVO.totalchaClosed}" /> </span>
										</td>
									</tr>
									<tr>
										<td>
											<p class="text-info  pull-left" style="margin-top: 1px;">Savings
												Actualized Till Date - From Idea Generation in Million</p></td>
										<td></td>
										<td>
											<p class="stat text-danger h3 pull-left"
												style="margin-top: 1px;">
												<span class="label label-primary"> <s:property
														value="%{chaMatrixVO.savingActualized}" />
														
												</span>
											</p><br><br><br><br><br><br><br>
										</td>
									</tr>
								</tbody>
							</table>
						</div>
					</div>
				</div>
				<div class="col-sm-6 col-md-6">
					<div class="panel panel-default">
						<a href="#widget2container" class="panel-heading"
							data-toggle="collapse"
							>PIE
							CHART</a> <select name="employee.user.role" id="role" tabindex=12
							onchange="loadCategoryPie();">
							<option value="potential">Potential</option>
							<option value="catagory">Catagory</option>
						</select>
						<div id="canvas-holder" style="font-size: 18;font-stretch: narrower;">
						<div id="chart-caption" style="font-weight: bolder;margin: -13px;margin-left:170px ;margin-bottom:0px;"></div>
							<div id ="chart">
							<div style="float: left;margin-left: 15px;margin-right:30px;text-align: left" id="pieLegend"></div>
							<canvas id="chart-area" style="float: left; padding-top:10px; text-align: center"  width="254" height="254"/>
							</div>
					</div>
				</div>
			</div>
		</div>
		<div class="row">
			<div class="col-sm-6 col-md-6">
				<div class="panel panel-default">
					<a href="#widget3container" class="panel-heading"
						
						data-toggle="collapse">STRATEGY - PORTFOLIO OF CHALLENGE </a>
					<div id="widget3container" class="panel-body collapse in">
						<table class="table list" style="margin: 0px;">
							<tbody>
								<tr>
									<td>
										<p class="text-info  pull-left"
											style="margin-top: 1px; font-weight: bold; font-size: 1em;">Types
											of Challenges</p></td>
									<td><p class="text-info  pull-left"
											style="margin-top: 1px; font-weight: bold; font-size: 1em;">Pursued</p>
									</td>
									<td>
										<p class="text-info  pull-left"
											style="margin-top: 1px; font-weight: bold; font-size: 1em;">Aspired</p>
									</td>
								</tr>
									<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">Individual
											Orbit-shift</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"> <s:property
													value="%{chaMatrixVO.individualCount}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{chaMatrixVO.individualAspiredCount}" /> </span>
										</p></td>
								</tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">Functional
											Orbit-shift</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"> <s:property
													value="%{chaMatrixVO.functionalCount}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{chaMatrixVO.functionalAspiredCount}" /> </span>
										</p></td>
								</tr>
									<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">
											Organization Orbit-shift</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"> <s:property
													value="%{chaMatrixVO.organisationalCount}"  /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{chaMatrixVO.organisationalAspiredCount}" /> </span>
										</p>
										</td>
								</tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">
											Industry Orbit-shift</p>
									</td>
									<td>
										<p class="stat text-danger h3 " style="margin-top: 1px;">
											<span class="label label-primary"> <s:property
													value="%{chaMatrixVO.industrialCount}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{chaMatrixVO.industrialAspiredCount}" /> </span>
										</p>
										<br><br><br><br><br><br><br>
										</td>
								</tr>
	
							
							
							</tbody>
						</table>
					</div>
				</div>
			</div>
			<div class="col-sm-6 col-md-6">
				<div class="panel panel-default">
					<a href="#widget4container" class="panel-heading"
						
						data-toggle="collapse">PEOPLE CAPABILITY MATRIX </a>
					<div id="widget4container" class="panel-body collapse in">
						<table class="table list" style="margin: 0px;">
							<tbody>
								<tr>
									<td></td>
									<td><p class="text-info  pull-left"
											style="margin-top: 1px; font-weight: bold; font-size: 1em;">Actual</p>
									</td>
									<td>
										<p class="text-info  pull-left"
											style="margin-top: 1px; font-weight: bold; font-size: 1em;">Aspired</p>
									</td>
								</tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">
											Innovators</p>
									</td>
									<td>
										<p class="stat text-danger h3 " style="margin-top: 1px;">
											<span class="label label-primary"> <s:property
													value="%{manpowerEmp.innovators}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{manpowerVO.manpowerCount}" /> </span>
										</p></td>
								</tr>
								<tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">Innovation
											Buddies</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"><s:property
													value="%{manpowerEmp.buddies}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{manpowerVO.manpowerCount}" /> </span>
										</p></td>
								</tr>
								<tr style="background-color: honeydew;">
									 <td>
										<p class="text-info  pull-left" style="margin-top: .5px;">
											</p>
									</td>
									<td>
										<p class="text-info  pull-left" style="margin-top: .5px;">
											</p>
									</td>
									<td>
										<p class="text-info  pull-left" style="margin-top: .5px;">
											</p>
									</td> 
								</tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">Innovation
											Starters</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"> <s:property
													value="%{manpowerEmp.innovattionStarter}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{manpowerVO.manpowerCount}" /> </span>
										</p></td>
								</tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">FS1</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"> <s:property
													value="%{manpowerEmp.fs1}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{manpowerVO.fs1}" /> </span>
										</p></td>
								</tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">FS2</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"><s:property
													value="%{manpowerEmp.fs2}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{manpowerVO.fs2}" /> </span>
										</p></td>
								</tr>
								<tr>
									<td>
										<p class="text-info  pull-left" style="margin-top: 1px;">FS3</p>
									</td>
									<td>
										<p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-primary"><s:property
													value="%{manpowerEmp.fs3}" /> </span>
										</p>
									</td>
									<td><p class="stat text-danger h3 pull-left"
											style="margin-top: 1px;">
											<span class="label label-warning"> <s:property
													value="%{manpowerVO.fs3}" /> </span>
										</p></td>
								</tr>
							</tbody>
						</table>
					</div>
				</div>
			</div>
		</div>

	</div>
	<%@include file="../uni/footer.jsp"%>
	</div>
</body>
</html>