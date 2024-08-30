package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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