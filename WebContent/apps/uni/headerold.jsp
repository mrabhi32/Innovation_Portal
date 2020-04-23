
<%@ taglib prefix="s" uri="/struts-tags"%>
<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">
<%-- <%@ taglib prefix="s" uri="/struts-tags" %> --%>
<!--  -->
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/font-awesome.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/theme.css">
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/jquery-ui.css">
<link rel="stylesheet" type="text/css" href="../lib/util/alertify.js-0.3.10/themes/alertify.core.css">
<link rel="stylesheet" type="text/css" href="../lib/util/alertify.js-0.3.10/themes/alertify.blueTheme.css" id="toggleCSS" />
<link rel="stylesheet" type="text/css" href="../lib/util/Chart.js-master/legend.css">


<script type="text/javascript" src="../lib/bootstrap/js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/jquery-ui.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="../lib/util/ajaxSupport/ajaxSupport.js"></script>
<script type="text/javascript" src="../lib/util/Chart.js-master/Chart.js"></script>
<script type="text/javascript" src="../lib/util/Chart.js-master/legend.js"></script>
<script type="text/javascript" src="../lib/util/jStorage/jstorage.min.js"></script>
<script type="text/javascript" src="../lib/util/alertify.js-0.3.10/lib/alertify.min.js"></script>


<script type="text/javascript">
        $(function() {
            var match = document.cookie.match(new RegExp('color=([^;]+)'));
            if(match) var color = match[1];
            if(color) {
                $('body').removeClass(function (index, css) {
                    return (css.match (/\btheme-\S+/g) || []).join(' ')
                })
                $('body').addClass('theme-' + color);
            }

            $('[data-popover="true"]').popover({html: true});
            
        });
</script>
<script type="text/javascript">
            $("[rel=tooltip]").tooltip();
            $(function() {
                $('.demo-cancel-click').click(function(){return false;});
            });
</script>
<script type="text/javascript">
        $(function() {
            var uls = $('.sidebar-nav > ul > *').clone();
            uls.addClass('visible-xs');
            $('#main-menu').append(uls.clone());
        });
    </script>
<%@include file="../jsp/messages.jsp"%>