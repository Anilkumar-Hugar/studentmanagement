package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class StudentDAO {
	private static Logger logger = LogManager.getLogger(StudentDAO.class);

	public int delete(HttpServletRequest req) {
		int delete = 0;
		try {

			Connection con = DBConnection.getcon();
			PreparedStatement ps = con.prepareStatement("delete from student where name=? and password=?");
			ps.setString(1, req.getParameter("name"));
			ps.setString(2, req.getParameter("pwd"));
			delete = ps.executeUpdate();
		} catch (Exception e) {
			logger.info("Data unable to delete from the db");
			logger.catching(e);
		}
		return delete;

	}

	public int insertData(HttpServletRequest req) {
		int insert = 0;
		try {
			Connection con = DBConnection.getcon();
			PreparedStatement ps = con.prepareStatement("insert into student values(?,?,?,?,?,?)");
			ps.setString(1, req.getParameter("name"));
			ps.setLong(2, Long.parseLong(req.getParameter("phone")));
			ps.setString(3, req.getParameter("email"));
			ps.setString(4, req.getParameter("addr"));
			ps.setString(5, req.getParameter("course"));
			ps.setString(6, req.getParameter("pword"));
			insert = ps.executeUpdate();
		} catch (Exception e) {
			logger.info("unable to insert data to db");
			logger.catching(e);
		}

		return insert;

	}

	public ArrayList<StudentBean> view() {
		ArrayList<StudentBean> list = new ArrayList<StudentBean>();
		try {
			Connection con = DBConnection.getcon();
			PreparedStatement ps = con.prepareStatement("select * from student");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				StudentBean sb = new StudentBean();
				sb.setName(rs.getString(1));
				sb.setPhone(rs.getLong(2));
				sb.setEmail(rs.getString(3));
				sb.setAddress(rs.getString(4));
				sb.setCourse(rs.getString(5));
				sb.setPassword(rs.getString(6));
				list.add(sb);
			}
		} catch (Exception e) {
			logger.info("Details have not been retrieved from db");
			logger.catching(e);
		}
		return list;

	}

	public StudentBean login(HttpServletRequest req) {
		StudentBean details = null;
		try {
			Connection con = DBConnection.getcon();
			PreparedStatement ps = con.prepareStatement("select * from student where name=? and password=?");
			ps.setString(1, req.getParameter("uname"));
			ps.setString(2, req.getParameter("pwd"));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				details = new StudentBean();
				details.setName(rs.getString(1));
				details.setPhone(rs.getLong(2));
				details.setEmail(rs.getString(3));
				details.setAddress(rs.getString(4));
				details.setCourse(rs.getString(5));
				details.setPassword(rs.getString(6));
			}
		} catch (Exception e) {
			logger.info("Having issue while retrieving data");
			logger.catching(e);
		}
		return details;
	}
}
