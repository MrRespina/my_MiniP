function searchCheck() {

	let b = document.searchB.search_board;

	if (isEmpty(b)) {
		b.value = "";
		b.focus();
		alert('검색할 것을 입력해주세요!\n[ ID or 내용]');
		return false;
	}
	return true;
	
}

function insertCheck() {
	
	let i = document.insertB.write_board;
	
	if (isEmpty(i)) {
		i.value = "";
		i.focus();
		alert('글의 내용을 입력해주세요!');
		return false;
	}
	return true;
	
}
