package com.innerpeace.themoonha.domain.live.vo;

import lombok.*;

import java.util.Date;

import static lombok.AccessLevel.*;

/**
 * LiveSetting VO
 * @author 김진규
 * @since 2024.09.01
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.09.01  	김진규       최초 생성
 * </pre>
 */
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = PROTECTED)
public class LiveSetting {
    private Long settingId;
    private Long liveId;
    private String resolution;
    private int bitrate;
    private int frameRate;
    private String codec;
    private String audioBitrate;
    private String sampleRate;
    private Date createdAt;
    private Date updatedAt;
}
