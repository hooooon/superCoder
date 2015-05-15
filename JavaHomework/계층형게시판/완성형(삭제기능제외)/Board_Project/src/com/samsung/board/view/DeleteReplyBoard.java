package com.samsung.board.view;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samsung.board.dao.BoardDAO;
import com.samsung.board.vo.BoardVO;

public class DeleteReplyBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO vo = new BoardVO();
		BoardDAO dao = new BoardDAO();
		
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		vo.setGroup_seq(Integer.parseInt(request.getParameter("group_seq")));
		dao.deleteReplyBoard(vo);
		
		response.sendRedirect("getBoardList");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
