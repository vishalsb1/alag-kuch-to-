import javax.servlet.*;
import javax.servlet.http.*;
import java.io.*;
import java.util.*;
import java.text.SimpleDateFormat;

public class Session1 extends HttpServlet {
    public void doGet(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        try {
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            
            // Get username from request
            String un = req.getParameter("uname");
            
            // Create session
            HttpSession session = req.getSession(true);
            
            // Store login time and username
            long loginTime = System.currentTimeMillis();
            session.setAttribute("user", un);
            session.setAttribute("loginTime", loginTime);
            
            // Format date for better readability
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            String formattedTime = sdf.format(new Date(loginTime));
            
            // Generate HTML response
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head><title>Welcome</title>");
            out.println("<style>");
            out.println("body { font-family: Arial, sans-serif; text-align: center; }");
            out.println(".time { position: absolute; top: 10px; right: 10px; }");
            out.println(".welcome { margin-top: 50px; }");
            out.println("form { display: inline-block; }");
            out.println("</style>");
            out.println("</head>");
            out.println("<body>");
            out.println("<div class='time'>Login Time: " + formattedTime + "</div>");
            out.println("<div class='welcome'>");
            out.println("<h2>Hello, " + un + "!</h2>");
            out.println("<form method='get' action='session26vib'>");
            out.println("<input type='submit' value='Logout'>");
            out.println("</form>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
