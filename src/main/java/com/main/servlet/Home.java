package com.main.servlet;

import java.io.IOException;
import java.sql.SQLException;
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


@WebServlet("/sendUserObject")
public class Home extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDao userDao;
	PostDao postDao;
	UserDto currentUser;
	LikeUnlikeDao likeUnlikeDao;
	ArrayList<PostDto> allPosts;
	ArrayList<UserDto> allUsers;
	ArrayList<LikeUnlikeDto> likeUnlike;
	ArrayList<Integer> likedPostCount;
	Map<Integer, Integer> postAndLikePair;
	Map<Integer, Boolean> checkLiked;
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			userDao = new UserDao();
			postDao = new PostDao();
			likeUnlikeDao = new LikeUnlikeDao();
		}
       
	@Override
		protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
			// TODO Auto-generated method stub
			likedPostCount = new ArrayList<>();
			postAndLikePair = new HashMap<>();
			checkLiked = new HashMap<>();
			HttpSession session = req.getSession();
			int id = (int)session.getAttribute("session_id");
			try {
				currentUser = userDao.selectById(id);
				
				
				
				allUsers = userDao.selectAll();
				allPosts = postDao.selectAll();
				likeUnlike = likeUnlikeDao.selectAll();
				for (LikeUnlikeDto likeUnlike : likeUnlike) {
					likedPostCount.add(likeUnlikeDao.count(likeUnlike.getPostId()));
					
				}
				for(int i = 0; i < likeUnlike.size(); i++) {
					
					LikeUnlikeDto localVariable = likeUnlike.get(i);
					int postId = localVariable.getPostId();
					int count = likedPostCount.get(i);
					checkLiked.put(postId, likeUnlikeDao.check(new LikeUnlikeDto(postId, id)));
					postAndLikePair.put(postId, count);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(currentUser != null & allUsers != null & allPosts != null) {
				session.setAttribute("currentUserObject", currentUser);
				req.setAttribute("checkLiked",checkLiked);
				req.setAttribute("likedCountForPosts", postAndLikePair);
				req.setAttribute("allUserArrayList", allUsers);
				req.setAttribute("allPosts", allPosts);
				req.getRequestDispatcher("/home.jsp").forward(req, resp);
			}
		}

}
