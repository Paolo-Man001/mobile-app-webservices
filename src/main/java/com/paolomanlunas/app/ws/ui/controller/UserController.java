package com.paolomanlunas.app.ws.ui.controller;

import org.springframework.web.bind.annotation.*;


@RestController            // register class as Controller
@RequestMapping("users")   // http://localhost:8080/users
public class UserController {

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
