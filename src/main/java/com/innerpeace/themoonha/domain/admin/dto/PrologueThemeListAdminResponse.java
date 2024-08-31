package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrologueThemeListAdminResponse {
    private Long prologueThemeId;
    private Long memberId;
    private String writer;
    private String name;
    private String description;
    private int videoCnt;
    private String period;
    private String latestUpdateDate;
    private String themeStatus;
}