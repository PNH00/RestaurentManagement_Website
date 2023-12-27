package com.restapi.exceptions;

import com.restapi.dto.ErrorResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RMValidateException extends RuntimeException {
    private final ErrorResponse errorResponse;
}