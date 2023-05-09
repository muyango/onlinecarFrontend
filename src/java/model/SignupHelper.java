/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Entities.carCustomer;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;

/**
 *
 * @author klion
 */
@ManagedBean(name = "custM")
public class SignupHelper {

    carCustomer customer = new carCustomer();

    public carCustomer getCustomer() {
        return customer;
    }

    public String signupHelper() {
        try {
             ClientBuilder.newClient().target("http://localhost:7777/cartrade/addcustomer")
                .request().post(Entity.json(customer));
            return "LOGINPAGE";
        } catch (Exception ex) {
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("signupdetails", new FacesMessage("UNABLE TO SIGNUP PLEASE CONTACT ADMIN"));
            ex.printStackTrace();
            return "SIGNUPPAGE";
        }
    }
    

}
