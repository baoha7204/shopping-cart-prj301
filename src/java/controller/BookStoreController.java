package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.bookCartDTO;
import models.bookDAO;
import models.bookDTO;
import models.cartDTO;

@WebServlet(name = "BookStoreController", urlPatterns = {"/BookStoreController"})
public class BookStoreController extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String url = "BookStore.jsp";
        HttpSession session = request.getSession();
        cartDTO cart = (cartDTO) session.getAttribute("CART");
        int id = Integer.parseInt(request.getParameter("select"));
        // check book exist
        boolean checkExist = false;
        List<bookCartDTO> list = cart.getList();
        for (bookCartDTO book : list) {
            if (id == book.getId()) {
                checkExist = true;
                book.setQuantity(book.getQuantity() + 1);
                break;
            }
        }
        if (!checkExist) {
            bookDAO dao = new bookDAO();
            bookDTO tempBook = dao.getBook(id);
            bookCartDTO book = new bookCartDTO(1, id, tempBook.getTitle(), tempBook.getPrice());
            list.add(book);
        }
        session.setAttribute("CART", cart);
        // calculate total price
        float totalPrice = 0;
        for (bookCartDTO item : list) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        session.setAttribute("totalPrice", totalPrice);
        
        url = "ShowCart.jsp";
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
