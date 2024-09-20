package com.innerpeace.themoonha.domain.live.service;

import com.innerpeace.themoonha.domain.alim.service.AlimService;
import com.innerpeace.themoonha.domain.auth.mapper.AuthMapper;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonDetailResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonRequest;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonResponse;
import com.innerpeace.themoonha.domain.live.dto.LiveLessonStatusResponse;
import com.innerpeace.themoonha.domain.live.mapper.LiveLessonMapper;
import com.innerpeace.themoonha.domain.live.vo.LiveLesson;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.entity.Member;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.service.S3Service;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

import static com.innerpeace.themoonha.domain.live.vo.LiveStatus.ENDED;
import static com.innerpeace.themoonha.domain.live.vo.LiveStatus.ON_AIR;
import static com.innerpeace.themoonha.global.exception.ErrorCode.*;
import static com.innerpeace.themoonha.global.vo.SuccessCode.LIVE_LESSON_END_SUCCESS;

/**
 * 실시간 강좌 서비스 구현체
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
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LiveLessonServiceImpl implements LiveLessonService {
    private static final String SUFFIX_MESSAGE = " 강좌가 개설되었습니다.";
    private final LiveLessonMapper liveLessonMapper;
    private final AuthMapper authMapper;
    private final S3Service s3Service;
    private final LiveLessonEventService liveLessonEventService;
    private final LiveLessonEventConsumer liveLessonEventConsumer;
    private final AlimService alimService;

    private static final String LIVE_VIEW_URL = "http://172.30.1.80:3000/live/view/";
    private static final String LIVE_CONTENT_PATH = "live/content";
    private static final String LIVE_THUMBNAIL_PATH = "live/thumbnail";

    @Override
    public List<LiveLessonResponse> getLiveLessonsByMember(Long memberId) {
        return liveLessonMapper.findLiveLessonsByMember(memberId);
    }

    @Override
    public List<LiveLessonResponse> getLiveLessonsByMemberOrderByTitle(Long memberId) {
        return liveLessonMapper.findLiveLessonsByMemberOrderByTitle(memberId);
    }

    @Override
    public List<LiveLessonResponse> getLiveLessonsMemberDoesNotHave(Long memberId) {
        return liveLessonMapper.findLiveLessonsMemberDoesNotHave(memberId);
    }

    @Override
    public List<LiveLessonResponse> getLiveLessonsMemberDoesNotHaveOrderByTitle(Long memberId) {
        return liveLessonMapper.findLiveLessonsMemberDoesNotHaveOrderByTitle(memberId);
    }

    @Override
    public LiveLessonDetailResponse getLiveLessonDetails(Long livedId, Long memberId) {
        LiveLessonDetailResponse liveLessonDetailResponse = liveLessonMapper.findLiveLessonDetailById(livedId, memberId)
                .orElseThrow(() -> new CustomException(LIVE_LESSON_NOT_FOUND));
        liveLessonDetailResponse.setBroadcastUrl(getBroadcastUrl(livedId));
        return liveLessonDetailResponse;
    }

    @Override
    public LiveLessonResponse createLiveLesson(Long memberId, LiveLessonRequest liveLessonRequest, MultipartFile thumbnail) {
        try {
            LiveLesson liveLesson = LiveLesson.createLiveLesson(memberId, liveLessonRequest, s3Service.saveFile(thumbnail, LIVE_THUMBNAIL_PATH));
            liveLesson.startLiveLesson();
            liveLessonMapper.insertLiveLesson(liveLesson);
            liveLessonEventService.sendStatusEvent(liveLesson.getLiveId(), liveLesson.getStatus().name());
            Member member = authMapper.selectByMemberId(memberId)
                    .orElseThrow(() -> new CustomException(MEMBER_NOT_FOUND));
            alimService.sendAlimToMultipleMembers(
                    liveLessonMapper.findFcmTokensByLessonId(liveLessonRequest.getLessonId()),
                    liveLesson.getTitle(),
                    liveLesson.getTitle() + SUFFIX_MESSAGE
            );
            return LiveLessonResponse.of(liveLesson, member.getName(), member.getProfileImgUrl());
        } catch (IOException e) {
            deleteS3Files(thumbnail.getOriginalFilename());
            throw new CustomException(S3_UPLOAD_FAILED);
        } catch (Exception e) {
            throw new CustomException(ErrorCode.LIVE_LESSON_CREATION_FAILED);
        }
    }

    @Override
    public CommonResponse endLiveLesson(Long liveId) {
        try {
            LiveLesson liveLesson = liveLessonMapper.findLiveLessonById(liveId)
                    .orElseThrow(() -> new CustomException(LIVE_LESSON_NOT_FOUND));
            if (liveLesson.getStatus() != ON_AIR) throw new CustomException(LIVE_LESSON_NOT_FOUND);
            getThumbnailName(liveLesson);
            deleteS3Files(getThumbnailName(liveLesson));
            liveLesson.endLiveLesson();
            liveLessonMapper.updateLiveLessonStatus(liveId, ENDED);
            liveLessonEventConsumer.removeViewers(liveId);
            liveLessonEventConsumer.removeLikes(liveId);
            liveLessonEventService.sendStatusEvent(liveId, liveLesson.getStatus().name());
            return new CommonResponse(LIVE_LESSON_END_SUCCESS);
        } catch (Exception e) {
            return new CommonResponse(LIVE_LESSON_END_FAILED);
        }
    }

    @Override
    public LiveLessonStatusResponse getLiveLessonStatus(Long liveId) {
        return LiveLessonStatusResponse.from(liveLessonMapper.findLiveLessonById(liveId)
                .orElseThrow(() -> new CustomException(LIVE_LESSON_NOT_FOUND)));
    }

    @Override
    public int getViewsCount(Long liveId) {
        return liveLessonEventConsumer.getViewersCount(liveId);
    }

    @Override
    public int getLikesCount(Long liveId) {
        return liveLessonEventConsumer.getLikesCount(liveId);
    }

    @Override
    public String getShareLink(Long liveId) {
        return getBroadcastUrl(liveId);
    }

    @Override
    public void joinLiveLesson(Long liveId, Long memberId) {
        liveLessonEventService.sendViewerJoinedEvent(liveId, memberId);
    }

    @Override
    public void leaveLiveLesson(Long liveId, Long memberId) {
        liveLessonEventService.sendViewerLeftEvent(liveId, memberId);
    }

    @Override
    public void likeLiveLesson(Long liveId, Long memberId) {
        liveLessonEventService.sendLikeEvent(liveId, memberId);
    }

    private String getThumbnailName(LiveLesson liveLesson) {
        String thumbnailUrl = liveLesson.getThumbnailUrl();
        return thumbnailUrl.split("/")[thumbnailUrl.split("/").length - 1];
    }

    private void deleteS3Files(String thumbnailFilename) {
        s3Service.deleteFile(LIVE_THUMBNAIL_PATH, thumbnailFilename);
    }

    private String getBroadcastUrl(Long liveId) {
        return LIVE_VIEW_URL + liveId;
    }
}
