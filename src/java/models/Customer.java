/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Shabab
 */
public class Customer extends AbstractModel{
    
    private int id;
    private String name;
    private String email;
    private String phone;
    private String address;
    private Date book_in_date;
    private Date book_out_date;
    private Integer is_paid;

    public Customer(int id, String name, String email, String phone, String address, String book_in_date, String book_out_date, Integer is_paid) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.is_paid = is_paid;
        setBook_in_date(book_in_date);
        setBook_out_date(book_out_date);
    }
    
    public String getPaymentStatus()
    {
        return (is_paid == 0)? "Not Paid" : "Paid";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getBook_in_date() {
        return book_in_date;
    }

    public final void setBook_in_date(String book_in_date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
             parsed = (java.util.Date) format.parse(book_in_date);
             
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.book_in_date = new Date(parsed.getTime());
    }

    public Date getBook_out_date() {
        return book_out_date;
    }

    public final void setBook_out_date(String book_out_date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        java.util.Date parsed = null;
        try {
             parsed = (java.util.Date) format.parse(book_out_date);
        } catch (ParseException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        this.book_out_date = new Date(parsed.getTime());
    }
    
    
}
