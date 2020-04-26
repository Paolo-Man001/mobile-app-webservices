package com.paolomanlunas.app.ws.exceptions;

import com.paolomanlunas.app.ws.ui.model.response.ErrorMessage;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

   /**
    * Exception.class is a GENERIC Exception
    */
   @ExceptionHandler(value = {Exception.class})
   public ResponseEntity<Object> handleAnyExceptions(Exception ex, WebRequest request) {
      String errorMessageDescription = ex.getLocalizedMessage();
      // check if error message is null:
      if (errorMessageDescription == null) errorMessageDescription = ex.toString();

      // Use Custom ErrorMessage Class
      ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

      // ResponseEntity of type = Object( as Body of Http Response )
      return new ResponseEntity<>(
              // ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
              errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
   }


   /**
    * NullPointerException.class is a SPECIFIC Exception
    */
   @ExceptionHandler(value = {NullPointerException.class})
   public ResponseEntity<Object> handleNullPointerExceptions(NullPointerException ex, WebRequest request) {
      String errorMessageDescription = ex.getLocalizedMessage();
      // check if error message is null:
      if (errorMessageDescription == null) errorMessageDescription = ex.toString();

      // Use Custom ErrorMessage Class
      ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

      // ResponseEntity of type = Object( as Body of Http Response )
      return new ResponseEntity<>(
//              ex, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
              errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
