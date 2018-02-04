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
        <form action="/HotelManagement/admin/user/room" method="POST">
        <div class="form-group">
          <label for="desc">Description:</label>
          <textarea name="desc" id="desc" class="form-control" rows="5" cols="10">
              
          </textarea>
        </div>
        <div class="form-group">
          <label for="cat">Category:</label>
          <select name="cat" id="cat" class="form-control">
            <%
              ArrayList<HashMap<String, String>> mapList = (ArrayList)request.getAttribute("result");
              for(HashMap<String, String> map : mapList)
              {
            %>
            <option value="<%= map.get("id")%>"><%= map.get("r_type") %></option>
            <%
              }
            
            %>      
          </select>
        </div>
        
        <button type="submit" class="btn btn-success">Add Room</button>
      </form>
        
        
        
    </div>
        
        <!-- /#page-content-wrapper -->

    
    
    
    <!-- /#wrapper -->

    <%@include file="script-include.jsp" %>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</body>

</html>
