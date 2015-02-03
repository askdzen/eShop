package com.epam.ad.testjpa.controller.usersController;

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

import java.util.List;

@WebServlet("userServlet")
public class UserServlet extends HttpServlet {
    @Inject
    UserJPAService service;


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<User> list = service.getAll();
        request.setAttribute("list",list);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/admin.jsp");
        requestDispatcher.forward(request, response);
    }
}
