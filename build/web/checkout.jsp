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
            <caption><h1>Checkout</h1></caption>
            <thead>
              <tr>
                <th>Id</th>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Address</th>
                <th>Room Assigned</th>
                <th>In Date</th>
                <th>Out Date</th>
                <th>Action</th>
              </tr>
            </thead>
            
             
        <%
          
            ArrayList<HashMap<String, String>> list = (ArrayList)request.getAttribute("result");
            
            if(list != null)
            {
                for(HashMap<String, String> result: list)
                {   
                    
                    Booking booking = new Booking(Integer.parseInt(result.get("id")), 
                                                (result.get("room_id")), 
                                                (result.get("customer_id")));
                    Customer customer = new Customer(Integer.parseInt(result.get("customer_id")), 
                                                result.get("full_name"), 
                                                result.get("email"), 
                                                result.get("phone"), 
                                                result.get("address"),
                                                result.get("book_in_date"),
                                                result.get("book_out_date"),
                                                Integer.parseInt(result.get("is_paid"))
                                                );
        %>
          
            <tbody>
                <tr>
                    <th><%= booking.getId() %></th>
                    <th><%= customer.getName() %></th>
                    <th><%= customer.getEmail() %></th>
                    <th><%= customer.getPhone() %></th>
                    <th><%= customer.getAddress() %></th>
                    <th>
                    <% 
                        if(booking.getRoom_id() == null)
                            out.print("None");
                        else
                            out.print(booking.getRoom_id());
                    %>
                    
                    </th>
                    <th><%= customer.getBook_in_date().toString()%></th>
                    <th><%= customer.getBook_out_date().toString() %></th>
                    <th><a href="/HotelManagement/admin/user/checkout/bill?id=<%=booking.getId() %>"  class="btn btn-info">Show Bill</a></th>
                    
                </tr>
            </tbody>
        
        <%
                }
                
            }
        %>
        
        
            
        </table>
        
        
    </div>
        
        <!-- /#page-content-wrapper -->

    
    
    
    <!-- /#wrapper -->

    <%@include file="script-include.jsp" %>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

</body>

</html>
