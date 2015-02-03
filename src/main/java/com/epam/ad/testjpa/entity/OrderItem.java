package com.epam.ad.testjpa.entity;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@IdClass(value = OrderItemPK.class)
@NamedQueries({
        @NamedQuery(name = "OrderItem.getAll", query = "SELECT o from OrderItem o"),
        @NamedQuery(name = "OrderItem.findByKey", query = "SELECT o from OrderItem o WHERE o.order_id =:oid AND o.item_id=:iid"),
        @NamedQuery(name = "OrderItem.update", query = "UPDATE OrderItem o SET o.itemQty =:qty where o.order_id = :oid and o.item_id=:iid"),
        @NamedQuery(name = "OrderItem.getAllByOrder",query = "SELECT o from OrderItem o WHERE o.order_id =:oid")

})
public class OrderItem {
    @Id
    @Column(name = "order_id")
    private int order_id;
    @Id
    @Column(name = "item_id")
    private int item_id;
    @Column(name = "item_qty")
    private int itemQty;

    public int getOrderId() {
        return order_id;
    }

    public void setOrderId(int orderId) {
        this.order_id = orderId;
    }

    public int getItemId() {
        return item_id;
    }

    public void setItemId(int itemId) {
        this.item_id = itemId;
    }

    public int getItemQty() {
        return itemQty;
    }

    public void setItemQty(int itemQty) {
        this.itemQty = itemQty;
    }
}
