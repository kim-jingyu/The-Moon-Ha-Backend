package com.innerpeace.themoonha.domain.bite.dto;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class BeforeAfterRequest {
    private Long lessonId;
    private String title;
    private List<String> hashtags;
}
