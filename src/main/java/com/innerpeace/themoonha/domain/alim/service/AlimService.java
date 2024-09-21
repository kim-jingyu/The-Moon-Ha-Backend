package com.innerpeace.themoonha.domain.alim.service;

import com.innerpeace.themoonha.domain.alim.dto.LoungeAlimDTO;
import com.innerpeace.themoonha.global.dto.CommonResponse;

import java.util.List;

/**
 * 알림 서비스
 * @author 조희정
 * @since 2024.09.11
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.11  	조희정       최초 생성
 * 2024.09.11  	조희정       FCM 토큰 저장, 수업 시작 5분전 알림 기능 구현
 * 2024.09.12  	조희정       라운지 오픈 기능, 라운지 오픈 후 알림 기능 구현
 * </pre>
 */
public interface AlimService {

    /**
     * FCM 토큰 저장
     * @param memberId
     * @param token
     * @return
     */
    CommonResponse addFcmToken(Long memberId, String token);

    /**
     * MemberId 하나로 알림 보내기
     * @param memberId
     * @param title
     * @param message
     */
    void sendAlimByMemberId(Long memberId, String title, String message);

    /**
     * MemberId 리스트에 알림 보내기
     * @param memberIds
     * @param title
     * @param message
     */
    void sendAlimByMemberId(List<Long> memberIds, String title, String message);

    /**
     * 클릭시 이동할 정보와 함께 알림 보내기
     * @param memberIds
     * @param title
     * @param message
     * @param type
     * @param id
     */
    void sendAlimByMemberId(List<Long> memberIds, String title, String message, String type, Long id);

    /**
     * 5분 후 시작하는 강좌 알림 보내기
     * @return
     */
    List<LoungeAlimDTO> getFcmTokenForUpcomingLessons();

    /**
     * 라운지 오픈 후 알림 보내기
     * @return
     */
    List<LoungeAlimDTO> addLoungeAndSendAlim();

    void sendAlimToMultipleMembers(List<String> fcmTokens, String title, String message);

    void sendAlimToMultipleMembers(List<String> fcmTokens, String title, String message, String type);

}
