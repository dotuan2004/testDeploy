package com.example.demo.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

@Component
@Slf4j
@PropertySource(value = "classpath:exception.properties", ignoreResourceNotFound = true)
public class MappingException {

    @Autowired
    private  Environment environment;

    public String getMessageError(String errorCode) {
        log.info("-------error code = {} ---------", errorCode);
        String message = environment.getProperty(errorCode);
        log.info("-----read errorCode = {} from exception properties with error message = {}",
                errorCode,message);
        return message;
    }

}
