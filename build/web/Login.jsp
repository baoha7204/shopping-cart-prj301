<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link href="css/styles.css" rel="stylesheet" type="text/CSS"/>
    </head>
    <body>
        <%
            String error = (String) session.getAttribute("ERROR_LOGIN");
            if (error == null)
                error = "";
        %>

        <h2 class="error"><%= error%></h2>

        <form action="MainController" method="POST">

            <h1>Login Page</h1>

            <div class="box"><label>Username</label><input type="text" name="username"/></div>
            <div class="box"><label>Password</label><input type="password" name="password" /></div> 
            <div class="box">
                <input type="submit" value="Login" name="action" class="marginleft"/>
                <input type="reset" value="Reset" />
                <button type="button"><a style="text-decoration: none; color: black" href="Register.jsp">Register</a></button>
            </div>
        </form>
    </body>
</html>
