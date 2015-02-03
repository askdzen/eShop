package com.epam.ad.testjpa.crud;


import com.epam.ad.testjpa.entity.OrderItem;
import com.epam.ad.testjpa.entity.OrderItemPK;
import org.jboss.logging.Logger;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

@Stateless
@Named
public class Order_ItemJPAService extends JPAService<OrderItem> implements Serializable {
    @Inject
    OrderItem orderItem;
    @Inject
    EntityManager entityManager;
    @Inject
    Logger logger;
    public Order_ItemJPAService() {
        super(OrderItem.class);
    }
    public OrderItem find(int order_id,int item_id){
        return  getEm().find(OrderItem.class,new OrderItemPK(order_id,item_id));
    }

    public OrderItem getOrderItemByKey(int order_id, int item_id) {
        orderItem = (OrderItem) entityManager.createNamedQuery("OrderItem.findByKey").setParameter("oid", order_id).setParameter("iid",item_id).getSingleResult();
        logger.info("Составной ключ существует: " + orderItem.getOrderId()+orderItem.getItemId());
        return orderItem;
    }
    public List<OrderItem> getAllByOrder(int order_id) {
        List<OrderItem> orderItemList = entityManager.createNamedQuery("OrderItem.getAllByOrder").setParameter("oid", order_id).getResultList();
        for (OrderItem o : orderItemList) {
            logger.info("ордер: " + o.getOrderId()+" с позицией: "+o.getItemId());
        }

        return orderItemList;
    }
   public void delete(int order_id,int item_id){
     OrderItem  orderItem=(OrderItem) entityManager.createNamedQuery("OrderItem.findByKey").setParameter("oid", order_id).setParameter("iid",item_id).getSingleResult();

       entityManager.remove(orderItem);
   }

}
