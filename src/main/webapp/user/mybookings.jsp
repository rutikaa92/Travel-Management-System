<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>My Bookings</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: sans-serif; background: #f0f4f8; }
        nav { background: #4f46e5; color: white; padding: 1rem 2rem;
              display: flex; justify-content: space-between; align-items: center; }
        nav a { color: white; text-decoration: none; margin-left: 1rem; font-size: 14px; }
        .container { padding: 2rem; max-width: 900px; margin: 0 auto; }
        h2 { margin-bottom: 1.5rem; }
        table { width: 100%; border-collapse: collapse; background: #fff;
                border-radius: 10px; overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        th { background: #4f46e5; color: white; padding: 0.8rem 1rem; text-align: left; }
        td { padding: 0.8rem 1rem; border-bottom: 1px solid #e2e8f0; font-size: 14px; }
        tr:last-child td { border-bottom: none; }
        .status { padding: 3px 10px; border-radius: 20px; font-size: 12px;
                  background: #c6f6d5; color: #276749; }
        .success-msg { color: green; margin-bottom: 1rem; font-size: 14px; }
    </style>
</head>
<body>
<nav>
    <strong>✈ Travel System</strong>
    <div>
        <a href="${pageContext.request.contextPath}/user/dashboard">Browse Packages</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
<div class="container">
    <h2>My Bookings</h2>
    <% if("booked".equals(request.getParameter("success"))) { %>
        <p class="success-msg">✅ Booking confirmed!</p>
    <% } %>
    <c:choose>
    <c:when test="${empty bookings}">
        <p>No bookings yet. <a href="${pageContext.request.contextPath}/user/dashboard">Browse packages</a></p>
    </c:when>
    <c:otherwise>
    <table>
        <tr><th>#</th><th>Package</th><th>Date</th><th>Amount</th><th>Status</th></tr>
        <c:forEach var="b" items="${bookings}" varStatus="s">
        <tr>
            <td>${s.count}</td>
            <td>${b.packageName}</td>
            <td>${b.bookingDate}</td>
            <td>₹${b.totalPrice}</td>
            <td><span class="status">${b.status}</span></td>
        </tr>
        </c:forEach>
    </table>
    </c:otherwise>
    </c:choose>
</div>
</body>
</html>