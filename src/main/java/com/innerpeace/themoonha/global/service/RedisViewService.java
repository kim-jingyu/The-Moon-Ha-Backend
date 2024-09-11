package com.innerpeace.themoonha.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisViewService {
    private static final long EXPIRE_TIME = 24L;
    private final RedisTemplate<String, Object> redisTemplate;

    public void incrementViewCount(Long memberId, Long domainId, String domain) {
        String domainFilterKey = domain + ": " + domainId;
        Boolean isViewed = redisTemplate.opsForSet().isMember(domainFilterKey, memberId);

        if (Boolean.FALSE.equals(isViewed)) {
            String domainViewCntKey = domain + ": " + domainId + ":viewCount";
            redisTemplate.opsForSet().add(domainFilterKey, memberId);
            redisTemplate.opsForValue().increment(domainViewCntKey);
            redisTemplate.expire(domainFilterKey, EXPIRE_TIME, TimeUnit.HOURS);
            redisTemplate.expire(domainViewCntKey, EXPIRE_TIME, TimeUnit.HOURS);
        }
    }

}
