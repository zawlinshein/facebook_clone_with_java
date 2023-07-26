package com.main.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.daos.PostDao;
import com.dto.PostDto;

/**
 * Servlet implementation class EditPost
 */
@WebServlet("/edit_post")
public class EditPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostDao postDao;
	PostDto post;
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			postDao = new PostDao();
		}
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int postId = Integer.parseInt(request.getParameter("id"));
		
		try {
			post = postDao.selectById(postId);
			if(post != null) {
				request.setAttribute("post", post);
				request.getRequestDispatcher("edit_post.jsp").forward(request, response);
			}
			System.out.println(post);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
