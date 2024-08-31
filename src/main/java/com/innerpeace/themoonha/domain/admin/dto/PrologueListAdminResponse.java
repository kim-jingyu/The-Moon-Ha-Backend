package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrologueListAdminResponse {
    private Long prologueId;
    private Long prologueThemeId;
    private String title;
    private String videoUrl;
    private Date latestUpdateDate;
    private String thumbnailUrl;
    private int likeCnt;
}
