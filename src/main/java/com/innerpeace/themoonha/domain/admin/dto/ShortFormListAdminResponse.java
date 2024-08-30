package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 어드민 숏폼 조회 응답 dto
 * @author 최유경
 * @since 2024.08.30
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.30  	최유경       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class ShortFormListAdminResponse {
    private Long shortFormId;
    private String name;
    private String videoUrl;
    private Date startDate;
    private Date expireDate;
    private Date createdAt;
    private Long lessonId;
    private Long branchId;
    private String branchName;
    private Long memberId;
    private String tutorName;
    private String title;
}