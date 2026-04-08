<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>User Dashboard</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: sans-serif; background: #f0f4f8; }
        nav { background: #4f46e5; color: white; padding: 1rem 2rem;
              display: flex; justify-content: space-between; align-items: center; }
        nav a { color: white; text-decoration: none; margin-left: 1rem; font-size: 14px; }
        .container { padding: 2rem; max-width: 1100px; margin: 0 auto; }
        h2 { margin-bottom: 1.5rem; color: #1a202c; }
        .grid { display: grid; grid-template-columns: repeat(auto-fill, minmax(280px, 1fr)); gap: 1.5rem; }
        .card { background: #fff; border-radius: 12px; padding: 1.5rem;
                box-shadow: 0 2px 8px rgba(0,0,0,0.08); }
        .card h3 { color: #1a202c; margin-bottom: 0.5rem; }
        .card p { color: #718096; font-size: 14px; margin-bottom: 0.5rem; }
        .price { color: #4f46e5; font-weight: 700; font-size: 18px; margin-bottom: 1rem; }
        .btn { display: inline-block; padding: 0.6rem 1.2rem; background: #4f46e5;
               color: white; border: none; border-radius: 8px; cursor: pointer;
               font-size: 14px; text-decoration: none; }
        .btn:hover { background: #4338ca; }
    </style>
</head>
<body>
<nav>
    <strong>✈ Travel System</strong>
    <div>
        <span>Hello, ${sessionScope.loggedUser.name}</span>
        <a href="${pageContext.request.contextPath}/booking">My Bookings</a>
        <a href="${pageContext.request.contextPath}/logout">Logout</a>
    </div>
</nav>
<div class="container">
    <h2>Available Travel Packages</h2>
    <% if("unavailable".equals(request.getParameter("error"))) { %>
        <p style="color:red;margin-bottom:1rem">Package not available or fully booked.</p>
    <% } %>
    <div class="grid">
    <c:forEach var="pkg" items="${packages}">
        <div class="card">
            <h3>${pkg.name}</h3>
            <p>📍 ${pkg.destination}</p>
            <p>📅 ${pkg.durationDays} days</p>
            <p>🪑 ${pkg.availableSlots} slots left</p>
            <p>${pkg.description}</p>
            <div class="price">₹${pkg.price}</div>
            <form method="post" action="${pageContext.request.contextPath}/booking">
                <input type="hidden" name="packageId" value="${pkg.id}"/>
                <button class="btn" type="submit">Book Now</button>
            </form>
        </div>
    </c:forEach>
    </div>
</div>
</body>
</html>