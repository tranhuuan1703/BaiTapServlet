<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" 
		rel="stylesheet" 
		integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
		crossorigin="anonymous">
<link href="<c:url value="/css/base.css"/>" rel="stylesheet" type="text/css" />
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" 
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" 
		crossorigin="anonymous"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.0/jquery.min.js"></script>
</head>

<body>
	
	<%
		
		String userNameError = request.getAttribute("message_userName")+"";
		String passWordError = request.getAttribute("message_passWord")+"";
		
		String userName = request.getAttribute("userName")+"";
		String passWord = request.getAttribute("passWord")+"";
		
		userNameError = (!userNameError.equals("null"))? userNameError: "";
		passWordError = (!passWordError.equals("null"))? passWordError: "";
		
		userName = (!userName.equals("null"))? userName: "";
		passWord = (!passWord.equals("null"))? passWord: "";
			
		
	%>
	
	
	
	<div class="container border bg-white" style="margin-top:250px; width:500px; border-radius: 10px;">
		 <div class="w-md-50 mx-auto px-10 px-md-0 py-10">
		 
		 	<div class="mb-10">
                <a class="d-inline-block d-lg-none mb-10" href="/pages/dashboard.html">
                    <img src="../../img/logos/logo-dark.svg" class="h-rem-10" alt="...">
                </a>
                <h1 class="ls-tight fw-bolder h3">Sign in to your account</h1>
                <div class="mt-3 text-sm text-muted"><span>Don't have an account?</span>
                    <a href="/pages/register.html" class="fw-semibold">Sign up</a> for a free trial.
                </div>
            </div>
		
		
		<form action="login" method="post">
		  <!-- message div-->
		  <div class="mb-3">
		    <label for="exampleInputEmail1" class="form-label">User Name</label>
		    <input type="text" class="form-control" id="exampleInputEmail1" aria-describedby="emailHelp" name="email" value="<%= userName %>" required>
		    <span class="rq"><%= userNameError %></span>
 
		  </div>
		  
		  <div class="mb-3">
		    <label for="exampleInputPassword1" class="form-label">Password</label>
		    <input type="password" class="form-control" id="exampleInputPassword1" name="password" value="<%= passWord %>" required>
		    <span class="rq"><%= passWordError %></span>
		  </div>
		  
		  <div class="mb-3 form-check">
		    <input type="checkbox" class="form-check-input" id="exampleCheck1" name="rememberMe">
		    <label class="form-check-label" for="exampleCheck1" >Remember me!</label>
		  </div>
		  <div class="grid text-center" style="padding:20px;">
		  	<button type="submit" class="btn btn-dark w-100">Submit</button>
		  </div>
		  
		</form>
		
		 
		 </div>
		
		
	</div>
	

	
</body>

</html>