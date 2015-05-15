package com.samsung.board.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samsung.board.dao.BoardDAO;
import com.samsung.board.vo.BoardVO;
public class AddReplyBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO vo = new BoardVO();
		BoardDAO dao = new BoardDAO();
		
		int currentSeq=0;
		if(!request.getParameter("currentSeq").equals("")){
			currentSeq = Integer.parseInt(request.getParameter("currentSeq"));
		} 
		
		String title = request.getParameter("title");
		String nickname = request.getParameter("nickname");
		String content = request.getParameter("content");
		String userid = request.getParameter("userid");
		
		
		vo.setTitle(title);
		vo.setNickname(nickname);
		vo.setContent(content);
		vo.setUserid(userid);
		
		dao.addReplyBoard(vo, currentSeq);
		
		response.sendRedirect("getBoardList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
