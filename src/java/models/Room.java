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
public class Room extends AbstractModel{
    private int id;
    private Integer category;
    private String description;

    public Room(int id, Integer category, String description) {
        this.id = id;
        this.category = category;
        this.description = description;
    }

    public Room() {
    }
    
    public ArrayList<HashMap<String, String>> getAll()
    {
        super.getConnection();
        Connection con = getConnection();
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT *, room.id AS real_id FROM room INNER join room_cat "
                    + "ON "
                    + "room.cat_id = room_cat.id");
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
    
    public boolean save()
    {
        try {
            super.getConnection();
            Connection con = getConnection();
            Statement statement = con.createStatement();
            
            if(!statement.execute("INSERT INTO room(description, cat_id) "
                    + "VALUES('" + description + "', '" + category + "') "))
            {
                System.out.println("got it");
                return true;
            }
                    
        } catch (SQLException ex) {
            Logger.getLogger(Room.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            closeDb();
        }
        System.out.println("not got it");
        return false;
    }
    
    public ArrayList<HashMap<String, String>> getCategories()
    {
        super.getConnection();
        Connection con = getConnection();
        ArrayList<HashMap<String, String>> mapList = new ArrayList<>();
        
        try {
            Statement statement = con.createStatement();
            ResultSet result = statement.executeQuery("SELECT * FROM room_cat");
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
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
