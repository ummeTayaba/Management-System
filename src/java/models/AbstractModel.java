/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabab
 */
public class AbstractModel {
    
    private Connection connection;
    
    public AbstractModel() {
        makeConnection();
    }
    
    protected Connection getConnection()
    {
        return connection;
    }
    
    protected final void makeConnection()
    {
        if(connection == null)
        {
            try
            {  
                Class.forName("com.mysql.jdbc.Driver");  
                connection = DriverManager.getConnection(DbUtil.CONTEXT,DbUtil.USER,DbUtil.PASS);  

            }
            catch(ClassNotFoundException | SQLException e)
            {
                System.out.println(e);
            } 
        }
    }
    
    protected final void closeDb()
    {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(AbstractModel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
