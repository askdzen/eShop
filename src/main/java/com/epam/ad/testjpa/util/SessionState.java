package com.epam.ad.testjpa.util;

import org.jboss.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.faces.event.ValueChangeEvent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

@Named
@Stateful
@SessionScoped
public class SessionState implements Serializable {
    @Inject
    Logger logger;

    private Locale locale;

    public void changeLocal(String lang) {
        if (locale==null){
            locale= FacesContext.getCurrentInstance().getViewRoot().getLocale();
        }
        Locale locale=new Locale(lang);
        setLocale(locale);
        FacesContext.getCurrentInstance().getViewRoot().setLocale(this.locale);

    }

    public Locale getLocale() {
        if (locale==null){
            locale= FacesContext.getCurrentInstance().getViewRoot().getLocale();
        }
        return locale;
    }

    public void setLocale(Locale locale) {
        this.locale = locale;
    }
}
