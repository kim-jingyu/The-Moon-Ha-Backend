package com.innerpeace.themoonha.domain.lounge.dto;

import lombok.*;

import java.util.List;

/**
 * 수강생 출석 정보 응답 DTO
 * @author 조희정
 * @since 2024.09.10
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.10  	조희정       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AttendanceMembersResponse {
    private List<String> attendanceDates;
    private List<AttendanceMembersDTO> students;
}
