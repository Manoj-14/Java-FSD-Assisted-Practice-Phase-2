package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
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

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			ServletContext context = config.getServletContext();
			String dburl = context.getInitParameter("dburl");
			String user = context.getInitParameter("dbuser");
			String password = context.getInitParameter("password");
			Class.forName("com.mysql.jdbc.Driver");
			connection = DriverManager.getConnection(dburl, user, password);
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
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("Select * from products");
			resp.setContentType("text/html");
			PrintWriter out = resp.getWriter();
			out.println("<h2>Products</h2>");
			out.println("<table border='1'>");
			out.println("<tr>");
			out.println("<th>Id</th>");
			out.println("<th>Name</th>");
			out.println("<th>Description</th>");
			out.println("<th>Price</th>");
			out.println("</tr>");
			while(rs.next()) {
				out.println("<tr>");
				out.println("<td>"+rs.getInt("id")+"</td>");
				out.println("<td>"+rs.getString("name")+"</td>");
				out.println("<td>"+rs.getString("description")+"</td>");
				out.println("<td>"+rs.getString("price")+"</td>");
				out.println("</tr>");
			}
			out.println("</table>");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
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
