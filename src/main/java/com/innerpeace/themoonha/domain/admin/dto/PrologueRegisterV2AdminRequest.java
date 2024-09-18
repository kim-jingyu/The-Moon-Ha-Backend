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
 * @since 2024.09.17
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.17   최유경       프롤로그 S3 업로드 분리
 * </pre>
 */
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PrologueRegisterV2AdminRequest extends PrologueRegisterAdminRequest{
    List<String> thumbnailList;
    List<String> videoList;
}
