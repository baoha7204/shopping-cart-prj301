package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.userDAO;
import models.userDTO;

@WebServlet(name = "RegisterController", urlPatterns = {"/RegisterController"})
public class RegisterController extends HttpServlet {

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
        String url = "Register.jsp";
        response.setContentType("text/html;charset=UTF-8");
        
        String username = request.getParameter("username").trim();
        String password = request.getParameter("password").trim();
        String confirmPassword = request.getParameter("confirmPassword").trim();
        String fullname = request.getParameter("fullName").trim();
        boolean isValidated = validateInputs(username, password, confirmPassword, fullname);
        if(!isValidated) {
           response.sendRedirect(url);
           return;
        }
        userDAO dao = new userDAO();
        if (dao.addUser(username, password, fullname)) {
            url = "Login.jsp";
        }
        response.sendRedirect(url);
    }
    
    private boolean validateInputs(String username, String password, String confirmPass, String fullname) {
        // Validate username length
        int usernameLen = username.length();
        if (usernameLen < 6 || usernameLen > 12) {
            return false;
        }
        
        userDAO userDao = new userDAO();
        userDTO user = userDao.getuser(username);
        if(user != null) {
            return false;
        }

        // Validate password length
        int passwordLen = password.length();
        if (passwordLen < 8 || passwordLen > 20) {
            return false;
        }
        
        if (!confirmPass.equals(password)) {
            return false;
        }

        // Validate fullname length
        int fullnameLen = fullname.length();
        if (fullnameLen < 2 || fullnameLen > 50) {
            return false;
        }

        // All inputs are valid
        return true;
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
