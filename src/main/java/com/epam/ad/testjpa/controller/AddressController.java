package com.epam.ad.testjpa.controller;

import com.epam.ad.testjpa.crud.AddressJPAService;
import com.epam.ad.testjpa.entity.Address;
import com.epam.ad.testjpa.service.CartService;
import com.epam.ad.testjpa.service.SignInService;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.inject.Inject;

@ManagedBean
public class AddressController {
    @Inject
    AddressJPAService addressJPAService;
    @Inject
    SignInService signInService;
@Inject
    CartService cartService;
    @ManagedProperty(value = "#{address}")
    private Address address;

    public String saveAddress(){
        String returnValue="address_saved?faces-redirect=true";
        try {
            address.setUser(signInService.getUser());
            addressJPAService.add(address);
            cartService.removeOrderItems();
            cartService.initOrder();
        }catch (Exception e){
            returnValue="error_saving_address?faces-redirect=true";
        }
        return  returnValue;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
