package com.innerpeace.themoonha.domain.auth.mapper;

import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;
import com.innerpeace.themoonha.global.entity.Member;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AuthMapper {
    int insertMember(Member member);
    Optional<Member> selectMemberByUsername(String userName);
}
