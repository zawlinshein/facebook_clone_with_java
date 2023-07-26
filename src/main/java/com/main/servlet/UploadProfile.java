package com.main.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;
import javax.servlet.jsp.tagext.TryCatchFinally;

import com.daos.UserDao;
import com.dto.UserDto;
import com.handleImages.ImageCRUD;

/**
 * Servlet implementation class UploadProfile
 */
@MultipartConfig
@WebServlet("/uploadprofile")
public class UploadProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDto user;
	UserDao userDao;
	
	private final String PATH = "C:/Users/MSI/eclipse-workspace/javaProject/src/main/webapp/profileImage/";
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			userDao = new UserDao();
		}
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String email = request.getParameter("email");
		String oldImage = request.getParameter("oldImage");
		Part newImage = request.getPart("newImage");
		String uploadProfileImage = "";
		
		if(newImage.getSize() > 0) {
			try {
				uploadProfileImage = ImageCRUD.getHashedImageName(newImage.getSubmittedFileName());
				ImageCRUD.deletePhoto(PATH, oldImage);
				ImageCRUD.savephoto(uploadProfileImage, newImage, PATH);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		} else {
			uploadProfileImage = oldImage;
		}
		
		user = new UserDto(id, name, password, email, uploadProfileImage);
		
		try {
			userDao.upDate(user);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		
		System.out.println(id);
		System.out.println(user);
		
		response.sendRedirect("profile?profile_id=" + id);
	}

}
