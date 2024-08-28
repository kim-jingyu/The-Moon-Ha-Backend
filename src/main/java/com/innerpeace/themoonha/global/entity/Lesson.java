package com.innerpeace.themoonha.global.entity;

import com.innerpeace.themoonha.domain.admin.dto.LessonRegisterRequest;
import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * Lesson 엔티티
 * @author 최유경
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.28  	최유경       최초 생성
 * </pre>
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Lesson {
    private Long lessonId;
    private Long branchId;
    private Long categoryId;
    private Long memberId;
    private String title;
    private String startDate;
    private String endDate;
    private String startTime;
    private String endTime;
    private String summary;
    private int cnt;
    private int cost;
    private String curriculum;
    private String supply;
    private String previewVideoUrl;
    private String thumbnailUrl;
    private String place;
    private String day;
    private Date createdAt;
    private Date deletedAt;
    private int target;
    private int onlineCost;
}
