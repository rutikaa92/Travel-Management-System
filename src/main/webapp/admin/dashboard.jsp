<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Admin Dashboard</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: sans-serif; background: #f0f4f8; }
        nav { background: #1a202c; color: white; padding: 1rem 2rem;
              display: flex; justify-content: space-between; align-items: center; }
        nav a { color: #a0aec0; text-decoration: none; margin-left: 1.5rem; font-size: 14px; }
        nav a:hover { color: white; }
        .container { padding: 2rem; max-width: 1100px; margin: 0 auto; }
        .stats { display: grid; grid-template-columns: repeat(2, 1fr); gap: 1.5rem; margin-bottom: 2rem; }
        .stat-card { background: #fff; padding: 1.5rem; border-radius: 12px;
                     box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        .stat-card h3 { font-size: 13px; color: #718096; margin-bottom: 0.5rem; }
        .stat-card .num { font-size: 36px; font-weight: 700; color: #4f46e5; }
        h2 { margin: 1.5rem 0 1rem; color: #1a202c; }
        table { width: 100%; border-collapse: collapse; background: #fff;
                border-radius: 10px; overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08); margin-bottom: 2rem; }
        th { background: #2d3748; color: white; padding: 0.8rem 1rem; text-align: left; }
        td { padding: 0.8rem 1rem; border-bottom: 1px solid #e2e8f0; font-size: 14px; }
    </style>
</head>
<body>
<nav>
    <strong>⚙ Admin Panel</strong>
    <div>
        <a href="${pageContext.request.contextPath}/admin/packages">Manage Packages</a>
        <a href="${pageContext.request.contextPath}/admin/bookings">Manage Bookings</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
<div class="container">
    <div class="stats">
        <div class="stat-card">
            <h3>Total Packages</h3>
            <div class="num">${packages.size()}</div>
        </div>
        <div class="stat-card">
            <h3>Total Bookings</h3>
            <div class="num">${bookings.size()}</div>
        </div>
    </div>

    <h2>Recent Bookings</h2>
    <table>
        <tr><th>ID</th><th>User</th><th>Package</th><th>Date</th><th>Amount</th><th>Status</th></tr>
        <c:forEach var="b" items="${bookings}">
        <tr>
            <td>${b.id}</td>
            <td>${b.userName}</td>
            <td>${b.packageName}</td>
            <td>${b.bookingDate}</td>
            <td>₹${b.totalPrice}</td>
            <td>${b.status}</td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>