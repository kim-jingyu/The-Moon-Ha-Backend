package com.innerpeace.themoonha.global.entity;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Prologue {
    private Long prologueId;
    private Long prologueThemeId;
    private String title;
    private String videoUrl;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private String thumbnailUrl;
}
