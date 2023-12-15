<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<table class="commentContent" align="center" style="background-color: black;border-radius: 3px;">
		<tr>
				<td class="bOwner_Title" colspan="3">글 작성자</td>
				<td class="bText_Title" colspan="3">글 내용</td>
		</tr>
		<c:forEach var="board" items="${boards }">
			<c:if test="${board.b_no != chk }">
				<tr>
					<td class="bOwner" colspan="3">${board.b_owner }</td>
					<td class="bText" colspan="3">${board.b_text }</td>
				</tr>
			</c:if>
				<c:forEach var="comment"  items="${comments}">	
					<c:if test="${comment.c_b_no eq board.b_no}">
						<c:if test="${board.b_no != chk }">
						<tr>											
						<td colspan="1"></td>					
						<td class="commentTitle" colspan="1">댓글</td>
						<td class="commentOwner" colspan="1">${comment.c_owner}</td>
						<td class="commentWhen" colspan="1"><fmt:formatDate
							value="${comment.c_when }" type="both" dateStyle="short"
							timeStyle="short" /></td>
						<td class="commentText" colspan="1">${comment.c_text}</td>
						<td class="commentInfo" colspan="1">
							<input id="c_no" value="${comment.c_no }" type="hidden"> 
							<input id="newComment" class="cmtUpdateInput" placeholder="수정할 내용">
								<span class=updateCommentToMy> 
									수정<img style="width: 20px; height: 20px;" src="resources/img/repair.png">
								</span> &nbsp; 
								<span class="deleteCommentToMy">
								 삭제<img style="width: 20px; height: 20px;" src="resources/img/garbage.png">
							</span>
						</td>						
					</tr>		
						</c:if>
					</c:if>			
				</c:forEach>				
			<c:set var="chk" value="${board.b_no }"/>	
		</c:forEach>
				
	</table>
</body>
</html>