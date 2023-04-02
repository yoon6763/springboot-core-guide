package com.springboot.test.common.exception;

import com.springboot.test.common.Constant;
import org.springframework.http.HttpStatus;

public class CustomException extends Exception{
    private Constant.ExceptionClass exceptionClass;
    private HttpStatus httpStatus;

    public CustomException(Constant.ExceptionClass exceptionClass, HttpStatus httpStatus, String message) {
        super(exceptionClass.toString() + message);
        this.exceptionClass = exceptionClass;
        this.httpStatus = httpStatus;
    }

    public Constant.ExceptionClass getExceptionClass() {
        return exceptionClass;
    }

    public int getHttpStatusCode() {
        return httpStatus.value();
    }

    public String getHttpStatusType() {
        return httpStatus.getReasonPhrase();
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }
}
