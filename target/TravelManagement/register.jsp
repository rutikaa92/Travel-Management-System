<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Register</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }
        body { font-family: sans-serif; background: #f0f4f8; display: flex;
               align-items: center; justify-content: center; min-height: 100vh; }
        .card { background: #fff; padding: 2rem; border-radius: 12px;
                box-shadow: 0 4px 20px rgba(0,0,0,0.1); width: 360px; }
        h2 { text-align: center; margin-bottom: 1.5rem; color: #1a202c; }
        input { width: 100%; padding: 0.75rem; margin-bottom: 1rem;
                border: 1px solid #e2e8f0; border-radius: 8px; font-size: 14px; }
        button { width: 100%; padding: 0.75rem; background: #4f46e5;
                 color: white; border: none; border-radius: 8px;
                 cursor: pointer; font-size: 15px; font-weight: 600; }
        button:hover { background: #4338ca; }
        .error { color: red; font-size: 13px; margin-bottom: 1rem; text-align: center; }
        .link { text-align: center; margin-top: 1rem; font-size: 13px; }
        .link a { color: #4f46e5; text-decoration: none; }
    </style>
</head>
<body>
<div class="card">
    <h2>Create Account</h2>
    <% if(request.getAttribute("error") != null) { %>
        <p class="error">${error}</p>
    <% } %>
    <form method="post" action="${pageContext.request.contextPath}/register">
        <input type="text" name="name" placeholder="Full Name" required/>
        <input type="email" name="email" placeholder="Email" required/>
        <input type="password" name="password" placeholder="Password" required/>
        <button type="submit">Register</button>
    </form>
    <div class="link">Already have an account?
        <a href="${pageContext.request.contextPath}/login">Login</a>
    </div>
</div>
</body>
</html>