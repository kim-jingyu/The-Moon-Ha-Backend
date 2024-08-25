package com.innerpeace.themoonha.global.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * 페이징 처리 클래스
 * @author 손승완
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	손승완       최초 생성
 * </pre>
 */
@Getter
@Setter
@ToString
public class Criteria {
    private int pageNum;
    private int size;
    private static final int DEFAULT_PAGE_SIZE = 5;

    public Criteria() {
        this(1, DEFAULT_PAGE_SIZE);
    }

    public Criteria(int pageNum, int size) {
        this.pageNum = pageNum;
        this.size = size;
    }
}
