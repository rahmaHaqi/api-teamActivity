package com.mat.api.core.errorhandling.businessexeption;

import com.mat.api.core.ResponseBody;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BusinessExceptionMapper<T> {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ResponseBody<String>> handleBusinessException(BusinessException ex) {
        return new ResponseEntity<>(new ResponseBody<>(ex.getErrorCode(), ex.getMessage()), ex.getStatus());
    }
}
