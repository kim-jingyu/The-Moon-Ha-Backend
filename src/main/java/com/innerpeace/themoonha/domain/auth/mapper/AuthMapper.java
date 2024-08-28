package com.innerpeace.themoonha.domain.auth.mapper;

import com.innerpeace.themoonha.global.entity.Member;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

/**
 * 회원가입/로그인 매퍼
 * @author 최유경
 * @since 2024.08.25
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.25  	최유경       최초 생성
 * </pre>
 */
@Mapper
public interface AuthMapper {
    int insertMember(Member member);
    Optional<Member> selectByUsername(String username);
    Optional<Member> selectByMemberId(Long memberId);
}
