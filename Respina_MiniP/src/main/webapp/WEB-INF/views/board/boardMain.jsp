<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<!-- boardVal =all 일때, 전체 / boardVal =my 일때 내 게시글 확인만 되게끔! -->
	<c:if test="${boardVal eq 'all' }">
		<table align="center">
			<tr>
				<td><c:if test="${pageNo != 1}">
						<a href="board.go?p=${pageNo - 1 }" class="boardL">&lt;</a>
					</c:if> <c:if test="${pageNo != pageCount}">
						<a href="board.go?p=${pageNo + 1 }" class="boardR">&gt;</a>
					</c:if></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${boardVal eq 'my' }">
		<table align="center">
			<tr>
				<td><c:if test="${pageNo2 != 1}">
						<a href="board.getmyBoard?p=${pageNo2 - 1 }" class="boardL">&lt;</a>
					</c:if> <c:if test="${pageNo2 != pageCount2}">
						<a href="board.getmyBoard?p=${pageNo2 + 1 }" class="boardR">&gt;</a>
					</c:if></td>
			</tr>
		</table>
	</c:if>
	<c:if test="${boardVal eq 'searched' }">
		<table align="center">
			<tr>
				<td><c:if test="${pageNo3 != 1}">
						<a href="board.getSearchedBoard?p=${pageNo3 - 1 }" class="boardL">&lt;</a>
					</c:if> <c:if test="${pageNo3 != pageCount3}">
						<a href="board.getSearchedBoard?p=${pageNo3 + 1 }" class="boardR">&gt;</a>
					</c:if></td>
			</tr>
		</table>
	</c:if>
	<div style="height: 660px;">
		<input id="token" value="${token }" type="hidden">
		<c:forEach var="board" items="${boards}">
			<form name="boardf" onclick="">
				<table class="boardContent" align="center" >
					<tr>
						<td align="center" class="board_owner">${board.b_owner}<br>
						</td>
						<td align="right" class="board_When">
							<input id="b_no" value="${board.b_no }" type="hidden"> 
							<input id="b_owner" value="${board.b_owner }" type="hidden"> 
							<input id="b_when" value="${board.b_when }" type="hidden"> 
							<fmt:formatDate value="${board.b_when }" type="both" dateStyle="long"
								timeStyle="short" /></td>
					</tr>
					<tr>
						<td colspan="3"><textarea id="b_text"
								class="board_Text" readonly="readonly" value="${board.b_text}">${board.b_text}</textarea></td>
					</tr>
					<tr>				
							<td style="text-align: left;margin-left: 5px;" colspan="3" class="board_comment">
								<table class="commentContent" >		
									<c:forEach var="comment" items="${comments }">
										<c:if test="${board.b_no eq comment.c_b_no }">
										<tr>										
											<td class="commentTdOwner">${comment.c_owner}</td>
											<td class="commentTdWhen">
												<fmt:formatDate value="${comment.c_when }" type="both" dateStyle="short" timeStyle="short" />
											</td>
											<td class="commentTdText">${comment.c_text}</td>								
											<c:if test="${(sessionScope.loginCheck.m_id eq comment.c_owner) or sessionScope.loginCheck.m_class == 1}">
												<td class="commentTdInfo">
													<input id="c_no" value="${comment.c_no }" type="hidden"> 
													<c:if test="${sessionScope.loginCheck.m_id eq comment.c_owner}">
														<input id="newComment"  class="cmtUpdateInput"  placeholder="수정할 내용">
														<span class="updateComment"> 
															수정<img style="width: 20px; height: 20px;"src="resources/img/repair.png">
														</span>	
													</c:if>
													&nbsp;				
													<span class="deleteComment"> 
														삭제<img style="width: 20px; height: 20px;"src="resources/img/garbage.png">
													</span>							
												</td>
											</c:if>
										</tr>
										</c:if>
									</c:forEach>		
								</table>
							</td>						
					</tr>			
					<tr>
						<td colspan="2" align="right" class="board_option">
						<c:if test="${board.b_owner != sessionScope.loginCheck.m_id and not empty sessionScope.loginCheck.m_id}">
									<input type="text"  class="cmtInput" id="cmtText"  placeholder="댓글 내용">
										<span class="inputComment">
											댓글 달기
											<img style="width: 30px; height: 30px;" src="resources/img/com_icon.png">
										</span>
							</c:if> 
							<c:if
								test="${board.b_owner == sessionScope.loginCheck.m_id or  sessionScope.loginCheck.m_class == 1}">
								<span class="infoClass"> 수정하기<img
									style="width: 30px; height: 30px;"
									src="resources/img/repair.png">
								</span>
							</c:if></td>
					</tr>
				</table>
			</form>
		</c:forEach>
	</div>

</body>
</html>