package com.travel.controller;

import com.travel.dao.UserDAO;
import com.travel.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/register.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
    	String name     = req.getParameter("name") != null ? req.getParameter("name").trim() : "";
    	String email    = req.getParameter("email") != null ? req.getParameter("email").trim() : "";
    	String password = req.getParameter("password") != null ? req.getParameter("password") : "";

        if (name.isEmpty() || email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("/register.jsp").forward(req, res);
            return;
        }

        UserDAO dao = new UserDAO();
        if (dao.emailExists(email)) {
            req.setAttribute("error", "Email already registered.");
            req.getRequestDispatcher("/register.jsp").forward(req, res);
            return;
        }

        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);

        if (dao.register(user)) {
            res.sendRedirect(req.getContextPath() + "/login?success=registered");
        } else {
            req.setAttribute("error", "Registration failed. Try again.");
            req.getRequestDispatcher("/register.jsp").forward(req, res);
        }
    }
}