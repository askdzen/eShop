package com.epam.ad.testjpa.crud;

import com.epam.ad.testjpa.entity.Order;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;

@Stateless
@Named
public class OrderJPAService extends JPAService<Order> implements Serializable{

    @Inject
    private EntityManager entityManager;
    public OrderJPAService() {
        super(Order.class);
    }

}
