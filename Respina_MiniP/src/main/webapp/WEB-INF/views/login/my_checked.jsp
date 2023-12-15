<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<c:if test="${myClass eq 'admin'}">
			등급 : 관리자&nbsp;
			<img src="resources/img/king.png"  style="height: 30px;width:30px;">
			<br>
		</c:if>
		<c:if test="${myClass eq 'basic'}">
			등급 : 일반<br>
		</c:if>
		내 게시글 수 : ${ brd }<br>
		내 댓글 수 : ${ com }
</body>
</html>