
	<meta charset="UTF-8">
    <title>Innovation@Anand</title>
    <meta content='width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no' name='viewport'>
    <link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/bootstrap.min.css"/>    
    <link rel="stylesheet" type="text/css" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.3.0/css/font-awesome.min.css"/>
    <link rel="stylesheet" type="text/css" href="http://code.ionicframework.com/ionicons/2.0.0/css/ionicons.min.css"/>    
    <link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/AdminLTE.min.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/AdminLTE.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/bootstrap/css/_all-skins.min.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/plugins/iCheck/flat/blue.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/plugins/morris/morris.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/plugins/jvectormap/jquery-jvectormap-1.2.2.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/plugins/datepicker/datepicker3.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/plugins/daterangepicker/daterangepicker-bs3.css"/>
    <link rel="stylesheet" type="text/css" href="../lib/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.min.css"/>



    <script type="text/javascript" src="../lib/plugins/jQuery/jQuery-2.1.3.min.js"></script>
	<script type="text/javascript" src="http://cdnjs.cloudflare.com/ajax/libs/raphael/2.1.0/raphael-min.js"></script>
    <script type="text/javascript"src="http://code.jquery.com/ui/1.11.2/jquery-ui.min.js"></script>
    <script>
      $.widget.bridge('uibutton', $.ui.button);
    </script>
    <script type="text/javascript" src="../lib/bootstrap/js/bootstrap.min.js" ></script>    

    <script type="text/javascript" src="../lib/plugins/morris/morris.min.js"></script>
    <script type="text/javascript" src="../lib/plugins/sparkline/jquery.sparkline.min.js"></script>
    <script type="text/javascript" src="../lib/plugins/jvectormap/jquery-jvectormap-1.2.2.min.js"></script>
    <script type="text/javascript" src="../lib/plugins/jvectormap/jquery-jvectormap-world-mill-en.js"></script>
    <script type="text/javascript" src="../lib/plugins/knob/jquery.knob.js" type="text/javascript"></script>
    <script type="text/javascript" src="../lib/plugins/daterangepicker/daterangepicker.js"></script>
    <script type="text/javascript" src="../lib/plugins/datepicker/bootstrap-datepicker.js"></script>
    <script type="text/javascript" src="../lib/plugins/bootstrap-wysihtml5/bootstrap3-wysihtml5.all.min.js"></script>
    <script type="text/javascript" src="../lib/plugins/iCheck/icheck.min.js"></script>
    <script type="text/javascript" src="../lib/plugins/slimScroll/jquery.slimscroll.min.js" ></script>
    <script type="text/javascript" src="../lib/plugins/fastclick/fastclick.min.js"></script>
    <script type="text/javascript" src="../lib/bootstrap/js/app.min.js"></script>
    <script type="text/javascript" src="../lib/bootstrap/js/dashboard.js"></script>
    <script type="text/javascript" src="../lib/bootstrap/js/demo.js"></script>
	
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