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
 * 2024.08.25  	조희정       selectLoungeList 메서드 생성
 * 2024.08.26  	조희정       selectLoungeInfo, selectLoungePostList, selectAttendanceList, selectLoungeMemberList, selectLoungePostDetail 메서드 생성
 * 2024.08.27  	조희정       selectLoungePostImgList, selectLoungeCommentList, insertLoungePost, insertLoungePostImgUrls, insertLoungeComment 메서드 생성
 * </pre>
 */
public interface LoungeMapper {
    /**
     * 라운지 목록 조회
     * @param memberId
     * @return
     */
    List<LoungeListResponse> selectLoungeList(Long memberId);

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
     * 수강생 출석 정보 조회
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
     * @param loungeId
     * @return
     */
    List<LoungeMemberDTO> selectLoungeMemberList(Long loungeId);

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
    int insertLoungePost(@Param("loungePostRequest") LoungePostRequest loungePostRequest, @Param("memberId") Long memberId);

    /**
     * 라운지 게시물 이미지 등록
     * @param loungePostId
     * @param postImgUrl
     * @return
     */
    int insertLoungePostImgUrls(@Param("loungePostId") Long loungePostId, @Param("postImgUrl") String postImgUrl);

    /**
     * 라운지 댓글 등록
     * @param loungeCommentRequest
     * @param memberId
     * @return
     */
    int insertLoungeComment(@Param("loungeComment") LoungeCommentRequest loungeCommentRequest, @Param("memberId") Long memberId);
}
