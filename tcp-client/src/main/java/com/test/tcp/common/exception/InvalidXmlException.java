package com.test.tcp.common.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidXmlException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidXmlException() {
        super("유효하지 않은 XML 형식입니다.");
    }

    public InvalidXmlException(String msg) {
        super(msg);
    }
}
