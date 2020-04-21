package com.paolomanlunas.app.ws.ui.controller;

import com.paolomanlunas.app.ws.ui.model.response.UserRest;
import org.springframework.web.bind.annotation.*;


@RestController            // register class as Controller
@RequestMapping("users")   // http://localhost:8080/users
public class UserController {

   // Get All User. Optional Query-String Request Params
   // Optional Params for page
   @GetMapping
   public String getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                          @RequestParam(value = "limit", defaultValue = "50") int limit,
                          @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort) {
      return "getUsers() was called... with @Request Parameters - 'page'=" + page + ",'limit'=" + limit + ", and 'sort'=" + sort + ".";
   }

   // Get A User
   @GetMapping(path = "/{userId}")
   public UserRest getUser(@PathVariable String userId) {
      UserRest returnUserValue = new UserRest();
      returnUserValue.setEmail("email@eamil.com");
      returnUserValue.setFirstName("James");
      returnUserValue.setLastName("Bond");
//      returnUserValue.setUserId(userId);
      return returnUserValue;
   }

   @PostMapping
   public String createUser() {
      return "createUser() was called.";
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
