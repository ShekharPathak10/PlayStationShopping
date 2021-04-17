/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package src;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.UUID;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author shekh
 */
public class ValidateConsoleServlet extends HttpServlet {

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
        HttpSession session = request.getSession();
        
        //To show that the console is created message for now! 
        //The method to create console is ready the nextPage needs to be set to
        //ConsoleDetail page to see the console after it is create
        String nextPage = "/ConsoleCreated.jsp";
        
        //Taking in all the values from the form
        String name = request.getParameter("name");
        String color = request.getParameter("color");
        String price = request.getParameter("price");
        String memory = request.getParameter("memory");
        String storage = request.getParameter("storage");
        
        //Create a new object of the ConsoleErrorList type to send the errors to JSP
        ConsoleErrorList errors = new ConsoleErrorList();
        
        //Call all the validation methods and store them on a boolean variable to keep track of the errors
        
        boolean isNameValid = ConsoleValidation.validateName(name, errors);
        boolean isColorValid = ConsoleValidation.validateColor(color, errors);
        boolean isPriceValid = ConsoleValidation.validatePrice(price, errors);
        boolean isMemoryValid = ConsoleValidation.validateMemory(memory, errors);
        boolean isStorageValid = ConsoleValidation.validateStorage(storage, errors);
        
        //Check to see if any errors were occured and if not create that Console
        if(isNameValid && isColorValid && isPriceValid && isMemoryValid && isStorageValid){
            ConsoleList consoles = (ConsoleList)session.getAttribute("consolelist");
            if(consoles == null){
                consoles = new ConsoleList();
            }
            //Console("PlayStation3", UUID.randomUUID(), "Black", 199, 4, "500")
            int numMemory = Integer.parseInt(memory);
            int numPrice = Integer.parseInt(price);
            UUID consoleid = UUID.randomUUID();
                    
            Console C = new Console(name, consoleid, color, numPrice, numMemory, storage);
            //consoles.add(C);
            
            String console_id = consoleid.toString();
            boolean isValid = ConsoleValidation.validateConsoleUnique(name, console_id);
            
            if(isValid){
            consoles.saveConsole(name, consoleid, color, numPrice, numMemory, storage);}
            
            session.setAttribute("currentconsole", consoles.getConsole(name, consoleid.toString()));
            session.setAttribute("consolelist", consoles);
        }
        else{
            nextPage = "/CreateConsole.jsp";
            // Add the validation object error to the session for use by the JSP
            request.setAttribute("errors", errors);
        }
        
        getServletContext().getRequestDispatcher(nextPage).forward(request, response);
        
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
