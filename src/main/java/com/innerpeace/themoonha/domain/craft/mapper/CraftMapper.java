package com.innerpeace.themoonha.domain.craft.mapper;

import com.innerpeace.themoonha.domain.craft.dto.PrologueDTO;
import com.innerpeace.themoonha.domain.craft.dto.WishLessonDTO;

import java.util.List;

public interface CraftMapper {
    List<PrologueDTO> selectPrologueList();
    List<WishLessonDTO> selectWishLessonList();
}
