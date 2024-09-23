package com.innerpeace.themoonha.global.dto;

import java.util.List;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
public class S3UploadCompleteDTO {
    private String fullPath;
    private String uploadId;
    private List<S3UploadPartsDetailDTO> parts;

    @Getter
    @AllArgsConstructor
    @NoArgsConstructor(access = AccessLevel.PROTECTED)
    @ToString
    public class S3UploadPartsDetailDTO {
        private Integer partNumber;
        private String awsETag;
    }
}
