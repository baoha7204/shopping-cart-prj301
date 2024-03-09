package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.bookDAO;
import models.cartDTO;
import models.userDAO;
import models.userDTO;

@WebServlet(name = "LoginController", urlPatterns = {"/LoginController"})
public class LoginController extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        response.setContentType("text/html; charset=UTF-8");
        String url = "Login.jsp";
        HttpSession session = request.getSession();
        userDAO user = new userDAO();
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        userDTO inforuser = user.getuser(username, password);
        if (inforuser != null) {
            session.setAttribute("fullname", inforuser.getFullname());
            session.setAttribute("username", inforuser.getUsername());
            
            bookDAO bookDao = new bookDAO();
            session.setAttribute("bookList", bookDao.getBooks());
            
            cartDTO cart = new cartDTO(inforuser);
            session.setAttribute("CART", cart);
            url = "BookStore.jsp";
        } else {
            session.setAttribute("ERROR_LOGIN", "Invalid Username or Password. Please login again!!!");
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
