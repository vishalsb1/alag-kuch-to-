import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Session2 extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            
            // Get session
            HttpSession session = req.getSession(false);
            
            if (session != null) {
                // Retrieve stored information
                String un = (String) session.getAttribute("user");
                Long loginTime = (Long) session.getAttribute("loginTime");
                
                // Calculate session duration
                long logoutTime = System.currentTimeMillis();
                long duration = (logoutTime - loginTime) / 1000; // duration in seconds
                
                // Format duration
                long hours = duration / 3600;
                long minutes = (duration % 3600) / 60;
                long seconds = duration % 60;
                
                // Invalidate session
                session.invalidate();
                
                // Generate HTML response
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head><title>Logout</title>");
                out.println("<style>");
                out.println("body { font-family: Arial, sans-serif; text-align: center; padding-top: 50px; }");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h2>Thank you, " + un + "!</h2>");
                out.println("<p>Session Duration: " + hours + " hours, " + minutes + " minutes, " + seconds + " seconds</p>");
                out.println("<a href='Session1.html'>Back to Login</a>");
                out.println("</body>");
                out.println("</html>");
            } else {
                out.println("No active session found.");
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
