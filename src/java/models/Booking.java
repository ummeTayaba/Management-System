/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabab
 */
public class Booking extends AbstractModel{
    private int id;
    private Integer room_id;
    private Integer customer_id;
    private boolean isPaid;

    public Booking(int id, String room_id, String customer_id) {
        this.id = id;
        setRoom_id(room_id);
        setCustomer_id(customer_id);
        
    }
    
    public Booking(int id, String room_id, String customer_id, boolean isPaid) {
        this.id = id;
        setRoom_id(room_id);
        setCustomer_id(customer_id);
        this.isPaid = isPaid;
    }
    
    public boolean hasPayment()
    {
        return isPaid;
    }

    public Booking() {
    }
    
    public void registerUser(String name, String email, String phone, String address, String bookIn, String bookOut)
    {
        super.getConnection();SimpleDateFormat format = new SimpleDateFormat("dd/MM/YYYY");
        java.util.Date parsed = null;
        try {
             parsed = (java.util.Date) format.parse(bookIn);
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        Date book_in_date = new Date(parsed.getTime());
        try {
             parsed = (java.util.Date) format.parse(bookOut);
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Date book_out_date = new Date(parsed.getTime());
        
        Connection con = getConnection();
        try {
            
            String build = "'" + name + "'" + ", '" + email + "', '" + phone + "', '" + address + "', '" + book_in_date.toString() + "', '" + book_out_date.toString() + "'";                              
            String query = "INSERT INTO customer(full_name, email, phone, address, book_in_date, book_out_date)"
                    + " VALUES (" + build + ")";
            System.out.println(query);
            PreparedStatement statement = con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.execute();
            
            ResultSet rs = statement.getGeneratedKeys();
            long id = -1;
            if (rs.next()) {
                id= rs.getLong(1);
                System.out.println("Inserted ID -" + id); // display inserted record
            }
            
            String qq = "INSERT INTO booking(customer_id) VALUES('" + id + "')";
            PreparedStatement state = con.prepareStatement(qq, Statement.RETURN_GENERATED_KEYS);
            state.execute();
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void cancel(String id)
    {
        super.getConnection();
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.execute("DELETE FROM booking WHERE customer_id = '" + id + "'");
            
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void assignRoom(String id, String roomId)
    {
        super.getConnection();
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("UPDATE booking SET room_id = '" + roomId + "' WHERE customer_id = '" + id + "'");
            //System.out.println("UPDATE booking SET room_id = '" + roomId + "' WHERE id = '" + id + "'");
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public boolean changeStatus(String id, String status)
    {
        super.getConnection();
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            statement.executeUpdate("UPDATE booking SET is_paid = '" + status + "' WHERE customer_id = '" + id + "'");
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return false;
    }
    
    public ArrayList<HashMap<String, String>> getNullBookings()
    {
        super.getConnection();
        Connection con = getConnection();
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM booking "
                    + "INNER JOIN customer ON booking.customer_id = customer.id "
                    + " WHERE booking.room_id IS null");
            if(result.first())
            {
                
                ResultSetMetaData meta = result.getMetaData();
                int total = meta.getColumnCount();
                do
                {
                    HashMap<String, String> map = new HashMap<>();
                    for(int c = 0; c < total; c++)
                    {
                        
                        map.put(meta.getColumnLabel(c + 1), result.getString(c + 1));
                        //System.out.println(meta.getColumnLabel(c + 1) + "  " + result.getString(c + 1));
                    }
                    mapList.add(map);
                }while(result.next());
            }
            return mapList;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeDb();
            
        }
        return null;
    }
    
    public ArrayList<HashMap<String, String>> getRegisteredBookings()
    {
        super.getConnection();
        Connection con = getConnection();
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM booking "
                    + "INNER JOIN customer ON booking.customer_id = customer.id "
                    + "WHERE booking.is_paid = '1'");
            if(result.first())
            {
                
                ResultSetMetaData meta = result.getMetaData();
                int total = meta.getColumnCount();
                do
                {
                    HashMap<String, String> map = new HashMap<>();
                    for(int c = 0; c < total; c++)
                    {
                        
                        map.put(meta.getColumnLabel(c + 1), result.getString(c + 1));
                        //System.out.println(meta.getColumnLabel(c + 1) + "  " + result.getString(c + 1));
                    }
                    mapList.add(map);
                }while(result.next());
            }
            return mapList;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeDb();
            
        }
        return null;
    }
    
    
    public ArrayList<HashMap<String, String>> getBookings()
    {
        super.getConnection();
        Connection con = getConnection();
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM booking "
                    + "INNER JOIN customer ON booking.customer_id = customer.id");
            if(result.first())
            {
                
                ResultSetMetaData meta = result.getMetaData();
                int total = meta.getColumnCount();
                do
                {
                    HashMap<String, String> map = new HashMap<>();
                    for(int c = 0; c < total; c++)
                    {
                        
                        map.put(meta.getColumnLabel(c + 1), result.getString(c + 1));
                        //System.out.println(meta.getColumnLabel(c + 1) + "  " + result.getString(c + 1));
                    }
                    mapList.add(map);
                }while(result.next());
            }
            return mapList;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeDb();
            
        }
        return null;
    }
    
    public ArrayList<HashMap<String, String>> getBookings(String fromDate, String toDate)
    {
        super.getConnection();
        Connection con = getConnection();
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        Date fr = parseSqlDate(fromDate);
        Date to = parseSqlDate(toDate);
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM booking "
                    + "INNER JOIN customer ON booking.customer_id = customer.id "
                    + "WHERE customer.book_in_date >= '" + fr.toString() + "' AND "
                    + "customer.book_out_date <= '" + to.toString() + "'");
            
            if(result.first())
            {
                
                ResultSetMetaData meta = result.getMetaData();
                int total = meta.getColumnCount();
                do
                {
                    HashMap<String, String> map = new HashMap<>();
                    for(int c = 0; c < total; c++)
                    {
                        
                        map.put(meta.getColumnLabel(c + 1), result.getString(c + 1));
                        //System.out.println(meta.getColumnLabel(c + 1) + "  " + result.getString(c + 1));
                    }
                    mapList.add(map);
                }while(result.next());
            }
            return mapList;
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeDb();
            
        }
        return null;
    }
    
    private Date parseSqlDate(String dt)
    {
        try {
            SimpleDateFormat format = new SimpleDateFormat("MM/dd/yyyy");
            java.util.Date date = (java.util.Date) format.parse(dt);
            Date sqlDate = new Date(date.getTime());
            
            return sqlDate;
        } catch (ParseException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getRoom_id() {
        return room_id;
    }

    public void setRoom_id(String room_id) {
        this.room_id = room_id != null? new Integer(room_id) : null;
    }

    public Integer getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id != null? new Integer(customer_id) : null;
    }
    
    
    
    
    
}
