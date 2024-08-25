package com.innerpeace.themoonha.global.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

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
