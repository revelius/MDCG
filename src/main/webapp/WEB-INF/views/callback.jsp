<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript"
  src="https://static.nid.naver.com/js/naverLogin_implicit-1.0.2.js"
  charset="utf-8"></script>
<script type="text/javascript"
  src="http://code.jquery.com/jquery-1.11.3.min.js"></script>
</head>
<body>
	<script type="text/javascript">
	  $(document).ready(function() {
		 var  result = ""+${result}+"";
	    var name = result.response.name;
	    var email = result.response.email;
	    $("#name").html("환영합니다. "+name+"님");
	    $("#email").html(email);
	   });
	</script>
	 <h2 style="text-align: center" id="name"></h2>
  	<h4 style="text-align: center" id="email"></h4>
</body>
</html>