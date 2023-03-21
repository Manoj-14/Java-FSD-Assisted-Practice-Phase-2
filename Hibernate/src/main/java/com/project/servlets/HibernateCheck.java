package com.project.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;

import com.project.domines.Eproduct;
import com.project.utils.HibernateUtil;

import java.util.List;


@WebServlet("/HibernateCheck")
public class HibernateCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Session session = HibernateUtil.getSessionFactory().openSession();
		PrintWriter out = response.getWriter();
		session.beginTransaction();
		Eproduct product = new Eproduct("Laptop",120000);
		session.save(product);
		session.getTransaction().commit();
		session.close();
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
