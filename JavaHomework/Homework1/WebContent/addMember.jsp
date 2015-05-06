<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>회원가입페이지입니다.</title>
</head>
<body>
<form action="insertMember.jsp" method="post">
<table border="1">
	<tr>
		<td colspan="2">회원가입</td>
	</tr>
	<tr>
		<td>이름</td>
		<td><input type="text" name="name"></td>
	</tr>
	<tr>
		<td>암호</td>
		<td><input type="password" name="password"></td>
	</tr>
	<tr>
		<td>email</td>
		<td><input type="email" name="email"></td>
	</tr>
	<tr>
		<td>성별</td>
		<td>
			<input type="radio" name="gender" value="0">남
			<input type="radio" name="gender" value="1">여
		</td>
	</tr>
	<tr>
		<td>나이</td>
		<td><input type="number" name="age" min="0" max="200" step="1"></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="회원가입">
			<input type="reset" value="리셋">
			<a href="index.html"><input type="button" value="홈"></a>
		</td>
	</tr>
</table>
</form>
</body>
</html>