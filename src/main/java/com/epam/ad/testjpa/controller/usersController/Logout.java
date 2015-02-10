package com.epam.ad.testjpa.controller.usersController;

import com.epam.ad.testjpa.crud.OrderJPAService;
import com.epam.ad.testjpa.crud.Order_ItemJPAService;
import com.epam.ad.testjpa.entity.Order;
import com.epam.ad.testjpa.model.CartService;
import com.epam.ad.testjpa.model.SignInService;
import org.jboss.logging.Logger;

import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("logout")
public class Logout extends HttpServlet {
    @Inject
    SignInService signInService;
    @Inject
    CartService cartService;
    @Inject
    Order_ItemJPAService order_itemJPAService;
    @Inject
    OrderJPAService orderJPAService;
    @Inject
    Logger logger;
    @Inject
    Order order;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("удаляемый заказ № " + cartService.getOrder().getId());
        if (cartService.getOrderItems().size() > 0) {
            cartService.deleteOrder();
            signInService.initNewUser();
        }
        response.sendRedirect(request.getContextPath());

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (!signInService.cartEmpty()) {
            request.setAttribute("cartNoEmpty", "В корзине имеется товар");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/shopcart.jsp");
            requestDispatcher.forward(request, response);
        } else {
           signInService.initNewUser();
            response.sendRedirect(request.getContextPath());

        }

    }
}
