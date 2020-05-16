/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject4;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.event.ActionEvent;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

@ManagedBean
@SessionScoped
public class CustomerController implements Serializable {

    public CustomerController() {
    }

    public void saveCustomer(ActionEvent actionEvent) {
     
        FacesMessage facesMessage = new FacesMessage("Data Saved Successfully");
        facesMessage.setSeverity(FacesMessage.SEVERITY_INFO);
        FacesContext.getCurrentInstance().addMessage(null, facesMessage);
    }
}
