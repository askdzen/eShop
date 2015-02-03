package com.epam.ad.testjpa.controller.usersController;

import com.epam.ad.testjpa.crud.UserJPAService;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("userDeleteServlet")
public class UserDeleteServlet extends HttpServlet {
    @Inject
    UserJPAService service;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        service.delete(Integer.parseInt(id));
        response.sendRedirect(request.getContextPath());
    }
}
