<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
	<script>
		if( ${vm.limit} == 2){
			alert("2분간 글쓰기를 사용 할수 없습니다.");
		} 
	</script>
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
			<c:when test="${fn:length(vm.list)> 0 }">
				<c:forEach items="${vm.list}" var="dto">
					<tr>
						<td>${dto.id}</td>
						<td>${dto.name}</td>
						<td>
							<c:forEach begin="1" end="${dto.indent}">-</c:forEach>
							<a href="../content_view?bId=${dto.id}">${dto.title}</a></td>
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
			<td colspan="5"> <a href="../write_view">글작성</a> </td>
		</tr>
	</table>
	<div>
		<c:choose>
		<c:when test="${fn:length(vm.list)> 0 }">
	         <c:if test="${vm.p.curRange ne 1 }">
	             <a href="#" onClick="fn_paging(1)">[처음]</a> 
	         </c:if>
	         <c:if test="${vm.p.curPage ne 1}">
	             <a href="#" onClick="fn_paging('${vm.p.prevPage }')">[이전]</a> 
	         </c:if>
	         <c:forEach var="pageNum" begin="${vm.p.startPage }" end="${vm.p.endPage }">
	             <c:choose>
	                 <c:when test="${pageNum eq  vm.p.curPage}">
	                     <span style="font-weight: bold;"><a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a></span> 
	                 </c:when>
	                 <c:otherwise>
	                     <a href="#" onClick="fn_paging('${pageNum }')">${pageNum }</a> 
	                 </c:otherwise>
	             </c:choose>
	         </c:forEach>
	         <c:if test="${vm.p.curPage ne vm.p.pageCnt &&vm.p.pageCnt > 0}">
	             <a href="#" onClick="fn_paging('${vm.p.nextPage }')">[다음]</a> 
	         </c:if>
	         <c:if test="${vm.p.curRange ne vm.p.rangeCnt && vm.p.rangeCnt > 0}">
	             <a href="#" onClick="fn_paging('${vm.p.pageCnt }')">[끝]</a> 
	         </c:if>
		</c:when>
		<c:otherwise>
	  			
	  	</c:otherwise>
		</c:choose>
     </div>
	<form action="../list/1" method="get">
		<select name="searchOption">
			<option value="title">제목</option>
			<option value="content">내용</option>
			<option value="titelcontent">제목  + 내용</option>
		</select>
		<input type="text" name="keyword" value="${vm.keyword != null ? vm.keyword : "" }">
		<input type="hidden" name="sc" value="search">
		<button type="submit">검색</button>
	</form>
	
</body>
</html>