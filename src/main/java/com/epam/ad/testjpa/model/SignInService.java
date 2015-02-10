package com.epam.ad.testjpa.model;


import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.Order;
import com.epam.ad.testjpa.entity.Role;
import com.epam.ad.testjpa.entity.User;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;


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

    public User user;

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
