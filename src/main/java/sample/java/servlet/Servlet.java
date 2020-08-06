package sample.java.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

public class Servlet extends HttpServlet {
	@Override
	public void init() throws ServletException {
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		PrintWriter out = response.getWriter();


		// context-param
		out.println("context-param: " + getServletContext().getInitParameter("context-param-name"));
		// init-param
		out.println("init-param: " + getInitParameter("init-param-name"));


		// session
		HttpSession session = request.getSession();
		//session.removeAttribute("name");
		//session.invalidate();
		//session.setMaxInactiveInterval(-1);
		//session.setAttribute("name", "value");


		//
		//request.setAttribute("name", "value");
		//request.getRequestDispatcher("/").forward(request, response);


		//
		//response.sendRedirect("/");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
	}
}
