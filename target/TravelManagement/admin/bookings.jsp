<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Bookings</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: sans-serif; background: #f0f4f8; }
        nav { background: #1a202c; color: white; padding: 1rem 2rem;
              display: flex; justify-content: space-between; align-items: center; }
        nav a { color: #a0aec0; text-decoration: none; margin-left: 1rem; font-size: 14px; }
        .container { padding: 2rem; max-width: 1100px; margin: 0 auto; }
        h2 { margin-bottom: 1rem; }
        table { width: 100%; border-collapse: collapse; background: #fff;
                border-radius: 10px; overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        th { background: #2d3748; color: white; padding: 0.75rem 1rem; text-align: left; }
        td { padding: 0.75rem 1rem; border-bottom: 1px solid #e2e8f0; font-size: 14px; }
        .del-btn { padding: 0.4rem 0.9rem; background: #e53e3e; color: white;
                   border: none; border-radius: 6px; cursor: pointer; font-size: 13px; }
    </style>
</head>
<body>
<nav>
    <strong>⚙ Admin Panel</strong>
    <div>
        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin/packages">Packages</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
<div class="container">
    <h2>All Bookings</h2>
    <table>
        <tr><th>ID</th><th>User</th><th>Package</th><th>Date</th><th>Amount</th><th>Status</th><th>Action</th></tr>
        <c:forEach var="b" items="${bookings}">
        <tr>
            <td>${b.id}</td>
            <td>${b.userName}</td>
            <td>${b.packageName}</td>
            <td>${b.bookingDate}</td>
            <td>₹${b.totalPrice}</td>
            <td>${b.status}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/admin/bookings"
                      style="display:inline">
                    <input type="hidden" name="id" value="${b.id}"/>
                    <button class="del-btn" onclick="return confirm('Cancel this booking?')">Cancel</button>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>