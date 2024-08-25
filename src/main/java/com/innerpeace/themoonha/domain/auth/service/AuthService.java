package com.innerpeace.themoonha.domain.auth.service;

import com.innerpeace.themoonha.domain.auth.dto.SignUpRequest;

public interface AuthService {
    int signUp(SignUpRequest request);
    boolean checkAvailableUsername(String userName);
}
