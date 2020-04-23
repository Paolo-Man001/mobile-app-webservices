package com.paolomanlunas.app.ws.ui.controller;

import com.paolomanlunas.app.ws.ui.model.request.UserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.response.UserRest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController            // register class as Controller
@RequestMapping("users")   // http://localhost:8080/users
public class UserController {

   /* MAP<> is used to temporarily store User-record in-memory
    *  to use to Update the Details. in-memory data will
    *  be erased when app is restarted.
    *  */
   Map<String, UserRest> usersMap;


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
      if (usersMap.containsKey(userId))
         return new ResponseEntity<>(usersMap.get(userId), HttpStatus.OK);
      return new ResponseEntity<>(HttpStatus.NO_CONTENT);

//      UserRest returnUserValue = new UserRest();
//      returnUserValue.setEmail("email@eamil.com");
//      returnUserValue.setFirstName("James");
//      returnUserValue.setLastName("Bond");
//      return new ResponseEntity<>(returnUserValue, HttpStatus.OK);
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


      String userId = UUID.randomUUID().toString();
      returnUserValue.setUserId(userId);        // sets UUID as UserId

      if (usersMap == null) usersMap = new HashMap<>();
      usersMap.put(userId, returnUserValue);    // temp-save into usersMap-HashMap<'userId'-KEY, returnUserValue-VALUE>

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
