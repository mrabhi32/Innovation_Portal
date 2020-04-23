<meta charset="utf-8">
<meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">

<link rel="apple-touch-icon" href="apple-touch-icon.png"/>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.css"/>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.min.css"/>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap-theme.min.css"/>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/plugins.css" />
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/lato-webfont.css" />
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/magnific-popup.css"/>	
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/responsive.css" />
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/style.css"/>	
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/font-awesome.css"/>
<link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/jquery-ui.css"/>
<link rel="stylesheet" type="text/css" href="../lib/util/Chart.js-master/legend.css"/>
<link rel="stylesheet" type="text/css" href="../lib/util/alertify.js-0.3.10/themes/alertify.core.css"/>
<link rel="stylesheet" type="text/css" href="../lib/util/alertify.js-0.3.10/themes/alertify.blueTheme.css" id="toggleCSS" />    


<script type="text/javascript" src="http://maps.google.com/maps/api/js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/gmaps.min.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/jquery-1.11.2.min.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/bootstrap.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/jquery.magnific-popup.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/plugins.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/main.js"></script>
<script type="text/javascript" src="../lib/bootstrap/js/modernizr-2.8.3-respond-1.4.2.min.js"></script>
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