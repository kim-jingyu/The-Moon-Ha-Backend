package com.innerpeace.themoonha.domain.lesson.controller;

import com.innerpeace.themoonha.domain.lesson.dto.LessonListRequest;
import com.innerpeace.themoonha.domain.lesson.dto.LessonListResponse;
import com.innerpeace.themoonha.domain.lesson.service.LessonService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lesson")
@Slf4j
public class LessonController {
    private final LessonService lessonService;

    @GetMapping("/list")
    public ResponseEntity<LessonListResponse> lessonList(LessonListRequest lessonListRequest) {
        LessonListResponse lessonList = lessonService.findLessonList(lessonListRequest);
        log.info("lessonList = {}", lessonList);
        return ResponseEntity.ok(lessonList);
    }
}
