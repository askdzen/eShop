package com.epam.ad.testjpa.model;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.crud.OrderJPAService;
import com.epam.ad.testjpa.crud.Order_ItemJPAService;
import com.epam.ad.testjpa.entity.Item;
import com.epam.ad.testjpa.entity.Order;
import org.jboss.logging.Logger;

import javax.ejb.Stateful;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Stateful
@Named
@SessionScoped
public class Cart implements Serializable {
    @Inject
    Logger logger;
    @Inject
    SignIn signIn;
    @Inject
    Order order;
    @Inject
    OrderJPAService service;
    @Inject
    ItemJPAService itemJPAService;
    @Inject
    Order_ItemJPAService order_itemJPAService;
    int itemIndexOf;

    private List<Item> orderItems = new ArrayList<>();

    public void addItemInCart(Item item) {


        orderItems.add(itemJPAService.getById(item.getId(), "iid"));
    }

    public boolean unicOrderItem(int orderId, int itemId) {

        try {
            order_itemJPAService.getOrderItemByKey(orderId, itemId);
            logger.info("OrderItem Size" + orderItems.size());
        } catch (Exception e) {
            logger.info("order Id " + order.getId());
            return true;
        }
        return false;
    }


    public void deleteItemFromCart(int itemId, int orderId) {
        logger.info("Item_id in the Cart methods: " + itemId);
        for (Item item1 : orderItems) {
            if (item1.getId() == (itemId)) {
                itemIndexOf = orderItems.indexOf(item1);
            }
        }
        if (orderItems.size() > 0) {

            order_itemJPAService.delete(orderId, itemId);
        }
        orderItems.remove(itemIndexOf);
    }


    public List<Item> getOrderItems() {
        return orderItems;
    }

    public Order getOrder() {

        return this.order;
    }

    public Order addNewOrder() {
        order.setUser(signIn.getUser());
        order.setId(service.add(order).getId());
        service.add(order);
        return order;
    }


    public void setSignIn(SignIn signIn) {
        this.signIn = signIn;
    }
}
