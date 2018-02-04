<%@page import="java.util.ArrayList"%>
<%@page import="java.lang.String"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="models.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<html lang="en">

<head>

    <title>Admin|Home</title>
    <%@include file="style-include.jsp" %>
    <link href="/HotelManagement/css/back.css" rel="stylesheet">
    <style>
        form
        {
            margin-left: 10px;
            margin-right: 10px;
        }
    </style>

</head>

<body>

    <div id="wrapper">

        <%@include file="sidebar.jsp" %>
        <!-- Page Content -->
        
        <form action="/HotelManagement/admin/user/add-bill" method="POST">
        <div class="form-group">
          <label for="desc">Description:</label>
          <input type='text' name="desc" id="desc" class="form-control" required/>
              
        </div>
        <div class="form-group">
          <label for="cat">Amount:</label>
          <input type='number' name="amount" id="amount" class="form-control" required />
        </div>
        <input type='hidden' name="id" value="<%= request.getParameter("id") %>" />
        <button type="submit" class="btn btn-success">Add Bill</button>
      </form>
        
        
        
    </div>
        
        <!-- /#page-content-wrapper -->

    
    
    
    <!-- /#wrapper -->

    <%@include file="script-include.jsp" %>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</body>

</html>
