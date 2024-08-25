package com.innerpeace.themoonha.domain.craft.mapper;

import com.innerpeace.themoonha.domain.craft.dto.PrologueDTO;
import com.innerpeace.themoonha.domain.craft.dto.SuggestionDTO;
import com.innerpeace.themoonha.domain.craft.dto.WishLessonDTO;
import com.innerpeace.themoonha.global.util.Criteria;

import java.util.List;

public interface CraftMapper {
    List<PrologueDTO> selectPrologueList();
    List<WishLessonDTO> selectWishLessonList();

    List<SuggestionDTO> selectSuggestionList(Criteria criteria);
}
