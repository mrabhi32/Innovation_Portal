
function ajaxServerCall(serverUrl, method, data, callback) {
	// Launch AJAX request.
	var callbacks = $.Callbacks("once");
	callbacks.add( callback );
	$.ajax({
		// The link we are accessing.
		url : serverUrl,
		// The type of request.
		type : method,
		// The type of data that is getting returned.
		//data: data,
		//contentType: "application/json; charset=utf-8",
		dataType : "html",
		beforeSend : function() {
		},
		error : function(jsonData) {
			var strData = $.parseJSON(jsonData);
		},
		complete : function() {
		},
		success : function(jsonData) {
			var strData = $.parseJSON(jsonData);
		    try {
				var actionErrs = (strData.actionErrors); // list of action errors
				var alertMessage="";
				var i=0;
				for(i=0;i<actionErrs.length; i++){
				alertMessage=alertMessage+" "+actionErrs[i];
				alertify.set({ delay: 5000 });
				alertify.error(alertMessage);
				}
			} catch (e) {
			}
			try {
				var actionMsgs= (strData.actionMessages); // list of action messages
				var alertMessage="";
				var i=0;
				for(i=0;i<actionMsgs.length; i++){
				alertMessage=alertMessage+" "+actionMsgs[i];
				alertify.set({ delay: 5000 });
				alertify.success(alertMessage);
				}
			} catch (e) {
			}
			try {
			    var actionFldErrs= (strData.fieldErrors); // list of field errors
			    var alertMessage="";
			    var i=0;
			    for(i=0;i<actionFldErrs.length; i++){
			    alertMessage=alertMessage+" "+actionFldErrs[i];
			    alertify.set({ delay: 5000 });
				alertify.error(alertMessage);
			    }
			} catch (e) {
			}
			
			if(callback != null){
				callbacks.fire(strData);
			}
		}
	});
	// Prevent default click.
	return (false);
}

