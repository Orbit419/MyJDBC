<%--
  Created by IntelliJ IDEA.
  User: Руслан
  Date: 14.02.2019
  Time: 14:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
</head>
<body>

<div class="container">
    <h2>Registration</h2>

    <form method="POST" action="http://localhost:8080/jdbcdemo_war_exploded/servlet/registration" class="form">
        Username: <input name="username" type="text" class="form-control" placeholder="Username"/>
        Password: <input name="password" type="password" class="from-control" placeholder="Password"/>
        Mail:     <input name="mail" type="text"/>
        <button class="btn btn-lg btn-primary btn-block" type="submit">Sign Up</button>
    </form>
</div>

</body>
</html>
