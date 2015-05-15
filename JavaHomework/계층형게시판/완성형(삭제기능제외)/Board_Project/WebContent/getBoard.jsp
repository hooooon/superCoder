<%@page import="com.samsung.board.vo.BoardVO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%	BoardVO board = (BoardVO) request.getAttribute("board"); %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보드디테일</title>
<script type="text/javascript">
	function goUrl(url){
		location.href = url;
	}
</script>
<style type="text/css">
	#title {
		text-align: center;
		font-weight: bold;
		font-size: x-large;
	}
	table {
		margin : 0 auto;
	}
	.tableHead {
		background-color: yellow;
		width: 200px;
	}
	.tableBody {
		width: 500px;
	}
	#buttons {
		width: 100%;
	}
	.centerForTd {
		margin: 0 auto;
		text-align: center;
	}
	
</style>
</head>
<body>
	<div id="title">상세보기 페이지</div>
	<br>
	<form action="updateBoard" method="post">
		<table>
			<tr>
				<td class="tableHead">제목</td>
				<td class="tableBody">
				<input type="hidden" name="seq" value=${board.seq }>
				<input type="text" name="title" value=${board.title }>
				</td>
			</tr>
			<tr>
				<td class="tableHead">글쓴이</td>
				<td>${board.nickname }</td>
			</tr>
			<tr>
				<td class="tableHead">올린날짜</td>
				<td>${board.regdate }</td>
			</tr>
			<tr>
				<td class="tableHead">조회수</td>
				<td>${board.cnt }</td>
			</tr>
			<tr>
				<td class="tableHead">내용</td>
				<td><textarea name=content rows=10 cols= 50>${board.content }</textarea></td>
			</tr>
			<tr>
				<td colspan="2" class="centerForTd">
					<input type="button" value="답글달기"
					 onclick="goUrl('addBoard.jsp?currentSeq=${board.seq }')">&nbsp;
					<input type="submit" value="수정" class="center">&nbsp;
					<input type="button" value="삭제" class="center"
					 onclick="goUrl('deleteReplyBoard?seq=${board.seq }&group_seq=${board.group_seq }')">
				</td>
			</tr>
		</table>
	</form>

</body>
</html>