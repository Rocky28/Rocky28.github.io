package p1;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "downloadresume", urlPatterns = {"/downloadresume"})
public class downloadresume extends HttpServlet {
   
/*    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        try {
        } finally {            
            out.close();
        }
    }*/

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
     try {
            String filePath = getServletContext().getRealPath("") + File.separator+"Rocky_Sinha.pdf";
            String str=System.getProperty("user.dir")+filePath;
            System.out.println(filePath);
            File downloadFile = new File(filePath);
            FileInputStream inStream = new FileInputStream(downloadFile);
            String relativePath = getServletContext().getRealPath("");
            System.out.println("relativePath = " + relativePath);
            ServletContext context = getServletContext();
            String mimeType = context.getMimeType(filePath);
            if (mimeType == null) 
            {          
                mimeType = "application/octet-stream";
            }
            System.out.println("MIME type: " + mimeType);
            response.setContentType(mimeType);
            response.setContentLength((int) downloadFile.length());
            String headerKey = "Content-Disposition";
            String headerValue = String.format("attachment; filename=\"%s\"", downloadFile.getName());
            response.setHeader(headerKey, headerValue);
            OutputStream outStream = response.getOutputStream();
            byte[] buffer = new byte[4096];
            int bytesRead = -1;
            while ((bytesRead = inStream.read(buffer)) != -1) {
                outStream.write(buffer, 0, bytesRead);
            }
            inStream.close();
            outStream.close();  
            response.sendRedirect("MyPortfolio.jsp");
        //    processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(downloadresume.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    //    processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException 
    {
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
