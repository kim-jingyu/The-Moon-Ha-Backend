package com.innerpeace.themoonha.domain.lounge.service;

import com.innerpeace.themoonha.domain.lounge.dto.*;
import com.innerpeace.themoonha.domain.lounge.mapper.LoungeMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 라운지 서비스 구현체
 * @author 조희정
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	조희정       최초 생성
 * 2024.08.25  	조희정       findLoungeList 메서드 추가
 * 2024.08.26  	조희정       findLoungeHome, findLoungePostDetail 메서드 추가
 * 2024.08.27  	조희정       addLoungePost, addLoungeComment 메서드 추가
 * </pre>
 */
@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class LoungeServiceImpl implements LoungeService {

    private final LoungeMapper loungeMapper;

    /**
     * 라운지 목록 조회
     * @param memberId
     * @return
     */
    @Override
    public List<LoungeListResponse> findLoungeList(Long memberId) {
        return loungeMapper.selectLoungeList(memberId);
    }

    /**
     * 라운지 홈 조회
     * @param loungeId
     * @param memberId
     * @param role
     * @return
     */
    @Override
    public LoungeHomeResponse findLoungeHome(Long loungeId, Long memberId, String role) {
        LoungeInfoDTO loungeInfo = loungeMapper.selectLoungeInfo(loungeId)
                .orElseThrow(() -> new CustomException(ErrorCode.LOUNGE_NOT_FOUND));
        List<LoungePostDTO> loungePostList = loungeMapper.selectLoungePostList(loungeId)
                .stream()
                .map(loungePost -> {
                    List<String> postImgUrl = loungeMapper.selectLoungePostImgList(loungePost.getLoungePostId());
                    return LoungePostDTO.of(loungePost, postImgUrl);
                })
                .collect(Collectors.toList());
        List<AttendanceDTO> attendanceList = loungeMapper.selectAttendanceList(loungeId, memberId, role);
        List<LoungeMemberDTO> loungeMemberList = loungeMapper.selectLoungeMemberList(loungeId);

        return LoungeHomeResponse.of(
                loungeInfo,
                loungePostList,
                attendanceList,
                loungeMemberList
        );
    }

    /**
     * 라운지 게시글 상세 조회
     * @param loungePostId
     * @return
     */
    @Override
    public LoungePostDetailDTO findLoungePostDetail(Long loungePostId) {

        LoungePostDTO loungePost = loungeMapper.selectLoungePostDetail(loungePostId)
                .map(loungePostDTO -> {
                    List<String> postImgUrl = loungeMapper.selectLoungePostImgList(loungePostDTO.getLoungePostId());
                    return LoungePostDTO.of(loungePostDTO, postImgUrl);
                })
                .orElseThrow(() -> new CustomException(ErrorCode.LOUNGE_POST_NOT_FOUND));
        List<LoungeCommentDTO> loungeCommentList = loungeMapper.selectLoungeCommentList(loungePostId);
        return LoungePostDetailDTO.of(
                loungePost,
                loungeCommentList
        );
    }

    /**
     * 라운지 게시물 등록
     * @param loungePostRequest
     * @return
     */
    @Override
    public CommonResponse addLoungePost(LoungePostRequest loungePostRequest, Long memberId) {
        // 게시물 저장
        if(loungeMapper.insertLoungePost(loungePostRequest, memberId) != 1) {
            throw new CustomException(ErrorCode.LOUNGE_POST_FAILED);
        };

        if (loungePostRequest.getLoungePostImgList() != null && !loungePostRequest.getLoungePostImgList().isEmpty()) {
            for (String postImgUrl : loungePostRequest.getLoungePostImgList()) {
                if (loungeMapper.insertLoungePostImgUrls(loungePostRequest.getLoungePostId(), postImgUrl) != 1) {
                    throw new CustomException(ErrorCode.LOUNGE_POST_FAILED);
                }
            }
        }

        return CommonResponse.of(true, SuccessCode.LOUNGE_POST_ADD_SUCCESS.getMessage());
    }

    /**
     * 라운지 게시물에 댓글 등록
     * @param loungeCommentRequest
     * @param memberId
     * @return
     */
    @Override
    public CommonResponse addLoungeComment(LoungeCommentRequest loungeCommentRequest, Long memberId) {
        if(loungeMapper.insertLoungeComment(loungeCommentRequest, memberId) != 1) {
            throw new CustomException(ErrorCode.LOUNGE_COMMENT_FAILED);
        }
        return CommonResponse.of(true, SuccessCode.LOUNGE_COMMENT_ADD_SUCCESS.getMessage());
    }
}
