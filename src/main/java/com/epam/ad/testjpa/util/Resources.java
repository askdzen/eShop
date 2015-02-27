package com.epam.ad.testjpa.util;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.crud.Order_ItemJPAService;
import com.epam.ad.testjpa.crud.RoleJPAService;
import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.*;
import com.epam.ad.testjpa.service.CartService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.Locale;
import java.util.ResourceBundle;


public class Resources {
    @Inject
    RoleJPAService roleJPAService;
    @Inject
    UserJPAService userJPAService;
    @Inject
    ItemJPAService itemJPAService;
    @Inject
    Order_ItemJPAService order_itemJPAService;
    @Inject
    CartService cartService;
    @Produces
    @PersistenceContext
    public EntityManager em;
    @Produces
    public Logger logger(){
        return LoggerFactory.getLogger(this.getClass());
    }



//    @Produces
//    Logger logger(InjectionPoint injectionPoint){
//        return Logger.(injectionPoint.getMember().getDeclaringClass().getName());
//    }


    public List<User> userList;
    public List<Role> roleList;
    public List<Item> itemList;
    public List<OrderItem> orderItemList;
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
    @Produces
    @Named
    public List<OrderItem> getOrderItemList(){
        orderItemList=order_itemJPAService.getAllByOrder(cartService.getOrder().getId());
        return orderItemList;
    }




}
