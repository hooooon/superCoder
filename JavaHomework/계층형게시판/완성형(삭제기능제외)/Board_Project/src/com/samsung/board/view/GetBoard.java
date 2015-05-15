package com.samsung.board.view;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samsung.board.dao.BoardDAO;
import com.samsung.board.vo.BoardVO;

public class GetBoard extends HttpServlet {
	private static final long serialVersionUID = 1L;
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardVO vo = new BoardVO();
		BoardDAO dao = new BoardDAO();
		
		vo.setSeq(Integer.parseInt(request.getParameter("seq")));
		BoardVO board = dao.getBoard(vo);
		
		request.setAttribute("board", board);
		RequestDispatcher view = request.getRequestDispatcher("getBoard.jsp");
		
		view.forward(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		doGet(request, response);
	}

}
