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
            <caption><h1>Un-approved Bookings</h1></caption>
            <thead>
              <tr>
                
                <th>Full Name</th>
                <th>Email</th>
                <th>Phone</th>
                <th>Payment Status</th>
                <th>Room Options</th>
                <th>In Date</th>
                <th>Out Date</th>
                <th>Action</th>
              </tr>
            </thead>
            
             
        <%
          
            ArrayList<HashMap<String, String>> list = (ArrayList)request.getAttribute("result");
            ArrayList<HashMap<String, String>> roomList = (ArrayList)request.getAttribute("rooms");
            
            if(list != null && roomList != null)
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
            <form>
                <tr>
                    <td><%= customer.getName() %></td>
                    <td><%= customer.getEmail() %></td>
                    <td><%= customer.getPhone() %></td>
                    <td><%= customer.getPaymentStatus() %></td>
                    
                    <td>
                        <select class="form-control" name="room_id">    
                    <% 
                        for(HashMap<String, String> room : roomList)
                        {
                            out.println("<option value=" + room.get("real_id") +">" + 
                                        room.get("real_id") + "(" + room.get("r_type") + ")"
                                    +   "</option>") ;
                        }
                    %>
                        </select>
                    </td>
                    <td><%= customer.getBook_in_date().toString()%></td>
                    <td><%= customer.getBook_out_date().toString() %></td>
                    <input type='hidden' name='id' value='<%= booking.getId() %>'/>
                    <td><button type="submit" class="btn btn-success">Assign Room</button></td>
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
