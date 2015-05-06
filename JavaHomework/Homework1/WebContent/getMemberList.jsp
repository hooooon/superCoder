<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.PreparedStatement"%>
<%@page import="com.hoon.homework.JDBCUtil"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection conn = JDBCUtil.getConnection();
	String sql = "select * from husers order by seq desc";
	
	PreparedStatement pstmt = conn.prepareStatement(sql);
	ResultSet rs = pstmt.executeQuery();
	
	String gender;

%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원리스트</title>
</head>
<body>
<h1>회원리스트</h1>
<table border="1">
	<tr>
		<th>번호</th>
		<th>이름</th>
		<th>암호</th>
		<th>이메일</th>
		<th>성별</th>
		<th>나이</th>
	</tr>
<% while(rs.next()){ 
	if(rs.getInt("gender") == 0){
		gender = "남";
	}else{
		gender = "여";
	}
%>
	<tr>
		<td><%=rs.getInt("seq") %></td>
		<td><%=rs.getString("name") %></td>
		<td><%=rs.getString("password") %></td>
		<td><%=rs.getString("email") %></td>
		<td><%=gender %></td>
		<td><%=rs.getInt("age") %></td>
	</tr>
<% 
}
%>
</table><br>
<a href="index.html">홈</a>
<a href="addMember.jsp">회원가입</a>
</body>
</html>
<%
JDBCUtil.close(conn, pstmt, rs);
%>