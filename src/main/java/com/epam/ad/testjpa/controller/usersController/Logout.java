package com.epam.ad.testjpa.controller.usersController;

import com.epam.ad.testjpa.crud.OrderJPAService;
import com.epam.ad.testjpa.crud.Order_ItemJPAService;
import com.epam.ad.testjpa.entity.Item;
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


@WebServlet("logout")
public class Logout extends HttpServlet {
    @Inject
    SignIn signIn;
    @Inject
    Cart cart;
    @Inject
    Order_ItemJPAService order_itemJPAService;
    @Inject
    OrderJPAService orderJPAService;
    @Inject
    Logger logger;
    @Inject
    Order order;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("удаляемый заказ № "+cart.getOrder().getId());
        List<Item> orderItems = cart.getOrderItems();
        logger.info("число удаляемых позиций "+orderItems.size());
        for (Item item : orderItems) {
            logger.info("удаляемая позиция заказа № "+item.getId() + " ");
            cart.deleteItemFromCart(item.getId(), cart.getOrder().getId());
        }
        response.sendRedirect(request.getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       if (!signIn.cartEmpty())
       {
           request.setAttribute("cartNoEmpty","В корзине имеется товар");
           RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
           requestDispatcher.forward(request, response);
       }else
       {

           RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
           requestDispatcher.forward(request, response);
       }

    }
}
