<%@ include file="common/header.jsp" %>

    <section id="login-form">
        <h2>Login to Your Account</h2>
        <c:if test="${not empty param.error}">
            <p style="color: red;">Invalid username or password. Please try again.</p>
        </c:if>
        <c:if test="${not empty param.logout}">
            <p style="color: green;">You have been logged out successfully.</p>
        </c:if>
        <form action="/doLogin" method="post">
            <div class="form-group">
                <label for="username">Username:</label>
                <input type="text" id="username" name="username" required>
            </div>
            <div class="form-group">
                <label for="password">Password:</label>
                <input type="password" id="password" name="password" required>
            </div>
            <button type="submit" class="btn">Login</button>
        </form>
        <p>Don't have an account? <a href="/register">Register here</a></p>
    </section>

<%@ include file="common/footer.jsp" %>