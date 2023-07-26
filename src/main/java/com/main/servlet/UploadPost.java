package com.main.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.daos.PostDao;
import com.dto.PostDto;
import com.handleImages.ImageCRUD;

/**
 * Servlet implementation class Upload
 */
@MultipartConfig
@WebServlet("/upload")
public class UploadPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostDao postDao;
	PostDto post;
	private final String PATH = "C:/Users/MSI/eclipse-workspace/javaProject/src/main/webapp/postImages/";
       
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			postDao = new PostDao();
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Part newImage = request.getPart("newPostImage");
		String newImageName = newImage.getSubmittedFileName();
		String oldImageName = request.getParameter("oldPostImage");
		int id = Integer.parseInt(request.getParameter("id"));
		String description = request.getParameter("description");
		int userId = Integer.parseInt(request.getParameter("userId"));
		
		String uploadImage = "";
		
		if(newImage.getSize() > 0) {
			try {
				uploadImage = ImageCRUD.getHashedImageName(newImageName);
				ImageCRUD.savephoto(uploadImage, newImage, PATH);
				ImageCRUD.deletePhoto(PATH, oldImageName);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			uploadImage = oldImageName;
		}
		
		post = new PostDto(id, description, uploadImage);
			
		System.out.println(post);
				
		try {
			postDao.upDate(post);
			response.sendRedirect("profile?profile_id=" + userId);
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}

	}

}
