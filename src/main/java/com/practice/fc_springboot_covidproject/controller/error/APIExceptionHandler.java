package com.practice.fc_springboot_covidproject.controller.error;

import com.practice.fc_springboot_covidproject.constant.ErrorCode;
import com.practice.fc_springboot_covidproject.dto.APIErrorResponse;
import com.practice.fc_springboot_covidproject.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@RestControllerAdvice(annotations = RestController.class)
public class APIExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> general(GeneralException e){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false, errorCode, errorCode.getMessage(e)
                ));
    }

    @ExceptionHandler
    public ResponseEntity<APIErrorResponse> exception(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return ResponseEntity
                .status(status)
                .body(APIErrorResponse.of(
                        false, errorCode, errorCode.getMessage(e)
                ));
    }

}
