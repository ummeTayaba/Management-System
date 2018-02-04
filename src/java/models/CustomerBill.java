/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabab
 */
public class CustomerBill extends AbstractModel{
    private int customerId;
    private String description;
    private int amount;

    public CustomerBill(int customerId, String description, int amount) {
        this.customerId = customerId;
        this.description = description;
        this.amount = amount;
    }
    
    public CustomerBill()
    {
        
    }
    
    public ArrayList<HashMap<String, String>> getAll(String id)
    {
        super.getConnection();
        Connection con = getConnection();
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM customer_bill "
                  +  " WHERE customer_id = " + "'" + id + "'");
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
    
    public void addBill()
    {
        super.getConnection();
        Connection con = getConnection();
        try {
            Statement statement = con.createStatement();
            String string = "'" + customerId + "', '" + description + "', '" + amount + "' , " + " NOW()";
            
            statement.execute("INSERT INTO customer_bill(customer_id, description, amount, created_date) "
                    + " VALUES(" + string + ")");
            
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeDb();
        }
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }
    
    
    
}
