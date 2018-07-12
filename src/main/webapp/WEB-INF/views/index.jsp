<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
  <meta charset="UTF-8">
  <meta name="Author" content="">
  <meta name="Keywords" content="">
  <meta name="Description" content="">
  <title>Document</title>
	<link rel="stylesheet" href="common/ncu.css" />
	<style>


		/*header*/
		#header{width:1280px;border:1px solid red;margin:0 auto;height:204px;}
		#banner1{width:1280px;border:1px solid red;margin:0 auto; height:100px;}
		
		.logo{float:left;}
		.logo h1 a{display:block; height:100px; width:190px; border:1px solid red;font-size:0;}
		
		.list_ul{border:1px solid blue; height:100px; overflow:hidden}
		.list_ul>ul>li a{float:left;border: 1px solid red; padding:40px;margin-left:10px;}
		.list_ul>ul>li:first-child a{margin-left:120px;}
		

		/*content */
		#content{width:1280px;height:2000px;border:1px solid red;margin:0 auto;margin-top:15px;}
		
		.board1{border:1px solid red; height:250px; width:880px; float:left;}
		.login{border: 1px solid red; width:375px; height:100px;float:right;}
		.lanking {border:1px solid red; width:375px; height:135px;float:right; margin-top:15px;}
		
	</style>
</head>
<body>

	 <!--  ${pageContext.request.contextPath} -->
	 
	 <div id="wrap">
		<div id="header">
				<div id="banner1">
				</div>
				<div class="logo">
					<h1><a href="#">배너</a></h1>
				</div>
				<div class="list_ul">
					<ul>
						<li><a href="#">공지사항</a></li>
						<li><a href="./list/1">게시판</a></li>
						<li><a href="#">자유게시판</a></li>
						<li><a href="#">콜로세움</a></li>
						<li><a href="#">게시판</a></li>
						<li><a href="#">게시판</a></li>
					</ul>
				</div>
		</div>
		<div	id="content">
			<div class="board1">
				<ul>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
					<li></li>
				</ul>
			</div>
			<div class="login">
				<a href="./login"><img src="common/img/btn_login_naver.png" width="310" height="64"></a>
				
			</div>
			<div class="lanking">
				랭킹
				<a href="./logout">로그아웃</a>
				<a href="./register">회원가입</a>
				<a href="./mailList/1">쪽지 보내기</a>
				<a href="./myinfo">내 정보 보기</a>
			</div>
		</div>
		<div id="footer">
			
		</div>
	</div>
	 
</body>
</html>
