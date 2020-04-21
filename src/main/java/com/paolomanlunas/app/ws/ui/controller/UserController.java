package com.paolomanlunas.app.ws.ui.controller;

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
   public String getUser(@PathVariable String userId) {
      return "getUser() was called. User Id = " + userId;
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
