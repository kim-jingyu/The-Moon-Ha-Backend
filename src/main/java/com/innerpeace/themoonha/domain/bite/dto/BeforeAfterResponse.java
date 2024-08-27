package com.innerpeace.themoonha.domain.bite.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeforeAfterResponse {
    private String beforeUrl;
    private int beforeIsImage;
    private String afterUrl;
    private int afterIsImage;
    private String title;
    private String profileImageUrl;
    private String memberName;
    private List<String> hashtags;
}
