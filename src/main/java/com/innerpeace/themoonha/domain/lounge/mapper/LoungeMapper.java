package com.innerpeace.themoonha.domain.lounge.mapper;

import com.innerpeace.themoonha.domain.lounge.dto.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

/**
 * 라운지 매퍼
 * @author 조희정
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	조희정       최초 생성
 * 2024.08.25  	조희정       라운지 목록 조회 기능 구현
 * 2024.08.26  	조희정       라운지 홈 조회, 게시글 상세 조회 구현
 * 2024.08.27  	조희정       게시글 생성, 삭제 구현
 * 2024.08.28  	조희정       게시글 수정, 댓글 삭제, 댓글 수정 구현
 * 2024.08.29  	조희정       출석 시작 구현
 * 2024.08.30  	조희정       수강생 출석 여부 수정 구현
 * </pre>
 */
public interface LoungeMapper {
    /**
     * 라운지 목록 조회
     * @param memberId
     * @return
     */
    List<LoungeListResponse> selectLoungeList(@Param("memberId") Long memberId, @Param("role") String role);

    /**
     * 라운지 기본 정보 조회
     * @param loungeId
     * @return
     */
    Optional<LoungeInfoDTO> selectLoungeInfo(Long loungeId);

    /**
     * 라운지 게시글 목록 조회
     * @param loungeId
     * @return
     */
    List<LoungePostDTO> selectLoungePostList(Long loungeId);

    /**
     * 라운지 게시글 이미지 목록 조회
     * @param LoungePostId
     * @return
     */
    List<String> selectLoungePostImgList(Long LoungePostId);

    /**
     * 수강생 출석 여부 조회
     * @param loungeId
     * @param memberId
     * @param role
     * @return
     */
    List<AttendanceDTO> selectAttendanceList(@Param("loungeId") Long loungeId,
                                             @Param("memberId") Long memberId,
                                             @Param("role") String role);

    /**
     * 라운지 회원 목록 조회
     * @param lessonId
     * @return
     */
    List<LoungeMemberDTO> selectLoungeMemberList(Long lessonId);

    /**
     * 라운지 게시글 한 건 조회
     * @param loungePostId
     * @return
     */
    Optional<LoungePostDTO> selectLoungePostDetail(Long loungePostId);

    /**
     * 라운지 게시글 한 건의 댓글 조회
     * @param loungePostId
     * @return
     */
    List<LoungeCommentDTO> selectLoungeCommentList(Long loungePostId);

    /**
     * 라운지 게시물 등록
     * @param loungePostRequest
     * @return
     */
    Long insertLoungePost(@Param("loungePostRequest") LoungePostRequest loungePostRequest, @Param("memberId") Long memberId);

    /**
     * 라운지 게시물 이미지 등록
     * @param loungePostId
     * @param postImgUrls
     * @return
     */
    int insertLoungePostImgUrls(@Param("loungePostId") Long loungePostId, @Param("postImgUrls") List<String> postImgUrls);

    /**
     * 라운지 댓글 등록
     * @param loungeCommentRequest
     * @param memberId
     * @return
     */
    int insertLoungeComment(@Param("loungeComment") LoungeCommentRequest loungeCommentRequest, @Param("memberId") Long memberId);

    /**
     * 라운지 게시글 이미지 삭제
     * @param loungePostId
     * @param postImgUrls
     * @return
     */
    int deleteLoungePostImgUrls(@Param("loungePostId") Long loungePostId, @Param("postImgUrls") List<String> postImgUrls);

    /**
     * 라운지 게시글 내용 수정
     * @param loungePostRequest
     * @return
     */
    int updateLoungePost(LoungePostRequest loungePostRequest);

    /**
     * 라운지 게시물 삭제
     * @param loungePostId
     * @return
     */
    int deleteLoungePost(Long loungePostId);

    /**
     * 라운지 댓글 삭제
     * @param loungePostId
     * @return
     */
    int deleteLoungeComment(@Param("loungePostId") Long loungePostId, @Param("loungeCommentId") Long loungeCommentId);

    /**
     * 라운지 이미지 삭제 및 추가
     * @param loungePostId
     * @param imgUrls
     * @return
     */
    int updateLoungePostImages(@Param("loungePostId") Long loungePostId, @Param("imgUrls") List<String> imgUrls);

    /**
     * 라운지 댓글 수정
     * @param loungeCommentUpdateRequest
     * @return
     */
    int updateLoungeComment(LoungeCommentUpdateRequest loungeCommentUpdateRequest);

    /**
     * 출석 시작
     * @param lessonId
     * @param currentTime
     * @return
     */
    int insertAttendanceList(@Param("lessonId") Long lessonId, @Param("currentTime") String currentTime);

    /**
     * 출석 시작 리스트 조회
     * @param lessonId
     * @param currentTime
     * @return
     */
    List<AttendanceDTO> selectAttendanceStartedList(@Param("lessonId") Long lessonId, @Param("currentTime") String currentTime);

    /**
     * 수강생 출석 여부 수정
     * @param attendanceId
     * @return
     */
    int updateAttendanceYn(Long attendanceId);

}
