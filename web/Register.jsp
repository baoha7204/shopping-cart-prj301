<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Register Page</title>
        <link href="css/styles.css" rel="stylesheet" type="text/CSS"/>
    </head>
    <body>
        <form action="MainController" method="post">

            <h1>Register Page</h1>

            <div class="box"><label>Username</label><input type="text" name="username"/> (6 - 12 chars)</div>
            <div class="box"><label>Password</label><input type="password" name="password" /> (8 - 20 chars)</div>
            <div class="box"><label>Confirm Pass</label><input type="password" name="confirmPassword" /></div>
            <div class="box"><label>Full Name</label><input type="text" name="fullName" /> (2 - 50 chars)</div>
            <div class="box">
                <input type="submit" value="Register" name="action" class="marginleft"/>
                <input type="reset" value="Reset" />
            </div>

        </form>
    </body>
</html>
