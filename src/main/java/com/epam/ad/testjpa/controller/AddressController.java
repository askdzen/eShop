package com.epam.ad.testjpa.controller;

import com.epam.ad.testjpa.crud.AddressJPAService;
import com.epam.ad.testjpa.entity.Address;
import com.epam.ad.testjpa.service.CartService;
import com.epam.ad.testjpa.service.SignInService;
import org.slf4j.Logger;


import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;
import java.io.Serializable;

@ManagedBean
public class AddressController implements Serializable {
    @Inject
    AddressJPAService addressJPAService;
    @Inject
    SignInService signInService;
    @Inject
    CartService cartService;
    @ManagedProperty(value = "#{address}")
    private Address address;
    @Inject
    Logger logger;

    public String saveAddress() {
        String returnValue = "address_saved?faces-redirect=true";
        try {
            address.setUser(cartService.getSignInService().getUser());
            addressJPAService.add(address);
            cartService.getOrder().setUser(cartService.getSignInService().getUser());
            logger.info("Order user: "+cartService.getOrder().getUser().getUsername());
            cartService.buyAll();
            cartService.removeOrderItems();
            cartService.initOrder();
        } catch (Exception e) {
            returnValue = "error_saving_address?faces-redirect=true";
        }
        return returnValue;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
