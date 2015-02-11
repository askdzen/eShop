package com.epam.ad.testjpa.controller.orderController;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.crud.OrderJPAService;
import com.epam.ad.testjpa.crud.Order_ItemJPAService;
import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.OrderItem;
import com.epam.ad.testjpa.service.CartService;
import com.epam.ad.testjpa.service.SignInService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet({
        "goToCart",
        "itemCountUpdate",
        "cartAdd",
        "deleteFromCart"

})
public class CartServlet extends HttpServlet {
    @Inject
    CartService cartService;
    @Inject
    SignInService signInService;
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
            order_itemJPAService.updateOrderItemCount(orderId, itemId, count);
            logger.info("Обновление " + orderItem.getOrderId() + " " + orderItem.getItemId());
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
            if (cartService.getOrderItems().size() > 0) {
                logger.info("order id : " + cartService.getOrder().getId());
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
                requestDispatcher.forward(request, response);
            } else {
                request.setAttribute("noItems", "Вы ничего не выбрали для покупок");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
                requestDispatcher.forward(request, response);
            }
        }
        if (request.getServletPath().equals("/cartAdd")) {
            cartService.setSignInService(this.signInService);
            logger.info("cartList size= " + cartService.getOrderItems().size());
            if (cartService.getOrderItems().size() == 0) {
                cartService.addNewOrder();
            }
            if (cartService.unicOrderItem(Integer.parseInt(request.getParameter("id")))){
            cartService.addItemInCart(itemJPAService.getById(Integer.parseInt(request.getParameter("id")), "iid"));
            logger.info("Order in ServletCartAdd" + cartService.getOrder().getId());
            request.setAttribute("cart", "disabled='disabled'");
            }
            request.setAttribute("cartSize", cartService.getOrderItems().size());
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/welcome.jsp");
            requestDispatcher.forward(request, response);
        }
        if (request.getServletPath().equals("/deleteFromCart")) {
            int itemId = Integer.parseInt(request.getParameter("itemId"));
            int orderId = Integer.parseInt(request.getParameter("orderId"));
            logger.info("Item id in the DeleteFromCartServlet " + itemId);
            logger.info("Order id in the DeleteFromCartServlet " + orderId);
            cartService.deleteItemFromCart(itemId, orderId);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
            requestDispatcher.forward(request, response);
        }
    }


}
