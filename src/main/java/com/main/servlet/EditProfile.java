package com.main.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daos.UserDao;
import com.dto.UserDto;

/**
 * Servlet implementation class EditProfile
 */
@WebServlet("/edit_profile")
public class EditProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;   
	UserDao userDao;
	UserDto user;
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			userDao = new UserDao();
		}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		
		try {
			user = userDao.selectUserInfo(id);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("user", user);
		request.getRequestDispatcher("edit_profile.jsp").forward(request, response);
	}

}
