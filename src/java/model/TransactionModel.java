/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Entities.TransactionEntity;
import Entities.carCustomer;
import Entities.carsEntity;
import Filter.SessionUtil;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author klion
 */
@ManagedBean(name = "trans")
public class TransactionModel {

 
    carCustomer customer = new carCustomer();
    carsEntity car = new carsEntity();
    TransactionEntity transaction = null;
    String username;
    List<carsEntity>carlist=new ArrayList<>();
    private String searchKey;
    public TransactionModel() {
        carlist=ClientBuilder.newClient()
                .target("http://localhost:7777/cartrade/getcars")
                .request().get(new GenericType<List<carsEntity>>() {
                });
        PatientSession();
        
    }

    public String getSearchKey() {
        return searchKey;
    }

    public void setSearchKey(String searchKey) {
        this.searchKey = searchKey;
    }

    public List<carsEntity> getCarlist() {
        return carlist;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public carCustomer getCustomer() {
        return customer;
    }

    public void setCustomer(carCustomer customer) {
        this.customer = customer;
    }

    public carsEntity getCar() {
        return car;
    }

    public void setCar(carsEntity car) {
        this.car = car;
    }

    public void transact(carsEntity carr) {
        try {
            TransactionEntity trans=new TransactionEntity();
            trans.setCarName(carr.getName());
            HttpSession session = SessionUtil.getSession();
            String customerName = (String) session.getAttribute("customerSession");
            trans.setCustomer(customerName);
            ClientBuilder.newClient().target("http://localhost:7777/cartrade/addtransaction")
                .request().post(Entity.json(trans));
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("transactiondetails", new FacesMessage("TRANSACTION SUCCESSFUL"));
        } catch (Exception ex) {
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("transactiondetails", new FacesMessage("TRANSACTION SUCCESSFULL"));
        }
    }

    public void PatientSession() {
        HttpSession session = SessionUtil.getSession();
        customer = (carCustomer) session.getAttribute("customerSession");
        setUsername(customer.getFname() + " " + customer.getLname());

    }

    public void searching() {
        System.out.println(searchKey);
        List<carsEntity> l = new ArrayList<>();
        List<carsEntity> list = getCarlist();
        if (searchKey.matches("[a-zA-z]*")) {
            for (carsEntity c : carlist) {
                if (!(c.getName().startsWith(searchKey))) {
                    l.add(c);
                }
            }
            carlist.removeAll(l);

        } else {
            carlist.clear();
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("name-frm" + ":key", new FacesMessage("CAN NOT FIND MATCH INTO THE DATABSE"));

        }
    }
}
