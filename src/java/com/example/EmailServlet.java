package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "EmailServlet", urlPatterns = {"/EmailServlet"})
public class EmailServlet extends HttpServlet {
    
    @EJB
    private EmailSessionBean emailBean;
    
    /*protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
         finally {            
            out.close();
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        
    //    processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
        HttpSession s=request.getSession(true);
        String from=request.getParameter("email");
        String name=request.getParameter("name");
        String subject=request.getParameter("subject");
        String msg=request.getParameter("comments");
        name=name+" : "+from+" : "+msg;
        if(from.trim().equals(null) || msg.trim().equals(null) || subject.trim().equals(null) || name.trim().equals(null))
        {
            s.setAttribute("Mistake", "Yes");
            response.sendRedirect("index.html");
        }
        s.setAttribute("Mistake", "No");
        try{
            emailBean.sendEmail(from, subject, name);
        }catch(Exception e){s.setAttribute("Mistake", "Yes");}
        response.sendRedirect("MyPortfolio.jsp");
    //    processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
