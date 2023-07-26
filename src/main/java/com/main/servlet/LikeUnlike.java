package com.main.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.daos.LikeUnlikeDao;
import com.dto.LikeUnlikeDto;

@WebServlet("/likeunlike")
public class LikeUnlike extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	LikeUnlikeDao likeUnlikeDao;
	LikeUnlikeDto likeUnlike;
       
	@Override
		public void init() throws ServletException {
			// TODO Auto-generated method stub
			likeUnlikeDao = new LikeUnlikeDao();
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		HttpSession session = request.getSession();
		
		int id = Integer.parseInt(request.getParameter("id"));
		int userId = (int) session.getAttribute("session_id");
		
		likeUnlike = new LikeUnlikeDto(id, userId);
		
		try {
			
			if(likeUnlikeDao.check(likeUnlike)) {
				likeUnlikeDao.delete(likeUnlike);
				
			} else {
				likeUnlikeDao.insert(likeUnlike);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
			e.getMessage();
		}
	}

}
