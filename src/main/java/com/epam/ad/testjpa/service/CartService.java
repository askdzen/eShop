package com.epam.ad.testjpa.service;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.crud.OrderJPAService;
import com.epam.ad.testjpa.crud.Order_ItemJPAService;
import com.epam.ad.testjpa.entity.Item;
import com.epam.ad.testjpa.entity.Order;
import com.epam.ad.testjpa.util.SessionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ejb.Stateful;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.*;

@Stateful
@Named
@SessionScoped
public class CartService implements Serializable {
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(CartService.class);
    @Inject
    Logger logger;
    @Inject
    SignInService signInService;
    @Inject
    Order order;
    @Inject
    OrderJPAService service;
    @Inject
    ItemJPAService itemJPAService;
    @Inject
    Order_ItemJPAService order_itemJPAService;
    @Inject
    SessionState sessionState;
    @RequestScoped
    private String resultBuy;
    int itemIndexOf;

    private List<Item> orderItems = new ArrayList<>();

    public void addItemInCart(Item item) {

        orderItems.add(itemJPAService.getById(item.getId(), "iid"));
        getOrder().setItems(getOrderItems());
        service.update(getOrder());
    }
    public void addItemInOrderItemList(Item item){
        orderItems.add(item);
    }

    public boolean removeOrderItems(){
        orderItems.clear();
        return true;
    }
    public boolean unicOrderItem(int itemId) {

        if (orderItems.size() > 0) {
            for (Item item : orderItems) {
                if (itemId == item.getId()) {
                    return false;
                }
            }
        }
        return true;
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
    public String buyAll(){
        String returnValue="address";
           logger.info("User name: "+getSignInService().getUser().getUsername());
        if (getOrderItems().size() != 0) {
            Order newOrder=addNewOrder();
            newOrder.setItems(getOrderItems());
            service.update(newOrder);
            logger.info("Order for User: "+getOrder().getUser().getUsername() +" OrderId: "+getOrder().getId());
        }

        return returnValue;
    }
    public Order addNewOrder() {
        order.setUser(signInService.getUser());

        order.setId(service.add(order).getId());
        service.add(order);
        return order;
    }

    public boolean deleteOrder(){
        service.delete(order.getId());
        removeOrderItems();
        return true;
    }
    public void initOrder(){
        order=new Order();
    }

    public void setSignInService(SignInService signInService) {
        this.signInService = signInService;
    }

    public SignInService getSignInService() {
        return signInService;
    }

    public OrderJPAService getService() {
        return service;
    }

    public void setService(OrderJPAService service) {
        this.service = service;
    }



    public String cartAdd(int itemId){
        String returnValue="welcome";
//        cartService.setSignInService(cartService.getSignInService());
//
//logger.info("itemId in CartAdd"+itemId );
//        if (cartService.getOrderItems().size() == 0) {
//            cartService.addNewOrder();
//        }
        if (unicOrderItem(itemId)){
            addItemInOrderItemList(itemJPAService.getById(itemId, "iid"));
            ResourceBundle resourceBundle= ResourceBundle.getBundle("i18n.text",sessionState.getLocale());
            setResultBuy(resourceBundle.getString("cart.add"));
        }
        return returnValue;
    }



    public String getResultBuy() {
        return resultBuy;
    }

    public void setResultBuy(String resultBuy) {
        this.resultBuy = resultBuy;
    }
}
