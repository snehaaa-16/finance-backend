package com.zorvyn.finance.service.interfaces;

import com.zorvyn.finance.dto.request.RegisterRequest;

public interface AuthService {

    String register(RegisterRequest request);
}