package com.main.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daos.CommentDao;
import com.daos.UserDao;
import com.dto.CommentDto;
import com.dto.UserDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pusher.rest.Pusher;

/**
 * Servlet implementation class LiveComment
 */
@WebServlet("/livecomment")
public class LiveComment extends HttpServlet {
	private static final long serialVersionUID = 1L;
	UserDto user;
	UserDao userDao;
	
	CommentDao commentDao;
	CommentDto comment;
	
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			userDao = new UserDao();
			commentDao = new CommentDao();
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		int user_id = (int)session.getAttribute("session_id");
		int post_id = Integer.parseInt(request.getParameter("id"));
		String commentText = request.getParameter("comment");
		
		comment = new CommentDto(post_id, user_id, commentText);	
		
		try {
			user = userDao.selectById(user_id);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		
		//convert object into json formet
		
		ObjectMapper mapper = new ObjectMapper();
		
		String jsonComment = mapper.writeValueAsString(comment);
		String jsonUser = mapper.writeValueAsString(user);
		
		//convert object into json formet
		
		//pusher code here
		
		Pusher pusher = new Pusher("1629453", "555a64f8f69f9b757123", "105a49bb52368ac1e1c6");
		pusher.setCluster("ap1");
		pusher.setEncrypted(true);

		pusher.trigger("my-channel", "my-event", "{\"user\":" +jsonUser + ", \"comment\":"+ jsonComment +"}");
		
		//pusher code here
		
		try {
			if(commentDao.insert(comment)) {
				System.out.println("comment inserted into database");
				response.sendRedirect("post.jsp");
			} else {
				System.out.println("this is not working");
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
		
		
	
	}

}
