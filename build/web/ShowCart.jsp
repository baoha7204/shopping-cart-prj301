<%@page import="java.util.List"%>
<%@page import="models.bookCartDTO"%>
<%@page import="models.cartDTO"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="css/styles.css" rel="stylesheet" type="text/CSS"/>
    </head>
    <body>
        <form action="MainController">
            <h1>Your Cart Items</h1>
            <table border='1'>
            <tr>
                <th>No</th>
                <th>Title</th>
                <th>Quantity</th>
                <th>Actions</th>
            </tr>
            <% 
                int no = 0;
                cartDTO cart = (cartDTO) session.getAttribute("CART");
                List<bookCartDTO> list = cart.getList();
                for(bookCartDTO b: list) {
            %>
            <tr>
                <td><%= ++no %></td> 
                <td><%= b.getTitle() %></td>
                <td><%= b.getQuantity() %></td>
                <td><input type="checkbox" name="bookId" value=<%= b.getId()%>></td>
            <% } %>  
            </tr>
            <tr>
                <td colspan="3">
                    <a href="BookStore.jsp">Add More Items To Cart</a>
                </td>
                <td colspan="1">
                    <input type="submit" value="RemoveCartItems" name="action"/>
                </td>
            </tr>

            </table>
            <h2>Total: <%= (float) session.getAttribute("totalPrice") %></h2>
            <div class="box">
                <button><a href="MainController?action=LogOut">Logout</a></button>
            </div>
            <div class="box">
                <button><a href="MainController?action=Payment">Payment</a></button>
            </div>
        </form>
        
    </body>
</html>
