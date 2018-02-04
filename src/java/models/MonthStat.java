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
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Shabab
 */
@XmlRootElement
public class MonthStat extends AbstractModel{
    private String month;
    private int total;
    
    public MonthStat()
    {
    }

    public MonthStat(String month, int total) {
        this.month = month;
        this.total = total;
    }
    
    
    
    public String getMonth() {
        return month;
    }
    
    public ArrayList<Month> getAll()
    {
        super.getConnection();
        Connection con = getConnection();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT "
                    + "months.id, COUNT(customer.id) AS total,"
                    + " months.month_name FROM customer RIGHT JOIN months "
                    + "ON MONTH(book_out_date) = months.id "
                    + "GROUP BY months.month_name,months.id "
                    + "ORDER BY months.id, total, months.month_name ASC");
            if(result.first())
            {
                ArrayList<Month> list = new ArrayList<>();
                
                do
                {
                    list.add(new Month(result.getString("month_name"), result.getInt("total")));
                    
                }while(result.next());
                
                return list;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(Booking.class.getName()).log(Level.SEVERE, null, ex);
        }
        finally
        {
            closeDb();
            
        }
        return null;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }
    
    
    
}

