package com.main.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.security.auth.message.callback.PrivateKeyCallback.Request;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daos.UserDao;
/**
 * Servlet implementation class Login
 */
@WebServlet("/home")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PrintWriter out;
	UserDao userDao;
	
	static int sessionId = 0;

	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			userDao = new UserDao();
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		out = response.getWriter();
		
		String name = request.getParameter("userName");
		String password = request.getParameter("userPassword");
		UserDao userDao = new UserDao();
		HttpSession session = request.getSession();
		int userId = 0;
		
		try {
			userId = userDao.checkUser(name, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sessionId = userId > 0 ? userId : (session.getAttribute("session_id") != null ? (int)session.getAttribute("session_id") : 0 );
		
		if(sessionId > 0) {
			session.setAttribute("session_id", sessionId);
			request.getRequestDispatcher("/sendUserObject").forward(request, response);
		} else {
			out.println("<script> alert(\"Wrong user name or wrong password please try again.\"); window.location.replace(\"login.jsp\"); </script>");
		}
	}
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = req.getSession();
		if(session.getAttribute("session_id") == null) {
			resp.sendRedirect("login.jsp");
		}
		doPost(req, resp);
	}
	
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		out.close();
	}
}
