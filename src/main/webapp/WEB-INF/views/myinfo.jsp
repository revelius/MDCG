<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>내 정보 보기</title>
</head> 
<body>
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>id</td>
			<td>${info.id}</td>
		</tr>
		<tr>
			<td>닉네임</td>
			<td>${info.nickname}</td>
		</tr>
		<tr>
			<td>이메일</td>
			<td>${info.email}</td>
		</tr>
		<tr>
			<td>회원가입일</td>
			<td>${info.create_date}</td>
		</tr>
		<tr>
			<td>계급</td>
			<td>${info.grade}</td>
		</tr>
		<tr>
			<td>경험치</td>
			<td>${info.exp}</td>
		</tr>

		<tr>
		<td colspan="2"> <a href="../write_view">회원정보 수정</a> </td>
		</tr>
	</table>
</body>
</html>