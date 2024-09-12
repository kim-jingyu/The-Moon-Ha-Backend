package com.innerpeace.themoonha.global.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.reflect.DeclareErrorOrWarning;

@Getter
@RequiredArgsConstructor
public enum SuccessCode implements StatusCode {
    CART_LESSON_ADDED_SUCCESS(200, "강좌가 성공적으로 장바구니에 담겼습니다."),
    SUGANG_APPLICATION_SUCCESS(200, "수강신청이 완료되었습니다."),
    SUGGESTION_WRITE_SUCCESS(200, "제안합니다 댓글 작성에 성공했습니다."),
    PROLOGUE_LIKE_SUCCESS(200, "프롤로그에 좋아요를 눌렀습니다."),
    LOUNGE_POST_ADD_SUCCESS(200, "라운지에 게시물이 등록되었습니다."),
    LOUNGE_COMMENT_ADD_SUCCESS(200, "라운지에 댓글이 등록되었습니다."),
    LOUNGE_POST_UPDATE_SUCCESS(200, "라운지 게시물이 수정되었습니다."),
    LOUNGE_POST_DELETE_SUCCESS(200, "라운지 게시물이 삭제되었습니다."),
    WISHLESSON_VOTE_SUCCESS(200, "듣고싶은 강좌에 투표를 완료했습니다."),
    AUTH_SIGNUP_SUCCESS(200,"회원가입에 성공하였습니다."),
    AUTH_LOGIN_SUCCESS(200, "로그인에 성공하였습니다."),
    ADMIN_LESSON_REGISTER_SUCCESS(200,"강좌 등록이 완료되었습니다."),
    ADMIN_SHORTFROM_REGISTER_SUCCESS(200, "숏폼 등록이 완료되었습니다."),
    LOUNGE_COMMENT_UPDATE_SUCCESS(200, "라운지 댓글이 수정되었습니다."),
    LOUNGE_COMMENT_DELETE_SUCCESS(200, "라운지 댓글이 삭제되었습니다."),
    ATTENDANCE_START_SUCCESS(200, "출석 시작에 성공하였습니다."),
    ATTENDANCE_UPDATE_SUCCESS(200, "출석 정보 수정에 성공하였습니다."),
    ADMIN_PROLOGUE_REGISTER_SUCCESS(200, "프롤로그 등록에 성공하였습니다."),
    LIVE_LESSON_END_SUCCESS(200, "실시간 강좌 종료에 성공하였습니다."),
    FCM_TOKEN_ADD_SUCCESS(200, "FCM 토큰 저장에 성공하였습니다."),
    ALIM_SEND_SUCCESS(200, "알림 보내기에 성공하였습니다.");

    private final int status;
    private final String message;
}
