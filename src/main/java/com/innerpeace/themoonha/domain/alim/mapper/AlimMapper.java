package com.innerpeace.themoonha.domain.alim.mapper;

import com.innerpeace.themoonha.domain.alim.dto.LoungeAlimDTO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 알림 매퍼
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
public interface AlimMapper {

    /**
     * FCM 토큰 저장
     * @param memberId
     * @param token
     * @return
     */
    int insertFcmToken(@Param("memberId") Long memberId, @Param("token") String token);

    /**
     * FCM 토큰이 이미 존재하는지 여부
     * @param token
     * @return
     */
    boolean selectFcmTokenExist(@Param("memberId") Long memberId, @Param("token") String token);

    /**
     * MemberId의 FCM 토큰 가져오기
     * @param memberIds
     * @return
     */
    List<String> selectFcmTokenByMemberId(List<Long> memberIds);

    /**
     * 5분 후에 수업인 MemberId의 FCM 토큰 가져오기
     * @return
     */
    List<LoungeAlimDTO> selectFcmTokenForUpcomingLessons();

    /**
     * 라운지 오픈
     */
    void insertLounge();

    /**
     * 오픈된 라운지에 참여하는 MemberId의 FCM 토큰 가져오기
     * @return
     */
    List<LoungeAlimDTO> selectFcmTokensAndLessonTitle();
}
