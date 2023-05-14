package com.shopziel.exception;

import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {


    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ProblemDetail sqlExceptionHandler(SQLException err,WebRequest req){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, err.getMessage());

        problemDetail.setTitle("Duplicate Entry Not Allowed...");

        return problemDetail;
    }

    @ExceptionHandler(ProductException.class)
    public ProblemDetail productExceptionHandler(ProductException err,WebRequest req){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, err.getMessage());

        return problemDetail;
    }

    @ExceptionHandler(SellerException.class)
    public ProblemDetail sellerExceptionHandler(SellerException err,WebRequest req){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, err.getMessage());

        return problemDetail;
    }
    

    @ExceptionHandler(Exception.class)
    public ProblemDetail globalExceptionHandler(Exception err,WebRequest req){

        ProblemDetail problemDetail = ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, err.getMessage());

        return problemDetail;
    }


}
