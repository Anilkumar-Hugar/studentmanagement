package com.studentmanagement;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;
import lombok.Getter;
import lombok.Setter;
@SuppressWarnings("serial")
@WebServlet("/login")
@Setter @Getter
public class LoginServlet extends HttpServlet{
	private static Logger logger=(Logger) LogManager.getLogger(LoginServlet.class.getName());
	@Override
	protected void doPost(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException{
		StudentDAO ad=new StudentDAO();
		res.setContentType("text/html");
		PrintWriter pw=res.getWriter();
		HttpSession hs=req.getSession(true);
		if(hs!=null) {
			StudentBean sb=ad.login(req);
		if(sb==null) {
			pw.println("STUDENT DETAILS DOES NOT EXIST. PLEASE INSERT DETAILS");
			logger.error("User details not exist");
			RequestDispatcher rd=req.getRequestDispatcher("studentEnroll.html");
			rd.include(req, res);
		}
		   else {
			String uname=sb.getName();
			String pword=sb.getPassword();
			hs.setAttribute("name", uname);
			if(uname.equals(req.getParameter("uname"))&&pword.equals(req.getParameter("pwd"))) {
				pw.println("WELCOME USER:  "+uname);
				hs.setMaxInactiveInterval(10);
				logger.info("logged in successfully");
			}
			else {
				pw.println("INVALID CREDENTIAL PLEASE LOGIN AGAIN..");
				logger.warn("invalid login credential given");
				hs.setMaxInactiveInterval(10);
			}
			
			RequestDispatcher rd=req.getRequestDispatcher("link.html");
			rd.include(req, res);
			hs.setMaxInactiveInterval(10);
		    }
		}
		else {
			pw.println("session expired please login again..");
			
			RequestDispatcher rd=req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
	}
}
