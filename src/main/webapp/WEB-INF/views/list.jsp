<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
	
	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>번호</td>
			<td>이름</td>
			<td>제목</td>
			<td>날짜</td>
			<td>히트</td>
		</tr>
		<c:choose>
			<c:when test="${fn:length(list)> 0 }">
				<c:forEach items="${list}" var="dto">
					<tr>
						<td>${dto.id}</td>
						<td>${dto.name}</td>
						<td>
							<c:forEach begin="1" end="${dto.indent}">-</c:forEach>
							<a href="content_view?bId=${dto.id}">${dto.title}</a></td>
						<td>${dto.date}</td>
						<td>${dto.hit}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
	  			<tr><td colspan="5">게시글이 없습니다.</td></tr>
	  		</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="5"> <a href="write_view">글작성</a> </td>
		</tr>
	</table>
	
</body>
</html>