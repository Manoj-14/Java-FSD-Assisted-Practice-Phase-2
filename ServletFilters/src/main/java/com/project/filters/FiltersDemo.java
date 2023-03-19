package com.project.filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;

@WebFilter("/filtersDemo")
public class FiltersDemo extends HttpFilter implements Filter {

	public FiltersDemo() {
		System.out.println("Filters Constructor");		
	}

	public void destroy() {
		System.out.println("Filters destroyed");
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		System.out.println("Before sending request to servlet");
		chain.doFilter(request, response);
		System.out.println("After getting response from servlet");
	}

	public void init(FilterConfig fConfig) throws ServletException {
		System.out.println("Filters initialization");
	}

}
