<%--
  Created by IntelliJ IDEA.
  User: Руслан
  Date: 12.02.2019
  Time: 20:43
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login</title>
</head>
<body>
    <div class="container">
        <h2>Log In</h2>

        <form method="POST" action="http://localhost:8080/jdbcdemo_war_exploded/servlet/login" class="form">
            <input name="username" type="text" class="form-control" placeholder="Username"/>
            <input name="password" type="password" class="form-control" placeholder="Password"/>
            <button class="btn btn-lg btn-primary btn-block" type="submit">Log In</button>
        </form>
    </div>
</body>
</html>
