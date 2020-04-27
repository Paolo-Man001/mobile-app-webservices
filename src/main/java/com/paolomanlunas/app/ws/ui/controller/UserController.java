package com.paolomanlunas.app.ws.ui.controller;

import com.paolomanlunas.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.request.UserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.response.UserRest;
import com.paolomanlunas.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;


@RestController            // register class as Controller
@RequestMapping("users")   // http://localhost:8080/users
public class UserController {

   /**
    * MAP<> is used to temporarily store User-record in-memory
    * to use to Update the Details. in-memory data will
    * be erased when app is restarted.
    */
   Map<String, UserRest> usersMap;

   private final UserService userService;

   @Autowired
   public UserController(UserService userService) {
      this.userService = userService;
   }


   /**
    * GET All User. Optional Query-String Request Params
    */
   @GetMapping
   public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                          @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
      return "getUsers() was called... with @Request Parameters - 'page'=" + page + ",'limit'=" + limit + ", and 'sort'=" + sort + ".";
   }

   /**
    * GET A User:
    * SET MediaType to return type of both JSON or XML
    * ResponseEntity<UserRest> is used to enable return of custom HttpStatus
    */
   @GetMapping(path = "/{userId}",
           produces = {
                   MediaType.APPLICATION_XML_VALUE,
                   MediaType.APPLICATION_JSON_VALUE})
   public ResponseEntity<UserRest> getUser(@PathVariable String userId) {

      /* FOR Exception Testing/Debugging :: Uncomment next 2 lines */
//      String firstName = null;
//      int firstNameLength = firstName.length();

      /* Custom Exception Using 'UserServiceException'
       *    Comment Out UserServiceException after testing.
       * */
//      if (true) throw new UserServiceException("A User Service Exception is thrown");


      if (usersMap.containsKey(userId))
         return new ResponseEntity<>(usersMap.get(userId), HttpStatus.OK);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);
   }


   /**
    * CREATE a User:
    * Set to accept + read JSON Body
    */
   @PostMapping(
           consumes = {
                   MediaType.APPLICATION_XML_VALUE,
                   MediaType.APPLICATION_JSON_VALUE},
           produces = {
                   MediaType.APPLICATION_XML_VALUE,
                   MediaType.APPLICATION_JSON_VALUE})
   public ResponseEntity<UserRest> createUser(
           @Valid @RequestBody UserDetailsRequestModel userDetails) {
      /* Original Business-Logic was move/converted into UserServiceImpl(Implementation) to Autowire */
      return new ResponseEntity<>(userService.createUser(userDetails), HttpStatus.OK);
   }


   /**
    * UPDATE a User:
    * Setup exactly the same as Create User.
    */
   @PutMapping(path = "/{userId}",
           consumes = {
                   MediaType.APPLICATION_XML_VALUE,
                   MediaType.APPLICATION_JSON_VALUE},
           produces = {
                   MediaType.APPLICATION_XML_VALUE,
                   MediaType.APPLICATION_JSON_VALUE})
   public UserRest updateUser(
           @PathVariable String userId,
           @Valid @RequestBody UpdateUserDetailsRequestModel userDetails) {
      return userService.updateUser(userId, userDetails);
   }


   /**
    * DELETE a User:
    */
   @DeleteMapping(path = "/{userId}")
   public ResponseEntity<Void> deleteUser(@PathVariable String userId) {
      usersMap.remove(userId);
      return ResponseEntity.noContent().build();   // returns a ResponseEntity Builder
   }
}
