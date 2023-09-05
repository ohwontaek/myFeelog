/*!
* Start Bootstrap - Modern Business v5.0.7 (https://startbootstrap.com/template-overviews/modern-business)
* Copyright 2013-2023 Start Bootstrap
* Licensed under MIT (https://github.com/StartBootstrap/startbootstrap-modern-business/blob/master/LICENSE)
*/
// This file is intentionally blank
// Use this file to add JavaScript to your project
type = "String/javascript"
function confirmQuit(memberId) {
    if (confirm("정말로 회원을 탈퇴하시겠습니까?")) {
        // 사용자가 확인을 눌렀을 때의 처리
        window.location.href = "/deleteMember/" + memberId; // 회원 탈퇴 처리 페이지로 이동
    } else {
        // 사용자가 취소를 눌렀을 때의 처리
        // 아무 작업을 하지 않거나 필요에 따라 다른 작업 수행 가능
    }
}