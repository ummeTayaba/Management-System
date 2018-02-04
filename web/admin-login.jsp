<%-- 
    Document   : admin-login
    Created on : Jul 12, 2017, 9:40:49 PM
    Author     : Shabab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- Latest compiled and minified CSS -->
        <title>Admin|Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- jQuery library -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

        <!-- Latest compiled JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    
        <link type="text/css" rel="stylesheet" href="/HotelManagement/css/login.css" />
        
    </head>
    <body>
        <div class="container">
  
  <div class="row" id="pwd-container">
    <div class="col-md-4"></div>
    
    <div class="col-md-4">
      <section class="login-form">
        <form method="post" action="/HotelManagement/admin/login" role="login">
          <img src="http://i.imgur.com/RcmcLv4.png" class="img-responsive" alt="" />
          <input type="email" name="email" name="email" placeholder="Email" required class="form-control input-lg" value="" />
          
          <input type="password" name="password" class="form-control input-lg" id="password" placeholder="Password" required="" />
          
          
          <div class="pwstrength_viewport_progress"></div>
          
          
          <button type="submit" name="go" class="btn btn-lg btn-primary btn-block">Sign in</button>
          
          
        </form>
        
        
      </section>  
      </div>
      
      <div class="col-md-4"></div>
      

  </div>
  
     
  
  
</div>
        
        <script src="/HotelManagement/js/login.js" />
    </body>
</html>
