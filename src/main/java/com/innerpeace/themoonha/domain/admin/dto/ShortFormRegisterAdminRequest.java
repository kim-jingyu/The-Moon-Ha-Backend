package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ShortFormRegisterAdminRequest {
    private Long lessonId;
    private String name;
    private Date startDate;
    private Date expireDate;
}
