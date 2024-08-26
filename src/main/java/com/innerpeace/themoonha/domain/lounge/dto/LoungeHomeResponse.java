package com.innerpeace.themoonha.domain.lounge.dto;

import lombok.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 라운지 홈 응답 DTO
 * @author 조희정
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	조희정       최초 생성
 * </pre>
 */
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoungeHomeResponse {

    private LoungeInfoDTO loungeInfo;
    private List<LoungePostDTO> loungeNoticePostList;
    private List<LoungePostDTO> loungePostList;
    private List<AttendanceDTO> attendanceList;
    private List<LoungeMemberDTO> loungeMemberList;

    public static LoungeHomeResponse of(LoungeInfoDTO loungeInfo,List<LoungePostDTO> loungePostList,
                                        List<AttendanceDTO> attendanceList, List<LoungeMemberDTO> loungeMemberList) {

        List<LoungePostDTO> loungeNoticePostList = loungePostList.stream()
                .filter(LoungePostDTO::isNoticeYn)
                .collect(Collectors.toList());

        return LoungeHomeResponse.builder()
                .loungeInfo(loungeInfo)
                .loungeNoticePostList(loungeNoticePostList)
                .loungePostList(loungePostList)
                .attendanceList(attendanceList)
                .loungeMemberList(loungeMemberList)
                .build();
    }

}
