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

function fn_paging(a){
	
	location.href= "../../list/"+a;
	
}

</script>
</head>
<body>

	<table width="500" cellpadding="0" cellspacing="0" border="1">
		<form action="modify" method="post">
			<input type="hidden" name="id" value="${vm.content_view.id}">
			<tr>
				<td> 번호 </td>
				<td> ${vm.content_view.id} </td>
			</tr>
			<tr>
				<td> 히트 </td>
				<td> ${vm.content_view.hit} </td>
			</tr>
			<tr>
				<td> 이름 </td>
				<td> <input type="text" name="name" value="${vm.content_view.name}"></td>
			</tr>
			<tr>
				<td> 제목 </td>
				<td> <input type="text" name="title" value="${vm.content_view.title}"></td>
			</tr>
			<tr>
				<td> 내용 </td>
				<td> <textarea rows="10" name="content" >${vm.content_view.content}</textarea></td>
			</tr>
			<tr >
				<td colspan="2"> <input type="submit" value="수정"> &nbsp;&nbsp; <a href="../../list/1">목록보기</a> &nbsp;&nbsp; <a href="delete?id=${content_view.id}">삭제</a> &nbsp;&nbsp; <a href="reply_view?id=${content_view.id}">답변</a></td>
			</tr>
		</form>
	</table>
	
	
	<br />
	<br />
	<!-- asdasd --> 
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
							<a href="../../content_view/${vm.curP}/${dto.id}${vm.getSearch ne '' ? vm.getSearch : null }">${dto.title}</a></td>
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
			<td colspan="5"> <a href="../../write_view">글작성</a> </td>
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
	<form action="../../list/1" method="get">
		<select name="searchOption">
			<option value="title" ${vm.searchOption eq 'title' ? 'selected' : null}  >제목</option> 
			<option value="content" ${vm.searchOption eq 'content' ? 'selected' : null} >본문</option>
			<option value="titelcontent" ${vm.searchOption eq 'titelcontent' ? 'selected' : null} >제목  + 본문</option>
			<option value="nameid">글쓴이</option>
		</select>
		<input type="text" name="keyword" value="${vm.keyword != null ? vm.keyword : null }">
		<input type="hidden" name="sc" value="search">
		<button type="submit">검색</button>
	</form>
</body>
</html>