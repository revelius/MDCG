<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>������</title>
</head>
<body>
	<h1>������</h1>
	
	
		<table width="500" cellpadding="0" cellspacing="0" border="1">
		<tr>
			<td>�޴���</td>
			<td>������</td>
			<td>����</td>
			<td>������Ȯ��</td>
			<td>������Ȯ��</td>
			<td>���� ��¥</td>
			<td>Ȯ���� ��¥</td>
			<td>�����̻���</td>
			<td>�����̻���</td>			
		</tr>
		<c:choose>
			<c:when test="${fn:length(ml.list)> 0 }">
				<c:forEach items="${ml.list}" var="dto">
					<tr>
						<td>${dto.post}</td>
						<td>${dto.send}</td>
						<td><a>${dto.contents}</a></td>
						<td>${dto.post_get}</td>
						<td>${dto.send_get}</td>
						<td>${dto.post_date}</td>
						<td>${dto.send_date}</td>
						<td>${dto.post_del}</td>
						<td>${dto.send_del}</td>
					</tr>
				</c:forEach>
			</c:when>
			<c:otherwise>
	  			<tr><td colspan="9">�Խñ��� �����ϴ�.</td></tr>
	  		</c:otherwise>
		</c:choose>
		<tr>
			<td colspan="9"> <a href="../mailWrite_view">����������</a> </td>
		</tr>
	</table>
		<div>
		<c:choose>
		<c:when test="${fn:length(ml.list)> 0 }">
	         <c:if test="${ml.p.curRange ne 1 }">
	             <a href="#" onClick="fn_paging(1)">[ó��]</a> 
	         </c:if>
	         <c:if test="${ml.p.curPage ne 1}">
	             <a href="#" onClick="fn_paging('${ml.p.prevPage }')">[����]</a> 
	         </c:if>
	         <c:forEach var="pageNum" begin="${ml.p.startPage }" end="${ml.p.endPage }">
	             <c:choose>
	                 <c:when test="${pageNum eq  ml.p.curPage}">
	                     <span style="font-weight: bold;"><a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a></span> 
	                 </c:when>
	                 <c:otherwise>
	                     <a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a> 
	                 </c:otherwise>
	             </c:choose>
	         </c:forEach>
	         <c:if test="${ml.p.curPage ne ml.p.pageCnt &&ml.p.pageCnt > 0}">
	             <a href="#" onClick="fn_paging('${ml.p.nextPage }')">[����]</a> 
	         </c:if>
	         <c:if test="${ml.p.curRange ne ml.p.rangeCnt && ml.p.rangeCnt > 0}">
	             <a href="#" onClick="fn_paging('${ml.p.pageCnt }')">[��]</a> 
	         </c:if>
		</c:when>
		</c:choose>
     </div>
</body>
</html>