<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="">
<head>	
<%@include file="../uni/loginss.jsp"%>
 </head>
    <body>   
       
        <section id="LogIn" class="sections lightbg" style="height: 100vh;">
            <div class="container center">
			  <div class="wrapper" style="margin-top:12%;">
				<form class="login" action="forgotPassword?method:passwordRecovery" method="post">
					<p class="title">Reset your password</p>
					<input type="text" name="user.userName" placeholder="Username" autofocus/>
					<i class="fa fa-user"></i>
					<input type="text" name="user.emailId" placeholder="Email Id" />
					<i class="fa fa-key"></i>
				
					<input type="submit" value="Reset Password" class="btn btn-primary pull-right"/>
					<div class="clearfix"></div>
				  </form>	
            </div>
            </div>        
        </section>
          <footer id="footer" class="footer colorsbg" >            
			<img src="../images/innovation_footer.jpg" class="img-responcive" 
				alt="Cinque Terre" width="100%" height="100%"></img>
        </footer>     
    </body>
</html>
        
        