package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import java.util.List;
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
public class PrologueRegisterAdminRequest {
    private String name;
    private Long memberId;
    private String description;
    private int videoCnt;
    private Date startDate;
    private Date expireDate;
    List<String> prologueList;
}
