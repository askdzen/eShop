package com.epam.ad.testjpa.util;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

@Named
public class MenuView {


    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



    public void inAccount() {
        addMessage("Success", "Data saved");
    }

    public void outAccount() {
        addMessage("Success", "Data saved");
    }

    public void registration() {
        addMessage("Success", "Data saved");
    }

    public void english() {
        addMessage("Success", "Data saved");
    }

    public void russian() {
        addMessage("Success", "Data saved");
    }
}
