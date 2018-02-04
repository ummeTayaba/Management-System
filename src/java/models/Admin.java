/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabab
 */
public class Admin extends AbstractModel{
    private String email;
    private String password;

    public Admin(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    public boolean isValid()
    {
        super.makeConnection();
        Connection con = getConnection();
        ResultSet rs = null;
        try 
        {
            Statement statement = con.createStatement();
            System.out.println("SELECT * FROM admin WHERE "
                    + " email=" + getEmail() + " AND "
                    + " pass=" + getPassword());
            rs = statement.
                    executeQuery(
                    "SELECT * FROM admin WHERE "
                    + " email=" + getEmail() + " AND "
                    + " pass=" + getPassword());
            if(rs.first())
            {
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }finally
        {
            try {
                rs.close();
            } catch (SQLException ex) {
                Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
            }
            closeDb();
        }
        
        return false;
    }

    public String getEmail() {
        return "'" + email + "'";
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return "'" + password + "'";
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" + "email=" + email  + '}';
    }
    
    
}
