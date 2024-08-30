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
public class PrologueListAdminResponse {
    private Long prologueId;
    private String title;
    private String videoUrl;
    private Date createdAt;
    private String thumbnailUrl;
    private int type;
}
