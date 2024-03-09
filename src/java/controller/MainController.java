package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.bookCartDTO;
import models.cartDTO;

public class MainController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    private static final String Login = "LoginController";
    private static final String LogOut = "LogOutController";
    private static final String AddBook = "BookStoreController";
    private static final String ShowCart = "ShowCartController";
    private static final String Register = "RegisterController";
    private static final String RemoveCartItems = "RemoveCartItemController";
    private static final String ShowCartView = "ShowCart.jsp";

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String url = Login;
        HttpSession session = request.getSession();
        String action = request.getParameter("action");
        if (null != action) switch (action) {
            case "Login":
                url = Login;
                break;
            case "LogOut":
                url = LogOut;
                break;
            case "AddBookToCart":
                url = AddBook;
                break;
            case "Payment":
                url = ShowCart;
                break;
            case "Register":
                url = Register;
                break;
            case "ViewCart":
                // total money
                cartDTO cart = (cartDTO) session.getAttribute("CART");
                List<bookCartDTO> bookList = cart.getList();
                float totalPrice = 0;
                for(bookCartDTO item: bookList) {
                    totalPrice += item.getPrice()*item.getQuantity();
                }
                session.setAttribute("totalPrice", totalPrice);
                
                url = ShowCartView;
                break;
            case "RemoveCartItems":
                url = RemoveCartItems;
                break;
            default:
                break;
        }

        request.getRequestDispatcher(url).forward(request, response);
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
