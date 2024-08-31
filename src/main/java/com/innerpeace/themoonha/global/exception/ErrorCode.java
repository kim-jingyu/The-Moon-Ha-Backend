package com.innerpeace.themoonha.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    MEMBER_NOT_FOUND(404, "존재하지 않은 유저입니다."),
    LESSON_NOT_FOUND(404, "강좌가 존재하지 않습니다."),
    SHORTFORM_NOT_FOUND(404, "숏폼이 존재하지 않습니다."),
    MEMBER_DUPLICATE(409, "중복되는 유저가 존재합니다. 다시 시도해주세요."),
    TUTOR_NOT_FOUND(404, "강사가 존재하지 않습니다"),
    CART_LESSON_ALREADY_EXISTS(409, "강좌가 이미 장바구니에 담겨있습니다."),
    SUGANG_FAILED(400, "수강신청에 실패했습니다."),
    LOUNGE_NOT_FOUND(404, "라운지 정보가 존재하지 않습니다."),
    LOUNGE_POST_FAILED(400, "라운지 게시글 작성에 실패했습니다."),
    LOUNGE_COMMENT_FAILED(400, "라운지 댓글 작성에 실패했습니다."),
    SUGGESTION_FAILED(400, "제안합니다 댓글 작성에 실패했습니다"),
    LOUNGE_POST_NOT_FOUND(404, "라운지 게시글 정보가 존재하지 않습니다."),
    PROLOGUE_LIKE_ALREADY_EXISTS(400, "해당 프롤로그에 대해 이미 좋아요를 눌렀습니다."),
    LOUNGE_POST_UPDATE_FAILED(400, "라운지 게시물 수정에 실패했습니다."),
    LOUNGE_POST_DELETE_FAILED(400, "라운지 게시물 삭제에 실패했습니다."),
    WISHLESSON_VOTE_ALREADY_EXISTS(400, "듣고싶은 강좌에 이미 투표했습니다."),
    MEMBER_INCORRECT_AUTH(400, "아이디와 비밀번호가 일치하지 않습니다."),
    INTERNAL_SERVER_ERROR(500, "예상치 못한 오류가 발생하였습니다."),
    LOUNGE_IMG_UPLOAD_FAILED(400, "라운지 게시물 이미지 업로드에 실패했습니다."),
    ADMIN_LESSON_REGISTER_DUPLICATE(409, "수강 스케줄 혹은 강좌명이 중복되었습니다."),
    LOUNGE_COMMENT_DELETE_FAILED(400, "라운지 댓글 삭제에 실패했습니다."),
    LOUNGE_COMMENT_UPDATE_FAILED(400, "라운지 댓글 수정에 실패했습니다"),
    ATTENDANCE_START_FAILED(400, "출석 시작에 실패했습니다."),
    ATTENDANCE_UPDATE_FAILED(400, "출석 정보 수정에 실패했습니다."),
    UNSUPPORTED_CONTENT_TYPE(400, "지원되지 않는 콘텐츠 타입 정보입니다."),
    S3_UPLOAD_FAILED(400, "콘텐츠 업로드에 실패하였습니다.");

    private final int status;
    private final String message;

}
