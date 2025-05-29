package ExamplePackage;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public LoginServlet() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        try {
            // Create user bean and set username and password
            UserBean user = new UserBean();
            user.setUserName(request.getParameter("un"));
            user.setPassword(request.getParameter("pw"));
            
            // Authenticate user
            user = UserDAO.login(user);
            
            // If user is valid
            if (user.isValid()) {
                HttpSession session = request.getSession(true);
                session.setAttribute("currentSessionUser", user);
                response.sendRedirect("userLogged.jsp"); // Logged-in page
            } 
            // If user is not valid
            else {
                response.sendRedirect("invalidLogin.jsp"); // Error page
            }
        } catch (Throwable ex) {
            System.out.println("Error in LoginServlet: " + ex);
            ex.printStackTrace();
        }
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        doGet(request, response);
    }
}