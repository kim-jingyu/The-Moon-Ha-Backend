package com.innerpeace.themoonha.domain.alim.service;

import com.innerpeace.themoonha.domain.alim.dto.LoungeAlimDTO;
import com.innerpeace.themoonha.domain.alim.mapper.AlimMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.util.FcmUtil;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;

/**
 * 알림 서비스 구현체
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
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AlimServiceImpl implements AlimService{

    private final AlimMapper alimMapper;
    private final FcmUtil fcmUtil;

    /**
     * FCM 토큰 저장
     * @param memberId
     * @param token
     * @return
     */
    @Override
    public CommonResponse addFcmToken(Long memberId, String token) {
        if (token != null && token.startsWith("\"") && token.endsWith("\"")) {
            token = token.substring(1, token.length() - 1);
        }

        if(alimMapper.insertFcmToken(memberId, token) != 1) {
            throw new CustomException(ErrorCode.INTERNAL_SERVER_ERROR);
        }
        return CommonResponse.of(true, SuccessCode.FCM_TOKEN_ADD_SUCCESS.getMessage());
    }

    /**
     * MemberId에게 알림 보내기
     * @param memberId
     * @param title
     * @param message
     */
    @Override
    public void sendAlimByMemberId(Long memberId, String title, String message) {
        List<Long> memberIds = Collections.singletonList(memberId);
        List<String> fcmTokens = alimMapper.selectFcmTokenByMemberId(memberIds);

        sendAlimToMultipleMembers(fcmTokens, title, message);
    }

    /**
     * MemberId 리스트에게 알림 보내기
     * @param memberIds
     * @param title
     * @param message
     */
    @Override
    public void sendAlimByMemberId(List<Long> memberIds, String title, String message) {
        if (!memberIds.isEmpty()) {
            List<String> fcmTokens = alimMapper.selectFcmTokenByMemberId(memberIds);
            sendAlimToMultipleMembers(fcmTokens, title, message);
        }
    }

    /**
     * 5분 후 시작하는 수업 수강생에게 알림 보내기
     * @return
     */
    @Override
    public List<LoungeAlimDTO> getFcmTokenForUpcomingLessons() {
        return alimMapper.selectFcmTokenForUpcomingLessons();
    }

    /**
     * 라운지 오픈 후 수강생에게 알립 보내기
     * @return
     */
    @Override
    public List<LoungeAlimDTO> addLoungeAndSendAlim() {
        alimMapper.insertLounge();
        return alimMapper.selectFcmTokensAndLessonTitle();
    }

    /**
     * 알림 보내기
     * @param fcmTokens
     * @param title
     * @param message
     */
    @Override
    public void sendAlimToMultipleMembers(List<String> fcmTokens, String title, String message) {
        for (String token : fcmTokens) {
            try {
                fcmUtil.send_FCM(token, title, message);
            } catch (Exception e) {
                log.error("FCM 알림 전송 실패: " + e.getMessage(), e);
                throw new CustomException(ErrorCode.ALIM_SEND_FAIL);
            }
        }

        CommonResponse.of(true, SuccessCode.ALIM_SEND_SUCCESS.getMessage());
    }



}
