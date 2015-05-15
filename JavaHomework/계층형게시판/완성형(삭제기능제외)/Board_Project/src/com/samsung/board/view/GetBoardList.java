package com.samsung.board.view;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.samsung.board.dao.BoardDAO;
import com.samsung.board.util.AdvancedPageUtility;
import com.samsung.board.vo.BoardVO;

public class GetBoardList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("겟보드페이지 진입");
		ArrayList<BoardVO> list = null;
		String cpage = request.getParameter("pageNo");
		BoardDAO mdao = new BoardDAO();
		int page = 1;

		if (cpage != null) {
			page = Integer.parseInt(cpage);
		}

		try {
			int interval = 10; // 한 페이지에 보여줄 게시글 개수
			list = mdao.list(page, interval);
			int total = mdao.listCount();
			total = total == 0 ? 1 : total;

			AdvancedPageUtility bar = new AdvancedPageUtility(interval, total,
					page, "image/");
			request.setAttribute("pageLink", bar.getPageBar());
			request.setAttribute("boardList", list);
			//request.setAttribute("list", list);
			//System.out.println(list.toString());
			//request.setAttribute("content", "/board/BoardList.jsp");
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher view = request.getRequestDispatcher("getBoardList.jsp");
		view.forward(request, response);


	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
