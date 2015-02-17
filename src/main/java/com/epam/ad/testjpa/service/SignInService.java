package com.epam.ad.testjpa.service;


import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.Order;
import com.epam.ad.testjpa.entity.Role;
import com.epam.ad.testjpa.entity.User;
import com.epam.ad.testjpa.util.MenuView;
import com.epam.ad.testjpa.util.SessionState;
import org.jboss.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;


@Stateful
@Named
@SessionScoped
public class SignInService implements Serializable {

    @Inject
    UserJPAService userJPAService;
    @Inject
    CartService cartService;
    @Inject
    Order order;
    @Inject
    Logger logger;
    @Inject
    SessionState sessionState;



    public User user;
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    public String goToWelcomeOrAdmin(User user){
        if (signIn(user.getUsername(),user.getPassword())){
            if (signInAdmin(user.getUsername())){
                return "admin?faces-redirect=true";
            }else
                return "welcome?faces-redirect=true";
        }

        logger.info("Locale in the addMessage" + sessionState.getLocale().getLanguage());
        ResourceBundle resourceBundle= ResourceBundle.getBundle("i18n.text",sessionState.getLocale());
        addMessage(resourceBundle.getString("index.message.badlogin"), "Вы ввели неправильный логин или пароль");
        return "index";
    }
    public boolean signIn(String username, String password) {
        if (userJPAService.usernameIs(username)) {
            user = userJPAService.getUserByUsername(username);
            if (user.getPassword().equals(password))

                return true;
        }
        return false;
    }

    public boolean signInAdmin(String username) {
        user = userJPAService.getUserByUsername(username);
        Role role = user.getRole();
        if (role.getName().equals("admin")) {
            return true;
        }
        return false;
    }

    public User getUser() {
        return user;
    }

    public boolean cartEmpty(){
        if (cartService.getOrderItems().size()>0){
            return false;
        }else
            return true;
    }
    @PostConstruct
    public void initNewUser() {
        user = new User();
if (!cartEmpty()){
    order = new Order();
}
    }
}
