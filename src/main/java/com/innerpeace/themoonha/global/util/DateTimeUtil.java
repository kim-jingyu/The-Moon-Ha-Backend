package com.innerpeace.themoonha.global.util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

/**
 * 시간 포맷 유틸리티
 * @author 조희정
 * @since 2024.08.26
 * @version 1.0
 *
 * <pre>
 * 수정일        수정자        수정내용
 * ----------  --------    ---------------------------
 * 2024.08.26  	조희정       최초 생성
 * </pre>
 */
public class DateTimeUtil {

    public static String timeAgo(String timeString) {
        if (timeString == null) {
            return "";
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime timeObj = LocalDateTime.parse(timeString, formatter);
        LocalDateTime now = LocalDateTime.now();

        Duration duration = Duration.between(timeObj, now);

        // 오늘 이전이라면 날짜 그대로 반환
        if (timeObj.toLocalDate().isBefore(now.toLocalDate())) {
            return formatDate(timeObj);
        }

        // 오늘이라면 날짜 포맷
        long seconds = duration.getSeconds();

        if (seconds < 60) {
            return seconds + "초 전";
        } else if (seconds < 3600) {
            long minutes = seconds / 60;
            return minutes + "분 전";
        } else if (seconds < 86400) {
            long hours = seconds / 3600;
            return hours + "시간 전";
        } else {
            return formatDate(timeObj);
        }
    }

    private static String formatDate(LocalDateTime time) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy.MM.dd. HH:mm", Locale.KOREAN);
        return time.format(dateFormatter);
    }
}

