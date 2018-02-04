<%-- 
    Document   : registration-form
    Created on : Jul 25, 2017, 11:14:05 AM
    Author     : Shabab
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration Form</title>
        <%@include file="style-include.jsp" %>
    </head>
    <body>
        <div class="container">
            <form action="/HotelManagement/user/register" method="POST">
                <div class="form-group">
                <label >Full Name:</label>
                <input type="text" name='full_name' class="form-control">
                </div>
                <div class="form-group">
                  <label >Email:</label>
                  <input type="email" name='email' class="form-control" >
                </div>
                <div class="form-group">
                  <label for="pwd">Phone:</label>
                  <input type="text" name='phone' class="form-control" id="pwd">
                </div>
                <div class="form-group">
                  <label>Address:</label>
                  <input type="text" name='address' class="form-control" >
                </div>
                <div class="form-group">
                  <label >Book In Date:</label>
                  <input type="text" name='book_in_date' class="date-picker form-control" >
                </div>
                <div class="form-group">
                  <label >Book Out Date:</label>
                  <input type="text" name='book_out_date' class="date-picker form-control">
                </div>
                
                <button type="submit" class="btn btn-default">Submit</button>
            </form>
            
        </div>
    </body>
    
    <%@include file="script-include.jsp" %>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
</html>
