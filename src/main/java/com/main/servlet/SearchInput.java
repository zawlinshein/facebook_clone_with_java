package com.main.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.connectDatabase.databaseConnection;
import com.daos.LikeUnlikeDao;
import com.dto.LikeUnlikeDto;
import com.dto.PostDto;
import com.dto.UserDto;

/**
 * Servlet implementation class SearchInput
 */
@WebServlet("/search")
public class SearchInput extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final String SEARCH_USER = "SELECT users.id, users.name, users.profie_image FROM users WHERE name LIKE ?";
	private final String SEARCH_POST = "SELECT posts.*, users.name, users.profie_image FROM posts INNER JOIN users ON posts.user_id=users.id WHERE posts.description LIKE ? ORDER BY posts.id DESC";
       
	List<PostDto> posts;
	List<UserDto> users;
	
	LikeUnlikeDao likeUnlikeDao;
	Map<Integer, Integer> postAndLikePair;
	Map<Integer, Boolean> checkLiked;
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			likeUnlikeDao = new LikeUnlikeDao();
		}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		int userId = 0;
		
		if(session.getAttribute("session_id") == null) {
			response.sendRedirect("login.jsp");
			return;
		} else {
			userId = (int) session.getAttribute("session_id");
		}
		
		posts = new ArrayList<>();
		users = new ArrayList<>();
		
		postAndLikePair = new HashMap<>();
		checkLiked = new HashMap<>();
		
		String searchInput = request.getParameter("text");
		System.out.println(searchInput);
		
		try {
			search(searchInput);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (PostDto post : posts) {
			try {
				postAndLikePair.put(post.getId(), likeUnlikeDao.count(post.getId()));
				checkLiked.put(post.getId(), likeUnlikeDao.check(new LikeUnlikeDto(post.getId(), userId)));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
		
		System.out.println("posts : " + posts);
		System.out.println("users : " + users);
		
		request.setAttribute("postResult", posts);
		request.setAttribute("userResult", users);
		
		request.setAttribute("likeCount", postAndLikePair);
		request.setAttribute("checkLike", checkLiked);
		
		request.getRequestDispatcher("searchResult.jsp").forward(request, response);
		
	}
	
	void search(String input) throws SQLException {
		Connection con = null;
		PreparedStatement userPs = null;
		PreparedStatement postPs = null;
		ResultSet userRs = null;
		ResultSet postRs = null;
		try {
			
			con = databaseConnection.getConnection();
			userPs = con.prepareStatement(SEARCH_USER);
			postPs = con.prepareStatement(SEARCH_POST);
			userPs.setString(1, "%" + input + "%");
			postPs.setString(1, "%" + input + "%");
			
			userRs = userPs.executeQuery();
			postRs = postPs.executeQuery();
			
			while(userRs.next()) {
				users.add(new UserDto(userRs.getInt("id"), userRs.getString("name"), userRs.getString("profie_image")));
			}
			
			while(postRs.next()) {
				posts.add(new PostDto(postRs.getInt("id"), postRs.getInt("user_id"), postRs.getString("description"), postRs.getString("post_image"), postRs.getString("name"), postRs.getString("profie_image")));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.getMessage();
			e.printStackTrace();
		} finally {
			con.close();
			userPs.close();
			postPs.close();
			userRs.close();
			postRs.close();
		}
	}

}
