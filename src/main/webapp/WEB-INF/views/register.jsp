<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>회원가입</title>
</head>
<body>
	<h1>회원가입 페이지</h1>
	
		<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="register" method="post">
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="name" size = "50"> </td>
			</tr>
			<tr>
				<td> 비밀번호</td>
				<td> <input type="text" name="title" size = "50"> </td>
			</tr>
			<tr>
				<td> 비밀번호 확인</td>
				<td> <input type="text" name="title" size = "50"> </td>
			</tr>
			<tr>
				<td> 닉네임 </td>
				<td> <textarea name="content" rows="10" ></textarea> </td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="입력"> &nbsp;&nbsp; <a href="list.do">목록보기</a></td>
			</tr>
		</form>
	</table>
</body>
</html>