package com.example.demo.exception;
import com.example.demo.aop.ApplicationContextHolder;
import lombok.Data;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.HttpStatus;
@Data
@PropertySource("classpath:exception.properties")
public class HrmException extends RuntimeException{
    private ExceptionModel exceptionModel;

    public HrmException(String errorCode) {
        ExceptionModel exceptionModel = new ExceptionModel();
        exceptionModel.setStatus(HttpStatus.BAD_REQUEST.value());
        exceptionModel.setErrorCode(errorCode);
        MappingException mappingException = ApplicationContextHolder.getApplicationContext().getBean(MappingException.class);
        String errorMessage = mappingException.getMessageError(errorCode);
        exceptionModel.setMessage(errorMessage);
        this.exceptionModel = exceptionModel;
    }
}
