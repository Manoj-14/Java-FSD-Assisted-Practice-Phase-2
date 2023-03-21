package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/list")
public class DbConnection extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection connection = null;
	PreparedStatement preparedStatement = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext context = config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String user = context.getInitParameter("dbuser");
			String password = context.getInitParameter("password");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, user, password);
			preparedStatement = connection.prepareStatement("call add_product(?,?)");
		} catch (SQLException se) {
			se.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		try {
			
			preparedStatement.setString(1,"Earphone" );
			preparedStatement.setInt(2,1499);
			
			int res = preparedStatement.executeUpdate();
			PrintWriter out = resp.getWriter();
			if(res > 0) out.println("Stored procedure executed");
			else out.println("Stored procedure execution failure");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void destroy() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

}
