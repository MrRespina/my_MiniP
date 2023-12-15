$(function() {

	boards();

});

function boards() {

	$('.infoClass').click(
			function() {
				// 부모 테이블 (boardContent) 에서 input의 id로 찾아라.
				let b_no = $(this).closest("table.boardContent").find(
						"input[id='b_no']").val();
				let b_owner = $(this).closest("table.boardContent").find(
						"input[id='b_owner']").val();
				let b_when = $(this).closest("table.boardContent").find(
						"input[id='b_when']").val();
				let b_text = $(this).closest("table.boardContent").find(
						"textarea[id='b_text']").val();
				location.href = "board.boardInfo?b_no=" + b_no + "&b_owner="
						+ b_owner + "&b_when=" + b_when + "&b_text=" + b_text;
			});

	$('.updateComment').click(

			function() {

				let c_no = $(this).parent().find("input[id='c_no']").val();
				let c_text = $(this).parent().find("input[id='newComment']")
						.val();

				if (c_text=="" || c_text==null) {
					c_text = "";
					alert('수정할 내용을 입력해주세요!\n');
				} else {
					location.href = "cmt.commentUpdateToBoard?c_text=" + c_text
							+ "&c_no=" + c_no;
				}

			});
	
	$('.updateCommentToMy').click(
			
			function() {
				
				let c_no = $(this).parent().find("input[id='c_no']").val();
				let c_text = $(this).parent().find("input[id='newComment']")
				.val();
				
				if (c_text=="" || c_text==null) {
					c_text = "";
					alert('수정할 내용을 입력해주세요!\n');
				} else {
					location.href = "cmt.commentUpdateToMy?c_text=" + c_text
					+ "&c_no=" + c_no;
				}
				
			});

	$('.deleteComment').click(

			function() {

				let c_no = $(this).parent().find("input[id='c_no']").val();

				if (confirm('정말 이 댓글을 삭제하시겠어요?\n삭제된 정보는 복구가 불가능합니다.')) {
					location.href = "cmt.commentDeleteToBoard?&c_no=" + c_no;
				} else {

				}

	});
	
	$('.deleteCommentToMy').click(
			
			function() {
				
				let c_no = $(this).parent().find("input[id='c_no']").val();
				
				if (confirm('정말 이 댓글을 삭제하시겠어요?\n삭제된 정보는 복구가 불가능합니다.')) {
					location.href = "cmt.commentDeleteToMy?&c_no=" + c_no;
				} else {
					
				}
				
			});

	$('.inputComment').click(

			function() {

				let cmtText = $(this).closest("table.boardContent").find(
						"input[id='cmtText']").val();
				let b_no = $(this).closest("table.boardContent").find(
						"input[id='b_no']").val();
				let token = $(this).closest("body").find("input[id='token']").val();
				
				if(cmtText=="" || cmtText==null){
					cmtText = "";
					alert('내용을 입력해주세요!\n');			
				}else{
					location.href = "cmt.insertComment?cmtText=" + cmtText+ "&b_no=" + b_no+"&token="+token;			
				}

			});

}
