<script type="text/javascript">
$(function() {
	var companyName = "";
	companyName = document.getElementById("companyName").value;
	//alert(companyName);
	if(document.getElementById("companyName").value != ""){
		//companyName = "ANAND";
		$.jStorage.set("companyName", companyName);
	}
	//alert(companyName);
	//alert($.jStorage.get("companyName"));
	document.getElementById("company").innerHTML  = $.jStorage.get("companyName");
});
</script>
      <header class="main-header">
        <!-- Logo -->
        <a href="" class="logo"><b>Innovation@Anand</b></a>
        <!-- Header Navbar: style can be found in header.less -->
        <nav class="navbar navbar-static-top" role="navigation">
          <!-- Sidebar toggle button-->
          <a href="#" class="sidebar-toggle" data-toggle="offcanvas" role="button">
            <span class="sr-only">Toggle navigation</span>
          </a>
          <div class="navbar-custom-menu">
            <ul class="nav navbar-nav">
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
    			<span class="hidden-xs">Admin Panel</span>
                </a>  
                <ul class="dropdown-menu">
        		<li><a href="setupCompany">Add Plant</a></li>
                <li><a href="setupPlant">Add Company</a></li>
                <li><a href="setupAspireValues">Save Aspire Value</a></li>
                </ul>       
              </li>
              <li class="dropdown user user-menu">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
    			<span class="hidden-xs">My Profile</span>
                </a>
                <ul class="dropdown-menu">
                  <li class="user-header">
                    <p> Abhishek Gupta<small>Member since Nov. 2012</small></p>
                    <p> Anand Automotive Pvt. Ltd<p>
                    <p> Anchor<p>
                  </li>
                  <li class="user-footer">
                    <div class="pull-left">
                      <a href="changePassword.jsp" class="btn btn-default btn-flat">Change Password</a>
                    </div>	
                    <div class="pull-right">
                      <a href="login.jsp" class="btn btn-default btn-flat">Sign out</a>
                    </div>			
                  </li>
                </ul>
              </li>
            </ul>
          </div>
        </nav>
      </header>