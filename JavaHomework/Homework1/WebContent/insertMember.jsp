<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.hoon.homework.JDBCUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	request.setCharacterEncoding("utf-8");
	Connection conn = JDBCUtil.getConnection();
	String sql = "insert into husers(seq, name, password, email, gender, age)"+
			" values( (select nvl(max(seq), 0)+1 from husers), ?, ?, ?, ?, ?)"; 
	PreparedStatement pstmt = conn.prepareStatement(sql);
	pstmt.setString(1, request.getParameter("name"));
	pstmt.setString(2, request.getParameter("password"));
	pstmt.setString(3, request.getParameter("email"));
	pstmt.setInt(4, Integer.parseInt(request.getParameter("gender")));
	pstmt.setInt(5, Integer.parseInt(request.getParameter("age")));

	pstmt.executeUpdate();
	
	JDBCUtil.close(conn, pstmt);
	
	response.sendRedirect("getMemberList.jsp");
%>
