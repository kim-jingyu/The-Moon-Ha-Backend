package com.innerpeace.themoonha.domain.craft.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import java.util.Date;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class SuggestionDTO {
    private String memberName;
    private String memberProfileImgUrl;
    private String content;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy.MM.dd", timezone = "Asia/Seoul")
    private Date createdAt;
}
