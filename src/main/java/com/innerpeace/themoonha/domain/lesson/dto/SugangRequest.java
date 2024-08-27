package com.innerpeace.themoonha.domain.lesson.dto;

import lombok.*;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SugangRequest {
    private List<Long> cartIdList;
}
