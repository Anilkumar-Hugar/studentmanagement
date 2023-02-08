package com.studentmanagement;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import javax.servlet.annotation.*;

@SuppressWarnings("serial")
@WebServlet("/enroll")
public class EnrollServlet extends HttpServlet {
	private static Logger logger = LogManager.getLogger(EnrollServlet.class);

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		StudentDAO ad = new StudentDAO();

		res.setContentType("text/html");
		PrintWriter pw = res.getWriter();

		int insertData = ad.insertData(req);
		if (insertData > 0 && ad != null) {
			pw.println("DETAILS ADDED SUCCESSFULLY...");
			logger.info("details addedd successfully");
			RequestDispatcher rd = req.getRequestDispatcher("link.html");
			rd.include(req, res);
		} else {
			pw.println("UNABLE TO ADD DETAILS. PLEASE INSERT DETAILS AGAIN");

			RequestDispatcher rd = req.getRequestDispatcher("studentEnroll.html");
			rd.include(req, res);
		}

	}
}
