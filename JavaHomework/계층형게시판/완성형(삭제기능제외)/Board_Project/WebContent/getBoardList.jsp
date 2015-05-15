<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>보드메인</title>
<script type="text/javascript">
	function pagelist(cpage){
				//input 양식의 hidden으로 선언된 page에 요청된 페이지 정보 셋팅 
				document.getElementById("pageNo").value=cpage;
				var frm = document.getElementById("frm");
				//frm.action="getBoardPage?action=LIST";
				frm.action="getBoardList?action=LIST";
				frm.submit();
	}
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
	#pagebar {
		text-align: center;
	}
	#button{
		text-align: right;
	}
	table {
		text-align: center;
		margin : 0 auto;
	}
	.tableHead {
		background-color: yellow;
	}
	.nickname {
		width: 200px;
	}
	.title {
		text-align: left;
		width: 500px;
	}
	.regdate {
		width: 200px;
	}
	.cnt {
		width: 150px;
	}
	a:link, a:visited {
		text-decoration: none;
	}
	
</style>
</head>
<body>
<div id="title">게시판</div>
<br>
<table>
	<tr border-top="black">
		<th class="tableHead">올린이</th>
		<th class="tableHead">제목</th>
		<th class="tableHead">날짜</th>
		<th class="tableHead">조회수</th>
	</tr>
	<c:forEach var="board" items="${boardList }">
	<tr>
		<td class="nickname">${board.nickname }</td>
		<c:choose>
			<c:when test="${board.depth ==0 }">
				<th class="title" width="200"><a href="getBoard?seq=${board.seq }">${board.title }</a></th>
			</c:when>
			<c:when test="${board.depth ==1 }">
				<th class="title" width="200"><a href="getBoard?seq=${board.seq }">&nbsp; └RE: ${board.title }</a></th>
			</c:when>
			<c:otherwise>
				<th class="title" width="200"><a href="getBoard?seq=${board.seq }">&nbsp; &nbsp; └RE: ${board.title }</a></th>
			</c:otherwise>
		</c:choose>
		<td class="regdate">${board.regdate }</td>
		<td class="cnt">${board.cnt }</td>
	</tr>
	</c:forEach>
	<tr>
	<td id="button" colspan="4">
		<input type="button" value="새글달기" onclick="goUrl('addBoard.jsp?currentSeq=${board.seq }')">
	</td>
	</tr>
</table>
<br><br><br>
<div id="pagebar">
<FONT size=2>${pageLink}</FONT><br/><br/>
<form  action="getBoardList" id="frm">
	<input type="hidden" id="pageNo" name="pageNo" />
</form>
</div>
</body>
</html>