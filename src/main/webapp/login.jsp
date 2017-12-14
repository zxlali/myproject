<%@page language="java" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<meta charset="utf-8">
<title>Login</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">

<!-- Le styles -->
<script type="text/javascript" src="js/jquery.js"></script>

<style type="text/css">
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #f5f5f5;
}

.form-signin {
	max-width: 360px;
	padding: 19px 29px 29px;
	margin: 0 auto 20px;
	background-color: #fff;
	border: 1px solid #e5e5e5;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
	border-radius: 5px;
	-webkit-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	-moz-box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
	box-shadow: 0 1px 2px rgba(0, 0, 0, .05);
}

.login-container {
  visibility: hidden;
}
</style>
<script type="text/javascript">
	$(function() {
	  var loginPath = "<%=request.getContextPath()%>" + "/login";
	  if (location.pathname !== loginPath) {
		$("body").hide();
		location.pathname = loginPath;
	  } else {
		$(".login-container").css("visibility", "visible")
	  }
	  var mes = '<%= session.getAttribute("SPRING_SECURITY_LAST_EXCEPTION") %>' ;
		console.log(mes)
	  if(mes.match("Bad credentials")){
		$("#loginFailed").html("Username or Password entered is incorrect.");
	  }else if(mes.match("User is disabled")){
		$("#loginFailed").html("Your account is diabled, please contact administrator.");
	  } else if (mes.match("UsernameNotFoundException")){
		  $("#loginFailed").html("UserName is not exist.");
	  }
	  <%session.setAttribute("SPRING_SECURITY_LAST_EXCEPTION", "");%> ;
	});
</script>
</head>

<body>
	<div class="container login-container">
		<form action="/login" method="POST"
			class="form-signin form-horizontal">
			<div class="form-group">
				<h2 class="form-signin-heading">Please Login</h2>
			</div>
			<div class="form-group">
				<input type="text" class="form-control" placeholder="UserName"
					required name="UserName" />
			</div>
			<div class="form-group">
				<input type="password" class="form-control" placeholder="Password"
					required name="Password" />
			</div>
			<div>
				<input id="remember-me" name="remember-me" type="checkbox" checked="checked"/>自动登录
			</div>
			<div class="form-group">
				<button class="btn btn-primary btn-lg" type="submit">Sign
					in</button>
			</div>
			<div class="form-group">
				<span id="loginFailed" style="color: red ; font-size: 15px">
				</span>
			</div>
		</form>
	</div>
	<!-- /container -->
</body>
</html>
