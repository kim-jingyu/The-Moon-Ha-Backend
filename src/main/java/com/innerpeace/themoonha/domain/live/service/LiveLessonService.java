package com.innerpeace.themoonha.domain.live.service;

import com.innerpeace.themoonha.domain.live.dto.LiveLessonDetailResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonRequest;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonStatusResponse;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 실시간 강좌 서비스
 *
 * @author 김진규
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  김진규        최초 생성
 * </pre>
 * @since 2024.09.01
 */
public interface LiveLessonService {
    List<LiveLessonResponse> getLiveLessonsByMember(Long memberId);
    List<LiveLessonResponse> getLiveLessonsByMemberOrderByTitle(Long memberId);
    List<LiveLessonResponse> getLiveLessonsMemberDoesNotHave(Long memberId);
    List<LiveLessonResponse> getLiveLessonsMemberDoesNotHaveOrderByTitle(Long memberId);
    LiveLessonDetailResponse getLiveLessonDetails(Long livedId, Long memberId);
    LiveLessonResponse createLiveLesson(Long memberId, LiveLessonRequest liveLessonRequest, MultipartFile thumbnail);
    CommonResponse endLiveLesson(Long liveId);
    LiveLessonStatusResponse getLiveLessonStatus(Long liveId);
    String getShareLink(Long liveId);
}
