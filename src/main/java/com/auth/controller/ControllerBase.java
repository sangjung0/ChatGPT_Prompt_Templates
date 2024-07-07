package com.auth.controller;

import org.springframework.http.ResponseEntity;

public interface ControllerBase {
    ResponseEntity<?> run() throws Exception;
}
