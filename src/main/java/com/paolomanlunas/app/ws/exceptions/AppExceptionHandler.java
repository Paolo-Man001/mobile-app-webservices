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
   public ResponseEntity<Object> handleAnyException(Exception ex, WebRequest request) {

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
    * HandleSpecificExceptions.class is a COMBINED Exceptions(Specific and Custom)
    *    COMBINES both NullPointerException and UserServiceException(Custom)
    *    with ONE ExceptionHandler Method. NOTICE 1st Arg changed back to 'Exception' to handle
    *    multiple-types of Exceptions separated by comma ",".
    */
   @ExceptionHandler(value = {NullPointerException.class, UserServiceException.class})
   public ResponseEntity<Object> handleSpecificExceptions(Exception ex, WebRequest request) {

      String errorMessageDescription = ex.getLocalizedMessage();
      // check if error message is null:
      if (errorMessageDescription == null) errorMessageDescription = ex.toString();
      // Use Custom ErrorMessage Class
      ErrorMessage errorMessage = new ErrorMessage(new Date(), errorMessageDescription);

      // ResponseEntity of type = Object( as Body of Http Response )
      return new ResponseEntity<>(
              errorMessage, new HttpHeaders(), HttpStatus.INTERNAL_SERVER_ERROR);
   }
}
