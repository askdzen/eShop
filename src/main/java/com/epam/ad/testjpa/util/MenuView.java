package com.epam.ad.testjpa.util;

import org.jboss.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;

@Named

public class MenuView implements Serializable{
    @Inject
    Logger logger;

    @Inject
    SessionState sessionState;


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
//        if (locale==null){
//            locale= FacesContext.getCurrentInstance().getViewRoot().getLocale();
//        }
//       Locale locale=new Locale("en");
//       setLocale(locale);
//        FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
sessionState.changeLocal("en");

    }
    public void russian() {
//            if (locale==null){
//            locale= FacesContext.getCurrentInstance().getViewRoot().getLocale();
//        }
//        Locale locale=new Locale("ru");
//        setLocale(locale);
//        FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);
        sessionState.changeLocal("ru");

    }





//    public String getLanguage() {
//        return getLocale().getLanguage();
//    }
//    public void changeLocale(String lang_code) {
//        this.locale = new Locale(lang_code);
//        try {
//            FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
//        } catch (NullPointerException e) {
//            logger.error("FacesContext is null. It's ok when we are using jsp :)");
//        }
//        logger.info("Locale was changed to: " + lang_code+" "+locale.getLanguage());
//        //fires entities which list depends of locale:
//
//    }
//    public void setLanguage(String lang) {
//        changeLocale(lang);
//    }
//    public void localeChanged(ValueChangeEvent e) {
//        changeLocale(e.getNewValue().toString());
//    }

}
