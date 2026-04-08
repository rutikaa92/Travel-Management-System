package com.travel.controller;

import com.travel.dao.*;
import com.travel.model.*;
import com.travel.model.Package;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/booking")
public class BookingServlet extends HttpServlet {

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = (User) session.getAttribute("loggedUser");
        req.setAttribute("bookings", new BookingDAO().getBookingsByUser(user.getId()));
        req.getRequestDispatcher("/user/mybookings.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedUser") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }
        User user = (User) session.getAttribute("loggedUser");
        int packageId = Integer.parseInt(req.getParameter("packageId"));

        Package pkg = new PackageDAO().getPackageById(packageId);
        if (pkg == null || pkg.getAvailableSlots() <= 0) {
            res.sendRedirect(req.getContextPath() + "/user/dashboard?error=unavailable");
            return;
        }

        Booking b = new Booking();
        b.setUserId(user.getId());
        b.setPackageId(packageId);
        b.setTotalPrice(pkg.getPrice());

        new BookingDAO().createBooking(b);
        res.sendRedirect(req.getContextPath() + "/booking?success=booked");
    }
}