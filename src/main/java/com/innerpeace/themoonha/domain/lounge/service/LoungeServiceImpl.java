package com.innerpeace.themoonha.domain.lounge.service;

import com.innerpeace.themoonha.domain.lounge.dto.*;
import com.innerpeace.themoonha.domain.lounge.mapper.LoungeMapper;
import com.innerpeace.themoonha.global.dto.CommonResponse;
import com.innerpeace.themoonha.global.exception.CustomException;
import com.innerpeace.themoonha.global.exception.ErrorCode;
import com.innerpeace.themoonha.global.vo.SuccessCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
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
 * 2024.08.25  	조희정       라운지 목록 조회 기능 구현
 * 2024.08.26  	조희정       라운지 홈 조회, 게시글 상세 조회 구현
 * 2024.08.27  	조희정       게시글 생성, 삭제, 수정 구현
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
     * @param memberId
     * @return
     */
    @Override
    public CommonResponse addLoungePost(LoungePostRequest loungePostRequest, Long memberId) {
        // 게시물 저장
        if(loungeMapper.insertLoungePost(loungePostRequest, memberId) != 1) {
            throw new CustomException(ErrorCode.LOUNGE_POST_FAILED);
        }

        // 이미지 저장
        if (loungePostRequest.getLoungePostImgList() != null && !loungePostRequest.getLoungePostImgList().isEmpty()) {
            int insertedCount = loungeMapper.insertLoungePostImgUrls(loungePostRequest.getLoungePostId(), loungePostRequest.getLoungePostImgList());

            if (insertedCount != loungePostRequest.getLoungePostImgList().size()) {
                throw new CustomException(ErrorCode.LOUNGE_POST_FAILED);
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

    /**
     * 라운지 게시물 수정
     * @param loungePostId
     * @param loungePostRequest
     * @return
     */
    @Override
    public CommonResponse modifyLoungePost(Long loungePostId, LoungePostRequest loungePostRequest) {
        // 기존 이미지 URL 리스트 가져오기
        List<String> oldImageUrls = loungeMapper.selectLoungePostImgList(loungePostId);

        // 새로운 이미지 리스트
        List<String> newImageUrls = loungePostRequest.getLoungePostImgList();

        // 삭제할 이미지 URL 리스트
        List<String> imagesToDelete = oldImageUrls.stream()
                .filter(oldUrl -> !newImageUrls.contains(oldUrl))
                .collect(Collectors.toList());

        // 추가된 이미지 URL 리스트
        List<String> imagesToAdd = newImageUrls.stream()
                .filter(newUrl -> !oldImageUrls.contains(newUrl))
                .collect(Collectors.toList());

        // 이미지 삭제
        if (!imagesToDelete.isEmpty()) {
            int deletedCount = loungeMapper.deleteLoungePostImgUrls(loungePostId, imagesToDelete);

            if (deletedCount != imagesToDelete.size()) {
                throw new CustomException(ErrorCode.LOUNGE_POST_UPDATE_FAILED);
            }
        }

        // 이미지 추가
        if (!imagesToAdd.isEmpty()) {
            int insertedCount = loungeMapper.insertLoungePostImgUrls(loungePostId, imagesToAdd);

            if (insertedCount != imagesToAdd.size()) {
                throw new CustomException(ErrorCode.LOUNGE_POST_UPDATE_FAILED);
            }
        }

        // 나머지 게시물 내용 수정
        if (loungeMapper.updateLoungePost(loungePostRequest) != 1) {
            throw new CustomException(ErrorCode.LOUNGE_POST_UPDATE_FAILED);
        }

        return CommonResponse.of(true, SuccessCode.LOUNGE_POST_UPDATE_SUCCESS.getMessage());
    }

    /**
     * 라운지 게시물 삭제
     * @param loungePostId
     * @return
     */
    @Override
    public CommonResponse deleteLoungePost(Long loungePostId) {
        // 게시물 삭제
        if(loungeMapper.deleteLoungePost(loungePostId) != 1) {
            throw new CustomException(ErrorCode.LOUNGE_POST_DELETE_FAILED);
        }
        // 댓글 삭제
        if(loungeMapper.deleteLoungeComment(loungePostId) < 0) {
            throw new CustomException(ErrorCode.LOUNGE_POST_DELETE_FAILED);
        }
        // 이미지 삭제
        if(loungeMapper.deleteLoungePostImgUrls(loungePostId, null) < 0) {
            throw new CustomException(ErrorCode.LOUNGE_POST_DELETE_FAILED);
        }
        return CommonResponse.of(true, SuccessCode.LOUNGE_POST_DELETE_SUCCESS.getMessage());
    }

}


