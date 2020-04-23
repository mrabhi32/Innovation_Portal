<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html class="no-js" lang="">
<head>	
<%@include file="../uni/loginss.jsp"%>
 </head>
    <body>   
       <div class="container center">
			  <div class="wrapper" style="margin-top:12%;">
			<form action="changePasswordAction.action" method="post" >
				<div class="form-group">
					<label>Password</label> <input name="user.userCredentials.password"
						class="form-control span12" type="text" tabindex=2
						placeholder="" id="oldPassword" required="" title="password"
						value="" size="35">

				</div>
				<div class="form-group">
					<label>New Password</label> <input type="password"
						name="user.userCredentials.newPassword"
						class="form-control span12">
				</div>
				<div class="form-group">
					<label>Confirm New Password</label> <input type="password"
						name="user.userCredentials.comfirmPassword"
						class="form-control span12">
				</div>
				<input type="submit" class="btn btn-primary center-block" style="margin-bottom: 1em;" name="method:changePassword" value="Save" />

				<div class="clearfix"></div>
			</form></div></div>
			</section>
          <footer id="footer" class="footer colorsbg" >            
			<img src="../images/innovation_footer.jpg" class="img-responcive" 
				alt="Cinque Terre" width="100%" height="100%"></img>
        </footer>     
    </body>
</html>