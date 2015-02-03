package com.epam.ad.testjpa.controller;


import com.epam.ad.testjpa.crud.RoleJPAService;
import com.epam.ad.testjpa.crud.UserJPAService;
import com.epam.ad.testjpa.entity.User;


import javax.inject.Inject;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("registration")
public class RegistrServlet extends HttpServlet {
    @Inject
    UserJPAService service;
    @Inject
    User user;
    @Inject
    RoleJPAService roleJPAService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        user.setUsername(request.getParameter("username"));
        user.setPassword(request.getParameter("password"));
        user.setFirstName(request.getParameter("firstName"));
        user.setLastName(request.getParameter("lastName"));
        user.setEmail(request.getParameter("email"));
        user.setRole(roleJPAService.getRoleByName("user"));
        service.add(user);
        request.setAttribute("successfully","You have successfully registered!");
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
        requestDispatcher.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/registration.jsp");
        requestDispatcher.forward(request, response);
    }
}
