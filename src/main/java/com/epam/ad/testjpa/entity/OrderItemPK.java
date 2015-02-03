package com.epam.ad.testjpa.entity;


import java.io.Serializable;

public class OrderItemPK implements Serializable {
    public int order_id;
    public int item_id;
    public OrderItemPK(){

    }
    public OrderItemPK(int order_Id,int item_Id){
        this.order_id=order_Id;
        this.item_id=item_Id;
    }



    @Override
    public boolean equals(Object obj) {
        boolean returnVal=false;
        if (obj==null){
            returnVal=false;
        }
        else  if (!obj.getClass().equals(this.getClass())){
            returnVal=false;
        }
        else
        {
            OrderItemPK other= (OrderItemPK) obj;
            if (this==other){
                returnVal=true;
            }
            else if (order_id != 0 && other.order_id != 0 && this.order_id==other.order_id){
                if (item_id!=0 && other.item_id!=0 && item_id==other.item_id){
                    returnVal=true;
                }
            }
            else
            {
                returnVal=false;
            }
        }
        return returnVal;
    }

    @Override
    public int hashCode() {
        if (order_id==0||item_id==0){
            return 0;
        }
        else {
            return order_id^item_id;
        }
    }
}
