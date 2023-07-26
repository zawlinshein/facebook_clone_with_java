package com.main.servlet;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daos.CommentDao;
import com.daos.LikeUnlikeDao;
import com.daos.PostDao;
import com.daos.UserDao;
import com.dto.CommentDto;
import com.dto.LikeUnlikeDto;
import com.dto.PostDto;
import com.dto.UserDto;

/**
 * Servlet implementation class ShowPost
 */
@WebServlet("/post")
public class ShowPost extends HttpServlet {
	private static final long serialVersionUID = 1L;
	PostDao postDao;
	UserDao userDao;
	CommentDao commentDao;
	LikeUnlikeDao likeUnlikeDao;
	
	ArrayList<UserDto> userForComment;
	ArrayList<CommentDto> comments;
	LikeUnlikeDto likeUnlike;
	int count;
	boolean checkUser;
	UserDto user;
	PostDto post;
       
	@Override
		public void init() throws ServletException {
			postDao = new PostDao();
			userDao = new UserDao();
			commentDao = new CommentDao();
			likeUnlikeDao = new LikeUnlikeDao();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int id = Integer.parseInt(request.getParameter("id"));
		HttpSession session = request.getSession();
		
		int userId = 0;
		
		if(session.getAttribute("session_id") == null) {
			response.sendRedirect("login.jsp");
		} else {
			userId = (int)session.getAttribute("session_id");
		}
		
		likeUnlike = new LikeUnlikeDto(id, userId);
		
		try {
			post = postDao.selectById(id);
			user = userDao.selectById(post.getUserId());
			comments = commentDao.selectAll(id);
			
			count = likeUnlikeDao.count(id);
			checkUser = likeUnlikeDao.check(likeUnlike);
			
			userForComment = userDao.selectUsersById(id);
			
			if(post != null & user != null & comments != null & userForComment != null) {
				request.setAttribute("likeCount", count);
				request.setAttribute("check", checkUser);
				request.setAttribute("post", post);
				request.setAttribute("postOwner", user);
				request.setAttribute("allComments", comments);
				request.setAttribute("commentedUsers", userForComment);
				request.getRequestDispatcher("post.jsp").forward(request, response);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
	}



}
