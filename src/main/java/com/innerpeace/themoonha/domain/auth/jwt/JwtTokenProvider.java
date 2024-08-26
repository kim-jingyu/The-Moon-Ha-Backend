package com.innerpeace.themoonha.domain.auth.jwt;

import com.innerpeace.themoonha.domain.auth.dto.JwtDTO;
import com.innerpeace.themoonha.global.entity.Member;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * JWT 생성,추출
 * @author 최유경
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	최유경       최초 생성
 * </pre>
 */
@Slf4j
@Component
@RequiredArgsConstructor
@PropertySource(value={"classpath:application.properties"})
public class JwtTokenProvider {
    private Key key;

    @Value("${jwt.access.expire.time}")
    private long ACCESS_TOKEN_EXPIRE_TIME;

    @Value("${jwt.refresh.expire.time}")
    private long REFRESH_TOKEN_EXPIRE_TIME;

    @Value("${jwt.secret}")
    private String SECRET_KEY;

    // JWT 비밀 키를 초기화하는 메서드
    @PostConstruct
    public void init() {
        byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    // JWT 생성
    public JwtDTO generateToken(Member member){
        long now = (new Date()).getTime();

        // AccessToken 생성
        String accessToken = Jwts.builder()
                .claim("memberId", member.getMemberId())
                .claim("username", member.getUsername())
                .claim("role", member.getMemberRole().getRole())
                .setExpiration(new Date(now + ACCESS_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        // RefreshToken 생성
        String refreshToken = Jwts.builder()
                .claim("memberId", member.getMemberId())
                .claim("username", member.getUsername())
                .claim("role", member.getMemberRole().getRole())
                .setExpiration(new Date(now + REFRESH_TOKEN_EXPIRE_TIME))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();

        return JwtDTO.of(accessToken,refreshToken);
    }

    // JWT 복호화
    public Authentication getAuthentication(String accessToken){
        Claims claims = parseClaims(accessToken);
        if (claims.get("auth") == null) {
            throw new RuntimeException("권한 정보가 없는 토큰입니다.");
        }

        // 클레임에서 권한 정보 가져옴
        Collection<? extends GrantedAuthority> authorities = Arrays.stream(claims.get("auth").toString().split(","))
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        // UserDetails 객체를 만들어서 Authentication 리턴
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, "", authorities);
    }
//

    // JWT 클레임 추출
    // JWT 토큰 안의 Claim 정보를 추출
    private Claims parseClaims(String accessToken){
        try{
            return Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(accessToken)
                    .getBody();
        } catch (ExpiredJwtException e){
            return e.getClaims();
        }
    }

    // JWT 검증
    public boolean validateToken(String token){
        try{
            Jwts.parserBuilder().setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true;
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.info("유효하지 않은 JWT 토큰입니다. ", e);
        } catch (ExpiredJwtException e) {
            log.info("만료된 JWT 토큰입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.info("제공되지 않은 JWT 토큰입니다. ", e);
        } catch (IllegalArgumentException e) {
            log.info("JWT Claim 문자열이 비어있습니다. ", e);
        }
        return false;
    }

    // 요청에서 토큰 추출
    // Request Header 로부터 JWT 토큰을 추출
    public String resolveToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer"))
            return bearerToken.substring(7);
        return null;
    }

}
