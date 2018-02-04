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
            <caption><h1>Bookings</h1></caption>
            <thead>
              <tr>
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Payment Status</th>
                <th>In Date</th>
                <th>Out Date</th>
                <th>Status Select</th>
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
                                                (result.get("customer_id")),
                                                (result.get("is_paid")).equals("0")? false : true);
                    
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
                <form action="/HotelManagement/admin/user/payment" method="POST">
                <tr>
                    <th><%= customer.getName() %></th>
                    <th><%= customer.getEmail() %></th>
                    <th><%= customer.getPhone() %></th>
                    <th>
                    <% 
                        if(booking.hasPayment())
                            out.print("Paid");
                        else
                            out.print("Not paid");
                    %>
                    
                    </th>
                    <th><%= customer.getBook_in_date().toString()%></th>
                    <th><%= customer.getBook_out_date().toString() %></th>
                    
                        <th>
                            <select class="form-control" name="status">
                                <option value="1">Paid</option>
                                <option value="0">Not Paid</option>
                            </select>
                        </th>
                        <th>
                            <input type="hidden" value="<%= booking.getId() %>" name="id" />
                            <button type="submit" class="btn btn-danger">Change Status</button>
                        </th>
                    
                </tr>
                </form>
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
