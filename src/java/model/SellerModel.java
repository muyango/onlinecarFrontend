/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import Entities.carCustomer;
import Entities.carsEntity;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;

/**
 *
 * @author klion
 */
@ManagedBean(name = "carSeller")
public class SellerModel {

    carsEntity car = new carsEntity();
    String uname, passwd;

    public String getUname() {
        return uname;
    }

    public void setUname(String uname) {
        this.uname = uname;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public carsEntity getCar() {
        return car;
    }

    public String clogin() {
        if (getUname().equals("admin") && getPasswd().equals("1234")) {
            return "CARSELLER_DASHBOARD";

        } else {
            return "CAR_SELLER_LOGIN_PAGE";
        }
    }

    public void save() {
        try {
            
            ClientBuilder.newClient().target("http://localhost:7777/cartrade/addcar")
                .request().post(Entity.json(car));
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("savingdetails", new FacesMessage("CAR SAVED SUCCESSFULLY"));
        } catch (Exception ex) {
            FacesContext fct = FacesContext.getCurrentInstance();
            fct.addMessage("savingdetails", new FacesMessage("CAR NOT SAVED"));
        }
    }

    public List<carsEntity> getCars() {
        List<carsEntity> list = new ArrayList<>();
        try {
            
                 list = ClientBuilder.newClient()
                .target("http://localhost:7777/cartrade/getcars")
                .request().get(new GenericType<List<carsEntity>>() {
                });

            return list;
        } catch (Exception ex) {
            return null;
        }
    }

    public void deletCar(carsEntity c) {
        ClientBuilder.newClient()
                    .target("http://localhost:7777/cartrade/deletecar/" + c.getId())
                    .request().delete(carsEntity.class);
        
    }

}
