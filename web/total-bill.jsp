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
    

</head>

<body>

    <div id="wrapper">

        <%@include file="sidebar.jsp" %>
            <!-- Page Content -->
        
        
            
        <table class="table table-hover">
            <caption><h1>Total Bills</h1></caption>
            <thead>
              <tr>
                <th>Description</th>
                <th>Amount</th>
                <th>Date</th>
                
              </tr>
            </thead>
            
             
        <%
            long sum = 0;
            
            ArrayList<HashMap<String, String>> list = (ArrayList)request.getAttribute("result");
            
            if(list != null)
            {
                for(HashMap<String, String> result: list)
                {   
                    
                    
        %>
          
            <tbody>
                <tr>
                    <th><%= result.get("description") %></th>
                    <th><%= result.get("amount") %></th>
                    <th><%= result.get("created_date") %></th>
                    
                </tr>
            </tbody>
        
        <%      
                sum += Integer.parseInt(result.get("amount"));
                }
                
            }
        %>
        
        
            
        </table>
        
         <h3>Total bill is : <b><%= sum %></b></h3>
    </div>
       
        <!-- /#page-content-wrapper -->

    
    
    
    <!-- /#wrapper -->

    <%@include file="script-include.jsp" %>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</body>

</html>
