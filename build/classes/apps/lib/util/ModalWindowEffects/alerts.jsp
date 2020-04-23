		<!-- All modals added here for the demo. You would of course just have one, dynamically created -->
		<div class="md-modal md-effect-3" id="modal-3" style="font-family: 'Lato', Calibri, Arial, sans-serif;color: black;">
			<div class="md-content" style="background-image: url('../../images/lightblue_background.jpg')">
				<h3>Machine Alert</h3>
				<div style="background-image: url('../../images/lightblue_background.jpg')">
					<center><p>Are you sure you want move the machine!</p></center>
					<ul>
					<div style="width: 100%;float: left;">
				<div style="width: 50%;float: left;">
				<button class="md-close" onClick="moveMachine();">YES</button>
				</div>
				<div style="width: 50%;float: left;">
					<button class="md-close1" onClick="cancelMoveMachine();">Cancel</button>
				</div>
			</div>
			</ul>
				</div>
			</div>
		</div>
		
				<div class="md-modal md-effect-3" id="modal-delete" style="font-family: 'Lato', Calibri, Arial, sans-serif;color: black;">
			<div class="md-content" style="background-image: url('../../images/lightblue_background.jpg')">
				<h3>Release Machine Alert</h3>
				<div style="background-image: url('../../images/lightblue_background.jpg')">
					<center><p>Are you sure you want release the machine from ShopWork!</p></center>
					<ul>
					<div style="width: 100%;float: left;">
				<div style="width: 50%;float: left;">
				<button class="md-close" onClick="deleteMachine();">OK</button>
				</div>
				<div style="width: 50%;float: left;">
					<button class="md-close1" onClick="close('modal-delete')">Cancel</button>
				</div>
			</div>
			</ul>
				</div>
			</div>
		</div>
		
		<div class="md-modal md-effect-10" id="modal-12" style="font-family: 'Lato', Calibri, Arial, sans-serif;color: black;">
			<div class="md-content" style="background-image: url('../../images/lightblue_background.jpg')">
				<h3>Update Comments</h3>
				<div style="background-image: url('../../images/lightblue_background.jpg')">
					 ADD COMMENTS 
           <center><p>
            <textarea rows="5" cols="75" id="comments" placeholder="Add machine servicing information/comments" ></textarea>
           </p>
            <p align="center"><a href="#" class="livebox">Click Here to see machine maintenance history</a></p>
            </center>
					<ul>
					<div style="width: 100%;float: left;">
				<div style="width: 50%;float: left;">
				<button class="md-close" onClick="updateComments();">Update Comments</button>
				</div>
				<div style="width: 50%;float: left;">
					<button id = "cancelComment" class="md-close" onClick="cancel('modal-12');">Cancel</button>
				</div>
			</div>
			</ul>
				</div>
			</div>
		</div>
		<div class="md-modal md-effect-10" id="uploadInvoiceDiv" style="font-family: 'Lato', Calibri, Arial, sans-serif;color: black;">
			<div class="md-content" style="background-image: url('../../images/lightblue_background.jpg')">
				<h3>Upload Invoice</h3>
				<div style="background-image: url('../../images/lightblue_background.jpg')">
					 Choose invoice file to upload 
           <center><p>
            <input type="file" id="invoicePDF" name="invoicePDF" />
           </p>
            </center>
					<ul>
					<div style="width: 100%;float: left;">
				<div style="width: 50%;float: left;">
					<button class="md-close"  onClick="uploadInvoicePDF();" id="uploadInvoiceButton" name="uploadInvoiceButton" >Upload</button>
				</div>
				<div style="width: 50%;float: left;">
					<button id = "cancelUploadInvoice" class="md-close" onClick="cancel('uploadInvoiceDiv');">Cancel</button>
				</div>
			</div>
			</ul>
				</div>
			</div>
		</div>		
		<div class="md-modal md-effect-10" id="modal-10" style="font-family: 'Lato', Calibri, Arial, sans-serif;color: black;">
			<div class="md-content" style="background-image: url('../../images/lightblue_background.jpg')" >
				<h3>Email Alert</h3>
				<div style="background-image: url('../../images/lightblue_background.jpg')">
					<center><p>Sending Email to Customer</p></center>
					<button class="md-close">OK</button>
				</div>
			</div>
		</div>
		
		<div class="md-modal md-effect-10" id="modal-deleteMsg" style="font-family: 'Lato', Calibri, Arial, sans-serif;color: black;">
			<div class="md-content" style="background-image: url('../../images/lightblue_background.jpg')" >
				<h3>Machine Release Alert</h3>
				<div style="background-image: url('../../images/lightblue_background.jpg')">
					<center><p>Machine Released</p></center>
					<button class="md-close">OK</button>
				</div>
			</div>
		</div>
		
		<div class="md-modal md-effect-10" id="modal-15" style="font-family: 'Lato', Calibri, Arial, sans-serif;color: black;">
			<div class="md-content" style="background-image: url('../../images/lightblue_background.jpg')">
				<h3>Email Alert</h3>
				<div style="background-image: url('../../images/lightblue_background.jpg')">
					<center><p>Do you want to send Email to customer!</p></center>
					<ul>
					<div style="width: 100%;float: left;">
				<div style="width: 50%;float: left;">
				<button class="md-close" onClick="callServerWithEmail();">YES</button>
				</div>
				<div style="width: 50%;float: left;">
					<button id= "email close1" class="md-close1" onClick="callServerWithOutEmail();">NO</button>
				</div>
			</div>
			</ul>
				</div>
			</div>
		</div>
		
				<div class="column" style="display: none;">
					<button id="moveMachine" class="md-trigger" data-modal="modal-3">Machine Alert</button>
					<button id="deleteMachineAlert" class="md-trigger" data-modal="modal-delete">Machine DeleteAlert</button>
					<button id="deleteMachineMsg" class="md-trigger" data-modal="modal-deleteMsg">Machine Deleted Msg</button>
					<button id="emailSent" class="md-trigger" data-modal="modal-10">Email Sent</button>
					<button id="emailAlert" class="md-trigger" data-modal="modal-15">Email Alert</button>
					<button id="updateComments" class="md-trigger" data-modal="modal-12">Update Comments</button>
					<button id="uploadInvoice" class="md-trigger" data-modal="uploadInvoiceDiv">Upload Invoice</button>
				</div>
		<div id="md-overlay" class="md-overlay"></div><!-- the overlay element -->

		<!-- classie.js by @desandro: https://github.com/desandro/classie -->
	<!-- 	<script src="js/classie.js"></script>
		<script src="js/modalEffects.js"></script> -->

		<!-- for the blur effect -->
		<!-- by @derSchepp https://github.com/Schepp/CSS-Filters-Polyfill -->
<!--		<script>
			// this is important for IEs
			var polyfilter_scriptpath = '/js/';
		</script>
 		<script src="js/cssParser.js"></script>
		<script src="js/css-filters-polyfill.js"></script>
-->
