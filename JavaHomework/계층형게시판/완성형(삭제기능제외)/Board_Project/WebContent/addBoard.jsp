<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<title>새글등록</title>
</head>
<body>
<div>
	<h3 style="text-align : center">글 등록하기</h3>
	<hr>
		<form action="addReplyBoard" method="post">
		<table border="1" cellpadding="0" cellspacing="0"style="margin : auto">
			<tr>
				<td>제목</td><td align="left"><input type="text" name="title"/></td>
			</tr>
			<tr>
				<td>작성자</td><td align="left"><input type="text" name="nickname" size="10"/></td>
			</tr>
			<tr>
				<td>내용</td><td align="left"><textarea name="content" cols="40" rows="10"></textarea></td>
			</tr>
			<tr>
				<td colspan="2" align="center">
				<input type="hidden" name="currentSeq" value=${param.currentSeq }>
				<input type="hidden" name="userid" value="test">
				<input type="submit" value=" 새글 등록 "/>
				</td>
			</tr>
		</table>
		</form>
	<hr>
</div>

</body>
</html>