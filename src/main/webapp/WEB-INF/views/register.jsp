<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>ȸ������</title>
</head>
<body>
	<h1>ȸ������ ������</h1>
	
		<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="register" method="post">
			<tr>
				<td> �̸� </td>
				<td> <input type="text" name="name" size = "50"> </td>
			</tr>
			<tr>
				<td> ��й�ȣ</td>
				<td> <input type="text" name="title" size = "50"> </td>
			</tr>
			<tr>
				<td> ��й�ȣ Ȯ��</td>
				<td> <input type="text" name="title" size = "50"> </td>
			</tr>
			<tr>
				<td> �г��� </td>
				<td> <textarea name="content" rows="10" ></textarea> </td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="�Է�"> &nbsp;&nbsp; <a href="list.do">��Ϻ���</a></td>
			</tr>
		</form>
	</table>
</body>
</html>