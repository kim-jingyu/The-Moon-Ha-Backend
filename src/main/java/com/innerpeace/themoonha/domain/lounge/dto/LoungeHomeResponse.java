package com.innerpeace.themoonha.domain.lounge.dto;

import lombok.*;

import java.util.List;

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
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoungeHomeResponse {

    private LoungeInfoDTO loungeInfo;
    private List<LoungePostDTO> loungeNoticeList;
    private AttendanceMembersResponse attendanceList;
    private List<LoungeMemberDTO> loungeMemberList;

    public static LoungeHomeResponse of(LoungeInfoDTO loungeInfo,
                                        AttendanceMembersResponse attendanceList,
                                        List<LoungePostDTO> loungeNoticeList,
                                        List<LoungeMemberDTO> loungeMemberList) {

        return LoungeHomeResponse.builder()
                .loungeInfo(loungeInfo)
                .attendanceList(attendanceList)
                .loungeNoticeList(loungeNoticeList)
                .loungeMemberList(loungeMemberList)
                .build();
    }

}
