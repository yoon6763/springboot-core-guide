package com.springboot.test.service;

import com.springboot.test.data.dto.SignUpResultDto;

public interface SignService {
    SignUpResultDto signUp(String id, String password, String name, String role);
    SignUpResultDto signIn(String id, String password) throws RuntimeException;
}
