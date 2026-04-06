package com.sneha.finance.service.interfaces;

import com.sneha.finance.dto.request.LoginRequest;
import com.sneha.finance.dto.request.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
    String login(LoginRequest request);
}