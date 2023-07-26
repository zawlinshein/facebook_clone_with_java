package com.main.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daos.LikeUnlikeDao;
import com.daos.PostDao;
import com.daos.UserDao;
import com.dto.LikeUnlikeDto;
import com.dto.PostDto;
import com.dto.UserDto;

/**
 * Servlet implementation class Profile
 */
@WebServlet("/profile")
public class Profile extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao;
	PostDao postDao;
	LikeUnlikeDao likeUnlikeDao;
	
	UserDto user;
	ArrayList<PostDto> currentUserPosts;
	Map<Integer, Integer> likeCount;
	Map<Integer, Boolean> checkLike;
	
    @Override
    	public void init() throws ServletException {
    		// TODO Auto-generated method stub
    		userDao = new UserDao();
    		postDao = new PostDao();
    		likeUnlikeDao = new LikeUnlikeDao();
    	} 

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		likeCount = new HashMap<>();
		checkLike = new HashMap<>();
		int id = Integer.parseInt(request.getParameter("profile_id"));
		HttpSession session = request.getSession();
		
		int sessionId = 0;
		
		if(session.getAttribute("session_id") == null) {
			response.sendRedirect("login.jsp");
		} else {
			sessionId = (int) session.getAttribute("session_id");
		}
		
		 
		
		try {
			user = userDao.selectById(id);
			currentUserPosts = postDao.selectPostsById(id);
			
			for(PostDto post : currentUserPosts) {
				likeCount.put(post.getId(), likeUnlikeDao.count(post.getId()));
				checkLike.put(post.getId(), likeUnlikeDao.check(new LikeUnlikeDto(post.getId(), sessionId)));
			}
			
			request.setAttribute("likeCount", likeCount);
			request.setAttribute("checkLike", checkLike);
			request.setAttribute("currentUser", user);
			request.setAttribute("userPosts", currentUserPosts);
			request.getRequestDispatcher("profile.jsp").forward(request, response);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		
	}


}
