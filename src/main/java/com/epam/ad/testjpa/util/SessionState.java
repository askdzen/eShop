//package com.epam.ad.testjpa.util;
//
//import org.jboss.logging.Logger;
//
//import javax.ejb.Stateful;
//import javax.enterprise.context.SessionScoped;
//import javax.faces.bean.ManagedBean;
//import javax.faces.context.FacesContext;
//import javax.faces.event.ValueChangeEvent;
//import javax.inject.Inject;
//import javax.inject.Named;
//import java.io.Serializable;
//import java.util.LinkedHashMap;
//import java.util.Locale;
//import java.util.Map;
//
//@ManagedBean
//@Stateful
//@SessionScoped
//public class SessionState implements Serializable {
//    @Inject
//    Logger logger;
//    private Locale locale;
//    private static Map<String, String> langs;
//
//
//    static {
//        langs = new LinkedHashMap<>();
//        langs.put("English", "en");
//        langs.put("Русский", "ru");
//    }
//    public Map<String, String> getLangs() {
//        return langs;
//    }
//
//    public void setLangs(Map<String, String> langs) {
//        SessionState.langs = langs;
//    }
//
//    public Locale getLocale() {
//        if (locale==null){
//            locale= FacesContext.getCurrentInstance().getViewRoot().getLocale();
//        }
//        return locale;
//    }
//
//    public void setLocale(Locale locale) {
//        this.locale = locale;
//    }
//
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
//        logger.info("Locale was changed to: " + lang_code);
//        //fires entities which list depends of locale:
//
//    }
//    public void setLanguage(String lang) {
//        changeLocale(lang);
//    }
//    public void localeChanged(ValueChangeEvent e) {
//        changeLocale(e.getNewValue().toString());
//    }
//}
