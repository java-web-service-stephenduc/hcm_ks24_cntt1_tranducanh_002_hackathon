package com.re.hackathon.exception.handler;


import com.re.hackathon.exception.NotFoundException;
import com.re.hackathon.exception.ValidateException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

@RestControllerAdvice
//có global exception
// lỗi validation, khi client giử dữ liệu sai validate , bắt lỗi 400 bad_request + danh sách trường lỗi
// lỗi not found, khi truy vấn với 1 id không tồn tại, bắt lỗi 404 not_found.
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<Map<String, String>> handleValidateException(ValidateException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(Map.of("error", ex.getMessage()));
    }

    @ExceptionHandler(ValidateException.class)
    public ResponseEntity<Map<String, String>> handleNotFoundException(NotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(Map.of("error", ex.getMessage()));
    }

}
