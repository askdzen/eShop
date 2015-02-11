package com.epam.ad.testjpa.controller;

import com.epam.ad.testjpa.crud.ItemJPAService;
import com.epam.ad.testjpa.crud.RoleJPAService;
import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.User;
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

@WebServlet({"/",
        "checkUsernamePassword"})
public class StartServlet extends HttpServlet {

    @Inject
    UserJPAService service;
    @Inject
    SignInService signInService;
    @Inject
    Logger logger;
    @Inject
    User user;
    @Inject
    RoleJPAService roleJPAService;
    @Inject
    ItemJPAService itemJPAService;
    @Inject
    CartService cartService;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (request.getServletPath().equals("/checkUsernamePassword")) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            if (signInService.signIn(username, password)) {
                if (signInService.signInAdmin(username)) {

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin.jsp");
                    requestDispatcher.forward(request, response);
                } else {

                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/welcome.jsp");
                    requestDispatcher.forward(request, response);
                }
            } else {
                request.setAttribute("nosign", "Не верны имя пользователя или пароль");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
                requestDispatcher.forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info(request.getContextPath());

        if (request.getServletPath().equals("/")) {
            logger.info(signInService.getUser().getUsername());

                if (signInService.getUser().getUsername()==null) {

                    request.setAttribute("user", "guest");
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/index.jsp");
                    requestDispatcher.forward(request, response);
                }
            else {
                    RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/welcome.jsp");
                    requestDispatcher.forward(request, response);
                }


//


        }

    }
}
