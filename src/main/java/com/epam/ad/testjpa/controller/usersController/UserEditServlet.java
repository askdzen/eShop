package com.epam.ad.testjpa.controller.usersController;


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


@WebServlet("userEditServlet")
public class UserEditServlet extends HttpServlet {
    @Inject
    UserJPAService service;
    @Inject
    RoleJPAService roleJPAService;
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email=request.getParameter("email");
        String id = request.getParameter("id");
        String role=request.getParameter("role");
        User user =service.getById(Integer.parseInt(id),"uid");
        user.setUsername(username);
        user.setPassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setRole(roleJPAService.getRoleByName(role));
        service.update(user);
        response.sendRedirect(request.getContextPath());
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        User user = service.getById(Integer.parseInt(id),"uid");
        request.setAttribute("username",user.getUsername());
        request.setAttribute("password",user.getPassword());
        request.setAttribute("firstName",user.getFirstName());
        request.setAttribute("lastName",user.getLastName());
        request.setAttribute("email",user.getEmail());
        request.setAttribute("roles",roleJPAService.getAll());
//        request.setAttribute("role",user.getRole().getRole());
        request.setAttribute("id",user.getId());
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/userEdit.jsp");
        requestDispatcher.forward(request, response);
    }
}
