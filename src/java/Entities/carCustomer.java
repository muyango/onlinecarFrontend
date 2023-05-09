/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author klion
 */
@Entity
@Table(name="customers")
public class carCustomer {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)        
    int id;
    String fname;
    String lname;
    String email;
    String Password;

    public carCustomer() {
    }
    
    

    public carCustomer(String fname, String lname, String email, String Password) {
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.Password = Password;
    }

    public int getId() {
        return id;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return Password;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String Password) {
        this.Password = Password;
    }
    
    
}
