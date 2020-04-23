package com.paolomanlunas.app.ws.ui.controller;

import com.paolomanlunas.app.ws.ui.model.request.UserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController            // register class as Controller
@RequestMapping("users")   // http://localhost:8080/users
public class UserController {

   /* Get All User. Optional Query-String Request Params
    * */
   @GetMapping
   public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                          @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
      return "getUsers() was called... with @Request Parameters - 'page'=" + page + ",'limit'=" + limit + ", and 'sort'=" + sort + ".";
   }

   /* Get A User:
    * SET MediaType to return type of both JSON or XML
    * ResponseEntity<UserRest> is used to enable return of custom HttpStatus
    * */
   @GetMapping(path = "/{userId}",
           produces = {
                   MediaType.APPLICATION_XML_VALUE,
                   MediaType.APPLICATION_JSON_VALUE
           })
   public ResponseEntity<UserRest> getUser(@PathVariable String userId) {
      UserRest returnUserValue = new UserRest();
      returnUserValue.setEmail("email@eamil.com");
      returnUserValue.setFirstName("James");
      returnUserValue.setLastName("Bond");

      return new ResponseEntity<>(returnUserValue, HttpStatus.OK);
   }


   /* Create a User:
    *  Set to accept + read JSON Body
    * */
   @PostMapping(consumes = {
           MediaType.APPLICATION_XML_VALUE,
           MediaType.APPLICATION_JSON_VALUE},
           produces = {
                   MediaType.APPLICATION_XML_VALUE,
                   MediaType.APPLICATION_JSON_VALUE
           })
   public ResponseEntity<UserRest> createUser(@Valid @RequestBody UserDetailsRequestModel userDetails) {
      UserRest returnUserValue = new UserRest();
      returnUserValue.setEmail(userDetails.getEmail());
      returnUserValue.setFirstName(userDetails.getFirstName());
      returnUserValue.setLastName(userDetails.getLastName()
      );

      return new ResponseEntity<>(returnUserValue, HttpStatus.OK);
   }

   @PutMapping
   public String updateUser() {
      return "updateUser() was called.";
   }

   @DeleteMapping
   public String deleteUser() {
      return "deleteUser() was called.";
   }
}
