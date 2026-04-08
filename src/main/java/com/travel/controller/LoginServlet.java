package com.travel.controller;

import com.travel.dao.UserDAO;
import com.travel.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        String email    = req.getParameter("email").trim();
        String password = req.getParameter("password");

        if (email.isEmpty() || password.isEmpty()) {
            req.setAttribute("error", "All fields are required.");
            req.getRequestDispatcher("/login.jsp").forward(req, res);
            return;
        }

        UserDAO dao = new UserDAO();
        User user = dao.login(email, password);

        if (user != null) {
            HttpSession session = req.getSession();
            session.setAttribute("loggedUser", user);
            session.setAttribute("userId", user.getId());
            session.setAttribute("userRole", user.getRole());

            if ("ADMIN".equals(user.getRole())) {
                res.sendRedirect(req.getContextPath() + "/admin/dashboard");
            } else {
                res.sendRedirect(req.getContextPath() + "/user/dashboard");
            }
        } else {
            req.setAttribute("error", "Invalid email or password.");
            req.getRequestDispatcher("/login.jsp").forward(req, res);
        }
    }
}