package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * 어드민 숏폼 등록 요청 dto
 * @author 최유경
 * @since 2024.08.30
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.30  	최유경       최초 생성
 * </pre>
 */
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
