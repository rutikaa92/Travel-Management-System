package com.travel.controller;

import com.travel.dao.PackageDAO;
import com.travel.model.User;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/user/dashboard")
public class UserDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = (User) session.getAttribute("loggedUser");
        if ("ADMIN".equals(user.getRole())) {
            res.sendRedirect(req.getContextPath() + "/admin/dashboard");
            return;
        }
        req.setAttribute("packages", new PackageDAO().getAllPackages());
        req.getRequestDispatcher("/user/dashboard.jsp").forward(req, res);
    }
}