package com.studentmanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.annotation.*;
import java.util.*;

@SuppressWarnings("serial")
@WebServlet("/view")
public class ViewServlet extends HttpServlet {
	
	private static Logger logger = LogManager.getLogger(ViewServlet.class);

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		ArrayList<StudentBean> list = null;
		StudentDAO ad = new StudentDAO();
		list = ad.view();
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession(false);

		if (session != null) {
			pw.println("Welcome user:  " + (String) session.getAttribute("name"));
			pw.println("<body>");
			pw.println("<table border='1px'>");
			pw.println("<tr><th>name</th><th>phone</th><th>email</th><th>address</th><th>course</th>");
			list.forEach(al1 -> {
				pw.println("<tr><td>" + al1.getName() + "</td><td>" + al1.getPhone() + "</td><td>" + al1.getEmail()
						+ "</td><td>" + al1.getAddress() + "</td><td>" + al1.getCourse() + "</td></tr>");
			});
			pw.println("</table>");
			pw.println("</body>");
			logger.info("details have been printed from db");
			session.setMaxInactiveInterval(10);
			RequestDispatcher rd = req.getRequestDispatcher("link.html");
			rd.include(req, res);

		} else {
			pw.println("Session expired please login again...");
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
	}

}
