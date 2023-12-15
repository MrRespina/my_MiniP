<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
function deleteInfo() {
	if (confirm('정말 삭제하시겠습니까?\n삭제한 데이터는 되돌릴 수 없습니다.')) {
		let b_no = document.infoForm.b_no;
		location.href = "board.deleteBoard?b_no=" + b_no.value;
	}
}
function updateInfo(){
	
	let i = document.infoForm.b_text;
	
	if (isEmpty(i)) {
		i.value = "";
		i.focus();
		alert('글의 내용을 입력해주세요!');
	}else{	
		if (confirm('수정하시겠어요?')) {
			let b_no = document.infoForm.b_no.value;
			let b_owner = document.infoForm.b_owner.value;
			let b_text = document.infoForm.b_text.value;
			location.href = "board.updateBoard?b_no="+b_no+"&b_text="+b_text
		}
	}
}
function goBackBoard(){
	location.href = 'board.go?';
}
</script>
</head>
<body>
	<form name="infoForm">
	<table class="infoBoard" align="center">
		<tr>
			<td align="center" colspan="3">게시글 수정하기</td>
		</tr>
		<tr>
			<td>
			<input name="b_no" value="${b_no }" type="hidden"> 
			<input name="b_owner" value="${b_owner }" type="hidden"></td>
		</tr>
		<tr>
			<td colspan="3"><textarea class=infoText name="b_text" >${b_text}</textarea></td>
		</tr>
		<tr>
			<td colspan="1">
				<input type="button" class="boardInfoInp" value="수정" onclick="updateInfo();">
			</td>
			<td colspan="1">
				<input type="button" class="boardInfoInp" value="삭제" onclick="deleteInfo();">
			</td>
			<td colspan="1">
				<input type="button" class="boardInfoInp" value="돌아가기" onclick="goBackBoard();">
			</td>
		</tr>
	
	</table>
	</form>
</body>
</html>