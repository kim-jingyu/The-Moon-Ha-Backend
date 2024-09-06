package com.innerpeace.themoonha.domain.live.mapper;

import com.innerpeace.themoonha.domain.live.dto.LiveLessonDetailResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonResponse;
import com.innerpeace.themoonha.domain.live.vo.LiveLesson;
import com.innerpeace.themoonha.domain.live.vo.LiveStatus;

import java.util.List;
import java.util.Optional;

/**
 * 실시간 강좌 Mapper
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
public interface LiveLessonMapper {
    List<LiveLessonResponse> findLiveLessonsByMember(Long memberId);
    List<LiveLessonResponse> findLiveLessonsByMemberOrderByTitle(Long memberId);
    List<LiveLessonResponse> findLiveLessonsMemberDoesNotHave(Long memberId);
    List<LiveLessonResponse> findLiveLessonsMemberDoesNotHaveOrderByTitle(Long memberId);
    Optional<LiveLessonDetailResponse> findLiveLessonDetailById(Long liveId, Long memberId);
    void insertLiveLesson(LiveLesson liveLesson);
    Optional<LiveLesson> findLiveLessonById(Long liveId);
    void updateLiveLessonStatus(Long liveId, LiveStatus status);
    List<Long> findActiveLiveLessonIdList();
}
