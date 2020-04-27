package com.paolomanlunas.app.ws.userservice.impl;

import com.paolomanlunas.app.ws.shared.Utils;
import com.paolomanlunas.app.ws.ui.model.request.UpdateUserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.request.UserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.response.UserRest;
import com.paolomanlunas.app.ws.userservice.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;


@Service
public class UserServiceImpl implements UserService {
   Map<String, UserRest> usersMap;
   private Utils utils;

   // CONSTRUCTOR empty: empty-const won't require Utils to be final :
   public UserServiceImpl() {
   }

   @Autowired
   public UserServiceImpl(Utils utils) {
      this.utils = utils;
   }


   /**
    * CREATE New User
    */
   @Override
   public UserRest createUser(UserDetailsRequestModel userDetails) {
      UserRest returnUserValue = new UserRest();
      returnUserValue.setEmail(userDetails.getEmail());
      returnUserValue.setFirstName(userDetails.getFirstName());
      returnUserValue.setLastName(userDetails.getLastName());

      // @Autowired Utils.class DI to generate UUID
      String userId = utils.generateUserId();
      returnUserValue.setUserId(userId);        // sets UUID as UserId

      if (usersMap == null) usersMap = new HashMap<>();
      usersMap.put(userId, returnUserValue);    // temp-save into usersMap-HashMap<'userId'-KEY, returnUserValue-VALUE>

      return returnUserValue;
   }

   /**
    * UPDATE User by userId
    */
   @Override
   public UserRest updateUser(String userId, UpdateUserDetailsRequestModel userDetails) {
      UserRest storedUserDetails = usersMap.get(userId);
      storedUserDetails.setFirstName(userDetails.getFirstName());
      storedUserDetails.setLastName(userDetails.getLastName());
      usersMap.put(userId, storedUserDetails);

      return storedUserDetails;
   }
}
