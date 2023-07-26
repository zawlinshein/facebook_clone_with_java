package com.main.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daos.PostDao;

/**
 * Servlet implementation class DeletePost
 */
@WebServlet("/deletePost")
public class DeletePost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostDao postDao;
       
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			postDao = new PostDao();
		}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int postId = Integer.parseInt(request.getParameter("id"));
		try {
			postDao.delete(postId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
