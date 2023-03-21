package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/DbConnection")
public class DbConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext context = config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String user = context.getInitParameter("dbuser");
			String password = context.getInitParameter("password");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl,user,password);
		}catch(SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		PrintWriter out = resp.getWriter();
		if(connection != null) {
			out.println("JDBC Connection Successfull");
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			out.println("JDBC Connection Closed");
		}
		else {
			out.println("JDBC not connected");
		}
		
	}

}
