package com.travel.controller;

import com.travel.dao.*;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || !"ADMIN".equals(session.getAttribute("userRole"))) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        req.setAttribute("packages", new PackageDAO().getAllPackages());
        req.setAttribute("bookings", new BookingDAO().getAllBookings());
        req.getRequestDispatcher("/admin/dashboard.jsp").forward(req, res);
    }
}