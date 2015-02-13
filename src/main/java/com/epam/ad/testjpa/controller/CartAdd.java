package com.epam.ad.testjpa.controller;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.service.CartService;
import com.epam.ad.testjpa.service.SignInService;
import org.jboss.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;


@ManagedBean
public class CartAdd {
    @Inject
    SignInService signInService;
    @Inject
    CartService cartService;
    @Inject
    ItemJPAService itemJPAService;
    @Inject
    Logger logger;

    public String cartAdd(int itemId){
        String returnValue="welcome";
        cartService.setSignInService(cartService.getSignInService());

logger.info("itemId in CartAdd"+itemId );
        if (cartService.getOrderItems().size() == 0) {
            cartService.addNewOrder();
        }
        if (cartService.unicOrderItem(itemId)){
            cartService.addItemInCart(itemJPAService.getById(itemId, "iid"));

        }
       return returnValue;
    }

}
