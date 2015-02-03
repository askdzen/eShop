package com.epam.ad.testjpa.crud;


import com.epam.ad.testjpa.entity.Item;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;

@Stateless
@Named

public class ItemJPAService extends JPAService<Item> implements Serializable {
    @Inject
    private EntityManager entityManager;
    public ItemJPAService() {
        super(Item.class);
    }



}
