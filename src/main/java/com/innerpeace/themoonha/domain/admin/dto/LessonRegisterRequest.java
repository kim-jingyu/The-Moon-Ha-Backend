package com.innerpeace.themoonha.domain.admin.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class LessonRegisterRequest {
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
    private String place;
    private String day;
    private int target;
    private int onlineCost;
}
