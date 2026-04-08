package com.travel.controller;

import com.travel.dao.BookingDAO;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/bookings")
public class AdminBookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || !"ADMIN".equals(s.getAttribute("userRole"))) {
            res.sendRedirect(req.getContextPath() + "/login"); return;
        }
        req.setAttribute("bookings", new BookingDAO().getAllBookings());
        req.getRequestDispatcher("/admin/bookings.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession s = req.getSession(false);
        if (s == null || !"ADMIN".equals(s.getAttribute("userRole"))) {
            res.sendRedirect(req.getContextPath() + "/login"); return;
        }
        int id = Integer.parseInt(req.getParameter("id"));
        new BookingDAO().deleteBooking(id);
        res.sendRedirect(req.getContextPath() + "/admin/bookings");
    }
}