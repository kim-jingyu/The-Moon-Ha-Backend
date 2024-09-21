package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

import java.util.List;

/**
 * 강좌 수강 신청 요청 DTO
 *
 * @author 손승완
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.27  	손승완       최초 생성
 * </pre>
 * @since 2024.08.27
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SugangRequest {
    private List<Long> cartIdList;
}
