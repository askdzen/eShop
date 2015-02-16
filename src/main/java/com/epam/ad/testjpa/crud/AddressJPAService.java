package com.epam.ad.testjpa.crud;


import com.epam.ad.testjpa.entity.Address;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;

@Stateless
@Named
public class AddressJPAService extends JPAService<Address>{
    @Inject
    private EntityManager entityManager;
    public AddressJPAService() {
        super(Address.class);
    }
}
