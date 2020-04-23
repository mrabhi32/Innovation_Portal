
<head>

<link rel="stylesheet" type="text/css"
	href="../lib/report/datatable/css/jquery.dataTables.min.css">
	
<link rel="stylesheet" type="text/css"
	href="../lib/report/datatable/css/dataTableTheme.css">

<link rel="stylesheet" type="text/css"
	href="../lib/report/datatable/css/complete.css">
<link rel="stylesheet" type="text/css"
	href="../lib/report/datatable/css/dataTables.keyTable.min.css">
		<link rel="stylesheet" type="text/css"
	href="../lib/report/datatable/css/dataTables.tableTools.min.css">
	<link rel="stylesheet" type="text/css"
	href="../lib/report/datatable/css/dataTables.colVis.min.css">

<script src="../lib/util/ajaxSupport/ajaxSupport.js"></script>
<script src="../lib/report/json-to-table.js"></script>
<!-- <script
	src="../../core/resource/lib/report/datatable/js/dataTables.bootstrap.js">
	</script> -->
<script
	src="../lib/report/datatable/js/jquery.dataTables.js"></script>
	<script
	src="../lib/report/datatable/js/dataTables.tableTools.min.js"></script>
	<script
	src="../lib/report/datatable/js/dataTables.keyTable.min.js"></script>
	<script
	src="../lib/report/datatable/js/dataTables.colVis.min.js"></script>
		<script
	src="../lib/report/datatable/js/jquery.jeditable.js"></script>
		<script
	src="../lib/report/datatable/js/jquery.dataTables.editable.js"></script>
	
	<style type="text/css">
	#tableBody {
	  font-family: Arial;
	  #font-size: 9px!important;
	  font-weight: normal!important;
	}
	</style>
<script>
	//reduce is not supported in IE hence add below code to support this.
	var rowData;
	if ('function' !== typeof Array.prototype.reduce) {
		Array.prototype.reduce = function(callback /*, initialValue*/) {
			'use strict';
			if (null === this || 'undefined' === typeof this) {
				throw new TypeError(
						'Array.prototype.reduce called on null or undefined');
			}
			if ('function' !== typeof callback) {
				throw new TypeError(callback + ' is not a function');
			}
			var t = Object(this), len = t.length >>> 0, k = 0, value;
			if (arguments.length >= 2) {
				value = arguments[1];
			} else {
				while (k < len && !k in t)
					k++;
				if (k >= len)
					throw new TypeError(
							'Reduce of empty array with no initial value');
				value = t[k++];
			}
			for (; k < len; k++) {
				if (k in t) {
					value = callback(value, t[k], k, t);
				}
			}
			return value;
		};
	}

	function reorder(order, obj) {
		typeof order === 'string' && (order = order.split(/\s*,\s*/));
		return order.reduce(function(rslt, prop) {
			rslt[prop] = obj[prop];
			return rslt;
		}, {});
	}

	function createTable(jsonData,tableConfig,tableHeaders) {
		var L = jsonData.length;
		for ( var i = 0; i < L; i++) {
			var obj = jsonData[i];
			for ( var j in obj) {
				if ($.inArray(j, tableConfig) == -1) {
					delete obj[j];
				}
			}
			obj = reorder(tableConfig, obj);
			// convert json object into string
			var jsonString = JSON.stringify(obj);
			
			for(var k = 0; k< tableConfig.length;k++){
			    jsonString = jsonString.replace(tableConfig[k], tableHeaders[k]);
			}
			obj = jQuery.parseJSON(jsonString);

			jsonData[i] = obj;
		}

		var jsonHtmlTable = ConvertJsonToTable(jsonData, 'jsonTable', null,
		'linkText');
		
		$('#tableBody').html(jsonHtmlTable);
		 var table = $('#jsonTable').dataTable({
			stateSave : true,
			"scrollX" : true,
			"dom" : '<"top"<"clear">T<"clear">Cfl>rt<"bottom"ip<"clear">>',
			"pagingType" : "full_numbers",
			 "tableTools": {
			        "sSwfPath": "../../core/resource/lib/report/datatable/export/copy_csv_xls_pdf.swf",
		            "aButtons": [
		                "copy",
		                "print",
		                {
		                    "sExtends":    "collection",
		                    "sButtonText": "Save",
		                    "aButtons":    [ "csv", "xls", "pdf" ]
		                }
		            ]
		        }
		}); 
		//select table row and edit whole row

	    $('#jsonTable tbody').on( 'click', 'tr', function () {
	        if ( $(this).hasClass('selected') ) {
	            $(this).removeClass('selected');
	        }
	        else {
	            table.$('tr.selected').removeClass('selected');
	            $(this).addClass('selected');
	            rowData = table.fnGetData(this);
	        }
	    } );

	
	    new $.fn.dataTable.KeyTable(table);
	    return table;
	}
	
</script>
</head>

<div id="tableBody"></div>
