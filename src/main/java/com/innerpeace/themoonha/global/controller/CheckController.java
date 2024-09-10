package com.innerpeace.themoonha.global.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CheckController {
    @GetMapping("/health-check")
    public ResponseEntity<Void> checkHealthStatus() {
        return ResponseEntity.ok().build();
    }
}
