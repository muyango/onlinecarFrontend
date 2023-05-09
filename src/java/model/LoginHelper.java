/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Entities.carCustomer;
import Filter.SessionUtil;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author klion
 */
@ManagedBean(name = "custLogin")
public class LoginHelper {

    carCustomer customer = new carCustomer();

    public carCustomer getCustomer() {
        return customer;
    }

    public String cLoginHelper() {
        boolean check = false;
        
         List<carCustomer> list = ClientBuilder.newClient()
                .target("http://localhost:7777/cartrade/getcustomer")
                .request().get(new GenericType<List<carCustomer>>() {
                });
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getEmail().equals(customer.getEmail())) {
                if (list.get(i).getPassword().equals(customer.getPassword())) {
                    customer.setFname(list.get(i).getFname());
                    customer.setLname(list.get(i).getLname());
                    check = true;
                }
            }
        }
        if (check) {
            HttpSession session = SessionUtil.getSession();
            session.setAttribute("customerSession", customer);
            return "CARDASHBOARD?faces-redirect=true";
        } else {
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("logindetails", new FacesMessage("Invalid LOGIN CREDENTIALS"));
            return "LOGINPAGE";
        }
    }
}
