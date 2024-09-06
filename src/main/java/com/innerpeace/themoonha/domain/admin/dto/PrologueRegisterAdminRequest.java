package com.innerpeace.themoonha.domain.admin.dto;

import java.util.Date;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 어드민 프롤로그 등록 요청 dto
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
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrologueRegisterAdminRequest {
    private String name;
//    private Long memberId;
    private String description;
    private int videoCnt;
    private Date startDate;
    private Date expireDate;
    List<String> prologueList;
}
