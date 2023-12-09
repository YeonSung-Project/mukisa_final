function boardremoveCheck(nm) {
	 if (confirm("정말 삭제하시겠습니까??") == true){
		location.href="boardDeletePro.me?b_no="+nm;
	 }else{
	     return false;
	 }
	}

function replyremoveCheck(nm) {
	 if (confirm("정말 삭제하시겠습니까??") == true){
		 $('#replydeleteForm').submit();
	 }else{
	     return false;
	 }
	}
function replyUpdate(nm){

	$('#replyUpdateForm'+nm).removeAttr('style');
	$('.reply_Update').attr('style','display:none');

}
function replyUpdate_Cancel(nm){

	$('#replyUpdateForm'+nm).attr('style','display:none');
	$('.reply_Update').show();

}

function check(){
	alert("로그인 후 이용 가능합니다");

}