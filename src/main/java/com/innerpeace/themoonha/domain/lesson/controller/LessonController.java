package com.innerpeace.themoonha.domain.lesson.controller;

import com.innerpeace.themoonha.domain.lesson.mapper.LessonMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lessons")
public class LessonController {

    private final LessonMapper lessonMapper;
    @GetMapping()
    public String getLesson() {
        lessonMapper.getLesson();
        return "Hello";
    }
}
