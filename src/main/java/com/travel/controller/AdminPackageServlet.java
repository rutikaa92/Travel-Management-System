package com.travel.controller;

import com.travel.dao.PackageDAO;
import com.travel.model.Package;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;

@WebServlet("/admin/packages")
public class AdminPackageServlet extends HttpServlet {

    private boolean isAdmin(HttpServletRequest req) {
        HttpSession s = req.getSession(false);
        return s != null && "ADMIN".equals(s.getAttribute("userRole"));
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (!isAdmin(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }
        req.setAttribute("packages", new PackageDAO().getAllPackages());
        req.getRequestDispatcher("/admin/packages.jsp").forward(req, res);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        if (!isAdmin(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String action = req.getParameter("action");
        PackageDAO dao = new PackageDAO();

        if ("delete".equals(action)) {
            dao.deletePackage(Integer.parseInt(req.getParameter("id")));
        } else {
            Package p = new Package();
            String idStr = req.getParameter("id");
            p.setName(req.getParameter("name"));
            p.setDescription(req.getParameter("description"));
            p.setDestination(req.getParameter("destination"));
            p.setDurationDays(Integer.parseInt(req.getParameter("durationDays")));
            p.setPrice(Double.parseDouble(req.getParameter("price")));
            p.setAvailableSlots(Integer.parseInt(req.getParameter("availableSlots")));

            if (idStr != null && !idStr.isEmpty()) {
                p.setId(Integer.parseInt(idStr));
                dao.updatePackage(p);
            } else {
                dao.addPackage(p);
            }
        }
        res.sendRedirect(req.getContextPath() + "/admin/packages");
    }
}