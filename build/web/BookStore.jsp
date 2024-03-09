<%@page import="models.bookDTO"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styles.css" rel="stylesheet" type="text/CSS"/>
    </head>
    <body>
        <form action="MainController" >
            <h1>Book Store</h1>
            <div class="box">
                Book
                <select id="select" name="select" >
                    <% List<bookDTO> list = (List) session.getAttribute("bookList");
                    for(bookDTO b: list) {
                    %>
                    <option value=<%= b.getId() %>><%= b.getTitle()%></option>
                    <% } %>
                </select>
            </div>
            <div class="box">
                <input type="submit" value="AddBookToCart" name="action"/>
                <button><a href="MainController?action=ViewCart">View Cart</a></button>
            </div>
                <div class="box">
                    <button><a href="MainController?action=LogOut">Logout</a></button>
                </div>
        </form>
    </body>
</html>
