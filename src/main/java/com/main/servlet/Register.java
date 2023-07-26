package com.main.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.daos.UserDao;
import com.dto.UserDto;
import com.handleImages.ImageCRUD;

/**
 * Servlet implementation class Register
 */
@MultipartConfig
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private UserDao userDao;
	PrintWriter out;
	
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
		String email = request.getParameter("email");
		String cPassword = request.getParameter("confirmpw");
		Part file = request.getPart("profileImage");
		
		String imageName = "";
		if(file.getSize() > 0) {
			try {
				imageName = ImageCRUD.getHashedImageName(file.getSubmittedFileName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				e.getMessage();
			}
		} else {
			imageName = "dummy.png";
		}
		
		UserDto user = new UserDto(name, password, email, imageName);
		
		if(password.equals(cPassword)) {
			try {
				userDao.insert(user, file);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			out.println("<script> alert(\"Sucessfully registered!\"); window.location.replace(\"login.jsp\"); </script>");
		} else {
			out.println("<script> alert(\"Password do not match!\"); window.location.replace(\"login.jsp\"); </script>");
		}
	}

}
