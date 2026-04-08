<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Manage Packages</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: sans-serif; background: #f0f4f8; }
        nav { background: #1a202c; color: white; padding: 1rem 2rem;
              display: flex; justify-content: space-between; align-items: center; }
        nav a { color: #a0aec0; text-decoration: none; margin-left: 1rem; font-size: 14px; }
        .container { padding: 2rem; max-width: 1100px; margin: 0 auto; }
        h2 { margin-bottom: 1rem; }
        form.add-form { background: #fff; padding: 1.5rem; border-radius: 12px;
                        box-shadow: 0 2px 8px rgba(0,0,0,0.08); margin-bottom: 2rem;
                        display: grid; grid-template-columns: 1fr 1fr; gap: 1rem; }
        form.add-form h3 { grid-column: 1/-1; margin-bottom: 0.5rem; }
        input, textarea { width: 100%; padding: 0.6rem; border: 1px solid #e2e8f0;
                          border-radius: 8px; font-size: 14px; }
        textarea { grid-column: 1/-1; resize: vertical; min-height: 70px; }
        .btn-row { grid-column: 1/-1; }
        button { padding: 0.6rem 1.4rem; background: #4f46e5; color: white;
                 border: none; border-radius: 8px; cursor: pointer; font-size: 14px; margin-right: 8px; }
        .del-btn { background: #e53e3e; }
        table { width: 100%; border-collapse: collapse; background: #fff;
                border-radius: 10px; overflow: hidden;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        th { background: #2d3748; color: white; padding: 0.75rem 1rem; text-align: left; }
        td { padding: 0.75rem 1rem; border-bottom: 1px solid #e2e8f0; font-size: 14px; }
    </style>
</head>
<body>
<nav>
    <strong>⚙ Admin Panel</strong>
    <div>
        <a href="${pageContext.request.contextPath}/admin/dashboard">Dashboard</a>
        <a href="${pageContext.request.contextPath}/admin/bookings">Bookings</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
<div class="container">
    <h2>Manage Packages</h2>
    <form class="add-form" method="post" action="${pageContext.request.contextPath}/admin/packages">
        <h3>Add New Package</h3>
        <input type="text" name="name" placeholder="Package Name" required/>
        <input type="text" name="destination" placeholder="Destination" required/>
        <input type="number" name="durationDays" placeholder="Duration (days)" required/>
        <input type="number" name="price" placeholder="Price (₹)" step="0.01" required/>
        <input type="number" name="availableSlots" placeholder="Available Slots" required/>
        <textarea name="description" placeholder="Description"></textarea>
        <div class="btn-row">
            <button type="submit">Add Package</button>
        </div>
    </form>

    <table>
        <tr><th>ID</th><th>Name</th><th>Destination</th><th>Days</th><th>Price</th><th>Slots</th><th>Actions</th></tr>
        <c:forEach var="p" items="${packages}">
        <tr>
            <td>${p.id}</td>
            <td>${p.name}</td>
            <td>${p.destination}</td>
            <td>${p.durationDays}</td>
            <td>₹${p.price}</td>
            <td>${p.availableSlots}</td>
            <td>
                <form method="post" action="${pageContext.request.contextPath}/admin/packages"
                      style="display:inline">
                    <input type="hidden" name="action" value="delete"/>
                    <input type="hidden" name="id" value="${p.id}"/>
                    <button class="del-btn" type="submit"
                            onclick="return confirm('Delete this package?')">Delete</button>
                </form>
            </td>
        </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>