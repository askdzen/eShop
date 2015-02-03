package com.epam.ad.testjpa.util;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.crud.RoleJPAService;
import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.Item;
import com.epam.ad.testjpa.entity.Order;
import com.epam.ad.testjpa.entity.Role;
import com.epam.ad.testjpa.entity.User;
import org.jboss.logging.Logger;

import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class Resources {
    @Inject
    RoleJPAService roleJPAService;
    @Inject
    UserJPAService userJPAService;
    @Inject
    ItemJPAService itemJPAService;
    @Produces
    @PersistenceContext
    public EntityManager em;

    @Produces
    Logger logger(InjectionPoint injectionPoint){
        return Logger.getLogger(injectionPoint.getMember().getDeclaringClass().getName());
    }

    public List<User> userList;
    public List<Role> roleList;
    public List<Item> itemList;
    @Produces
    @Named
    public List<Role> getRoleList() {
        roleList=roleJPAService.getAll();
        return roleList;
    }
    @Produces
    @Named
    public List<User> getUserList() {
        userList=userJPAService.getAll();
        return userList;
    }
    @Produces
    @Named
    public List<Item> getItemList() {
        itemList=itemJPAService.getAll();
        return itemList;
    }
}
