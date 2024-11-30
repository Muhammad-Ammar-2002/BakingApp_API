package net.javaguides.banking.Exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


@ControllerAdvice
public class GlobalExceptionHandler {


    String  time= LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss a", Locale.ENGLISH));

    @ExceptionHandler(AccountException.class)
    public ResponseEntity<?> handelAccountException(AccountException accountException, WebRequest webRequest)
    {
        return new ResponseEntity<>(
                new CustomeResponse<>(
                         time,
                        accountException.getMessage(),
                        webRequest.getDescription(false),
                        "ACCOUNT_NOT_FOUND",
                        Boolean.FALSE
                ), HttpStatus.NOT_FOUND
        );


    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<?> handelIllegalArgumentException(IllegalArgumentException illegalArg, WebRequest webRequest)
    {
        return new ResponseEntity<>(
                new CustomeResponse<>(
                        time,
                        illegalArg.getMessage(),
                        webRequest.getDescription(false),
                        "Transaction fail",
                        Boolean.FALSE
                ), HttpStatus.BANDWIDTH_LIMIT_EXCEEDED
        );
    }

    @ExceptionHandler(JSONParseExceptions.class)
    public ResponseEntity<?> handelJSONParseExceptions(HttpMessageNotReadableException jsonEx, WebRequest webRequest)
    {
        return new ResponseEntity<>(
                new CustomeResponse<>(
                        time,
                        jsonEx.getMessage(),
                        webRequest.getDescription(false),
                        "JASON_ERROR",
                        Boolean.FALSE
                ), HttpStatus.BAD_REQUEST
        );
    }




    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<?> handelServiceException(ServiceException serviceException, WebRequest webRequest)
    {
        return new ResponseEntity<>(
                new CustomeResponse<>(
                        time,
                        serviceException.getMessage(),
                        webRequest.getDescription(false),
                        "JASON_ERROR",
                        Boolean.FALSE
                ), HttpStatus.BAD_REQUEST
        );
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>handelGenericException(Exception e,WebRequest request)
    {
        return new ResponseEntity<>(
                new CustomeResponse<>(
                        time,
                        e.getMessage(),
                        request.getDescription(false),
                        "INTERNAL_SERVER_ERROR",
                        Boolean.FALSE
                )
                ,HttpStatus.INTERNAL_SERVER_ERROR
        );
    }


}
