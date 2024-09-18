package com.innerpeace.themoonha.global.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
@Slf4j
public class RedisViewService {
    private static final long EXPIRE_TIME = 24L;
    private final RedisTemplate<String, Object> redisTemplate;

    public void incrementViewCount(Long memberId, Long domainId, String domain) {
        log.info("domain : {}", domain + "에 대한 캐싱을 시작합니다.");
        log.info("domain Id : {}", domainId);
        String domainFilterKey = domain + ": " + domainId;
        Boolean isViewed = redisTemplate.opsForSet().isMember(domainFilterKey, memberId);

        if (Boolean.FALSE.equals(isViewed)) {
            log.info("memberId 첫 조회: {}", memberId);
            String domainViewCntKey = domain + ":" + domainId + ":viewCount";
            redisTemplate.opsForSet().add(domainFilterKey, memberId);
            redisTemplate.opsForValue().increment(domainViewCntKey);
            redisTemplate.expire(domainFilterKey, EXPIRE_TIME, TimeUnit.HOURS);
            redisTemplate.expire(domainViewCntKey, EXPIRE_TIME, TimeUnit.HOURS);
        }
    }

    public Map<Long, Integer> getDomainViewCounts(String keyPattern) {
        Map<Long, Integer> viewCounts = new HashMap<>();

        Set<String> keys = redisTemplate.keys(keyPattern + "*:viewCount");
        if (keys != null && !keys.isEmpty()) {
            for (String key : keys) {
                Integer viewCount = (Integer) redisTemplate.opsForValue().get(key);
                log.info("key = {}", key);
                log.info("viewCount = {} ", viewCount);
                if (viewCount != null) {
                    String[] parts = key.split(":");
                    if (parts.length > 1) {
                        try {
                            Long domainId = Long.parseLong(parts[1]);
                            viewCounts.put(domainId, viewCount);
                        } catch (NumberFormatException e) {
                            log.error("domainId 파싱 오류: {}", parts[1], e);
                        }
                    }
                }
            }
        } else {
            log.info("조회수를 가져올 키가 없습니다.");
        }

        return viewCounts;
    }


    public void clearDomainCache(String keyPattern) {
        Set<String> keys = redisTemplate.keys(keyPattern);

        if (keys != null && !keys.isEmpty()) {
            for (String key : keys) {
                redisTemplate.delete(key);
                log.info("Redis domainCache {} 삭제 완료", key);
            }
        } else {
            log.info("삭제할 domainCache가 없습니다.");
        }
    }


}
