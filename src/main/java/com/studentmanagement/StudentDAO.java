package com.studentmanagement;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.core.Logger;

public class StudentDAO {
	private static Logger logger=(Logger) LogManager.getLogger(DBConnection.class);
	public int delete(HttpServletRequest req) {
		int k=0;
		try {
			
			Connection	con=DBConnection.getcon();
			PreparedStatement ps=con.prepareStatement("delete from student where name=? and password=?");
			ps.setString(1, req.getParameter("name"));
			ps.setString(2, req.getParameter("pwd"));
			 k=ps.executeUpdate();
		}catch(Exception e) {
			logger.info("delete dao called");
		}
		return k;
		
	}
	public int insertData(HttpServletRequest req) {
		 int k=0;
		try {
			Connection con=DBConnection.getcon();
			PreparedStatement ps=con.prepareStatement("insert into student values(?,?,?,?,?,?)");
			ps.setString(1, req.getParameter("name"));
			ps.setLong(2, Long.parseLong(req.getParameter("phone")));
			ps.setString(3, req.getParameter("email"));
			ps.setString(4, req.getParameter("addr"));
			ps.setString(5, req.getParameter("course"));
			ps.setString(6, req.getParameter("pword"));
			k=ps.executeUpdate();
		}catch(Exception e) {
			e.printStackTrace();
		}
		return k;
		
	}
	public ArrayList<StudentBean> view(){
		ArrayList<StudentBean> al=new ArrayList<StudentBean>();
		try {
			Connection con=DBConnection.getcon();
			PreparedStatement ps=con.prepareStatement("select * from student");
			ResultSet rs=ps.executeQuery();
			while(rs.next()) {
				StudentBean sb=new StudentBean();
				sb.setName(rs.getString(1));
				sb.setPhone(rs.getLong(2));
				sb.setEmail(rs.getString(3));
				sb.setAddress(rs.getString(4));
				sb.setCourse(rs.getString(5));
				sb.setPassword(rs.getString(6));
				al.add(sb);
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return al;
		
	}
	public StudentBean login(HttpServletRequest req) {
		StudentBean sb=null;
		try {
			Connection con=DBConnection.getcon();
			PreparedStatement ps=con.prepareStatement("select * from student where name=? and password=?");
			ps.setString(1, req.getParameter("uname"));
			ps.setString(2, req.getParameter("pwd"));
			ResultSet rs=ps.executeQuery();
			if(rs.next()) {
				sb=new StudentBean();
				sb.setName(rs.getString(1));
				sb.setPhone(rs.getLong(2));
				sb.setEmail(rs.getString(3));
				sb.setAddress(rs.getString(4));
				sb.setCourse(rs.getString(5));
				sb.setPassword(rs.getString(6));
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
		return sb;
	}
}
