package com.main.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.daos.PostDao;
import com.dto.PostDto;
import com.handleImages.ImageCRUD;


@MultipartConfig
@WebServlet("/create_post")
public class CreatePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostDto post;
	PostDao postDao;
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			postDao = new PostDao();
		}
       

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		HttpSession session = request.getSession();
		int user_id = (int) session.getAttribute("session_id");
		String description = request.getParameter("description");
		Part file = request.getPart("post_photo");
		String imageName = "";
		try {
			imageName = ImageCRUD.getHashedImageName(file.getSubmittedFileName());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		post = new PostDto(user_id, description, imageName);
		try {
			postDao.insert(post, file);
			response.sendRedirect("create_post.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
