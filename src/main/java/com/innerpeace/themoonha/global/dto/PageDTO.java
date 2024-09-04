package com.innerpeace.themoonha.global.dto;

import com.innerpeace.themoonha.global.util.Criteria;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PageDTO {
    private int pageCount;
    private int startPage;
    private int endPage;
    private int realEnd;
    private boolean prev;
    private boolean next;
    private int total;
    private Criteria criteria;

    public static PageDTO of(int total, int pageCount, Criteria criteria) {
        return new PageDTO(total, pageCount, criteria);
    }

    private PageDTO(int total, int pageCount, Criteria criteria) {
        this.total = total;
        this.criteria = criteria;
        this.pageCount = pageCount;
        this.endPage = (int)(Math.ceil(criteria.getPageNum() * 1.0 / pageCount)) * pageCount;
        this.startPage = endPage - (pageCount - 1);

        realEnd = (int) (Math.ceil(total * 1.0 / criteria.getSize()));

        if (endPage > realEnd) {
            endPage = realEnd == 0 ? 1 : realEnd;
        }

        prev = startPage > 1;
        next = endPage < realEnd;
    }
}
