package com.paolomanlunas.app.ws.exceptions;

public class UserServiceException extends RuntimeException {

   /** ENABLE " Serializable class without 'serialVersionUID' ", by:
    *  Ctrl + Shift + A
    *  SET " Serializable class without 'serialVersionUID' " --> 'ON'.
    *  Highlight Class Name then Alt + Enter.
    *  Click, " Add 'serialVersionUID' field "
    */
   private static final long serialVersionUID = 7480523035021610151L;

   public UserServiceException(String message) {
      super(message);
   }
}
