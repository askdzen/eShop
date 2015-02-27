package com.epam.ad.testjpa.crud;

import com.epam.ad.testjpa.entity.Role;
import com.epam.ad.testjpa.entity.User;
import org.slf4j.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;


@Stateless
@Named
public class UserJPAService extends JPAService<User> implements Serializable{

    @Inject
    Logger logger;
    @Inject
    User user;
    @Inject
    private EntityManager entityManager;


    public UserJPAService() {
        super(User.class);
    }

    public User getUserByUsername(String username) {
        user = (User) entityManager.createNamedQuery("User.findByUsername").setParameter("uname", username).getSingleResult();
        logger.info("Пользователь с именем существует: " + user.getUsername());
        return user;
    }

    public boolean usernameIs(String username) {
        try {
            user = (User) entityManager.createNamedQuery("User.findByUsername").setParameter("uname", username).getSingleResult();
            logger.info("Пользователь с именем существует: " + user.getUsername());
            return true;
        } catch (Exception e) {
            return false;
        }
    }




}
