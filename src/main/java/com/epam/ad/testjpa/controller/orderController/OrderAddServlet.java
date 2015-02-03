package com.epam.ad.testjpa.controller.orderController;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.crud.OrderJPAService;
import com.epam.ad.testjpa.crud.Order_ItemJPAService;
import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.Order;
import com.epam.ad.testjpa.entity.OrderItem;
import com.epam.ad.testjpa.model.Cart;
import com.epam.ad.testjpa.model.SignIn;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet({
        "goToCart",
        "itemCountUpdate",
        "cartAdd",
        "deleteFromCart"

})
public class OrderAddServlet extends HttpServlet {
    @Inject
    Cart cart;
    @Inject
    SignIn signIn;
    @Inject
    ItemJPAService itemJPAService;
    @Inject
    OrderJPAService orderJPAService;
    @Inject
    UserJPAService userJPAService;
    @Inject
    Logger logger;

    @Inject
    OrderItem orderItem;
    @Inject
    Order_ItemJPAService order_itemJPAService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getServletPath().equals("/itemCountUpdate")) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int count = Integer.parseInt(request.getParameter("count"));
            orderItem = order_itemJPAService.getOrderItemByKey(orderId, itemId);
            orderItem.setItemQty(count);
            order_itemJPAService.update(orderItem);
            logger.info("Обновление " + orderItem.getOrderId() + " " + orderItem.getItemId());
            List<OrderItem> orderItemList = order_itemJPAService.getAllByOrder(cart.getOrder().getId());
            request.setAttribute("orderItemList", orderItemList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getServletPath().equals("/itemCountUpdate")) {
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int count = Integer.parseInt(request.getParameter("count"));
            request.setAttribute("orderId", orderId);
            request.setAttribute("itemId", itemId);
            request.setAttribute("count", count);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/changeCount.jsp");
            requestDispatcher.forward(request, response);
        }
        if (request.getServletPath().equals("/goToCart")) {
            if (cart.getUserCartList().size() > 0) {

                logger.info("order id : " + cart.getOrder().getId());
                List<OrderItem> orderItemList = order_itemJPAService.getAllByOrder(cart.getOrder().getId());
                request.setAttribute("orderItemList", orderItemList);
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("noItems", "Вы ничего не выбрали для покупок");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        if (request.getServletPath().equals("/cartAdd")) {
            cart.setSignIn(this.signIn);
            logger.info("cartList size= " + cart.getUserCartList().size());
            if (cart.getUserCartList().size() == 0) {

                cart.addNewOrder();

//                if (cart.unicOrderItem(order.getId(),Integer.parseInt(request.getParameter("id")))){
//                    cart.addItemInCart(itemJPAService.getById(Integer.parseInt(request.getParameter("id")), "iid"));
//                    order.setItems(cart.getUserCartList());
//                    orderJPAService.update(order);
//                }

            }
//            if (cart.unicOrderItem(order.getId(),Integer.parseInt(request.getParameter("id")))){

            cart.addItemInCart(itemJPAService.getById(Integer.parseInt(request.getParameter("id")), "iid"));
            logger.info("Order in ServletCartAdd" + cart.getOrder().getId());
            cart.getOrder().setItems(cart.getUserCartList());
            orderJPAService.update(cart.getOrder());
            request.setAttribute("cart", "disabled='disabled'");
//            }
            request.setAttribute("cartSize", cart.getUserCartList().size());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/welcome.jsp");
            requestDispatcher.forward(request, response);
        }
        if (request.getServletPath().equals("/deleteFromCart")) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            logger.info("Item id in the DeleteFromCartServlet " + itemId);
            logger.info("Order id in the DeleteFromCartServlet " + orderId);
            cart.deleteItemFromCart(itemId, orderId);
            List<OrderItem> orderItemList = order_itemJPAService.getAllByOrder(cart.getOrder().getId());
            request.setAttribute("orderItemList", orderItemList);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
            requestDispatcher.forward(request, response);
        }
    }


}
