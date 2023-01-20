package com.studentmanagement;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

import javax.servlet.annotation.*;

@SuppressWarnings("serial")
@WebServlet("/delete")
public class DeletionServlet extends HttpServlet {
	private static Logger logger = (Logger) LogManager.getLogger(DeletionServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StudentDAO studentData = new StudentDAO();
		int deleteStatus = studentData.delete(req);
		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();
		HttpSession session = req.getSession();
		if (session != null) {
			if (deleteStatus > 0) {
				pw.println("DETAILS DELETED SUCCESSFULLY");
				logger.info("details deleted from DB");
				session.setMaxInactiveInterval(10);
				RequestDispatcher rd = req.getRequestDispatcher("link.html");
				rd.include(req, res);

			} else {
				pw.println("INVALID NAME OR PASSWORD..");
				logger.error("provided details are not correct");
				session.setMaxInactiveInterval(10);
				RequestDispatcher rd = req.getRequestDispatcher("delete.html");
				rd.include(req, res);
				session.setMaxInactiveInterval(10);
			}
		} else {
			pw.println("Session expired please login again");
			logger.info("session expired");
			RequestDispatcher rd = req.getRequestDispatcher("login.html");
			rd.include(req, res);
		}
	}
}
