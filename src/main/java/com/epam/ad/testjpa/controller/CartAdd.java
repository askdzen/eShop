package com.epam.ad.testjpa.controller;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.service.CartService;
import com.epam.ad.testjpa.service.SignInService;
import com.epam.ad.testjpa.util.MenuView;
import com.epam.ad.testjpa.util.SessionState;
import org.jboss.logging.Logger;

import javax.faces.bean.ManagedBean;
import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import java.util.ResourceBundle;


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
    @Inject
    SessionState sessionState;
    private String resultPay;

    public String cartAdd(int itemId){
        String returnValue="welcome";
        cartService.setSignInService(cartService.getSignInService());

logger.info("itemId in CartAdd"+itemId );
        if (cartService.getOrderItems().size() == 0) {
            cartService.addNewOrder();
        }
        if (cartService.unicOrderItem(itemId)){
            cartService.addItemInCart(itemJPAService.getById(itemId, "iid"));
            ResourceBundle resourceBundle= ResourceBundle.getBundle("i18n.text",sessionState.getLocale());
            setResultPay(resourceBundle.getString("cart.add"));
        }
       return returnValue;
    }


    public String getResultPay() {
        return resultPay;
    }

    public void setResultPay(String resultPay) {
        this.resultPay = resultPay;
    }
}
