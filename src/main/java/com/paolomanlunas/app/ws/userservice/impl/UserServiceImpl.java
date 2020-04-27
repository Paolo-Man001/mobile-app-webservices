package com.paolomanlunas.app.ws.userservice.impl;

import com.paolomanlunas.app.ws.ui.model.request.UserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.response.UserRest;
import com.paolomanlunas.app.ws.userservice.UserService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@Service
public class UserServiceImpl implements UserService {
   Map<String, UserRest> usersMap;

   @Override
   public UserRest createUser(UserDetailsRequestModel userDetails) {
      UserRest returnUserValue = new UserRest();
      returnUserValue.setEmail(userDetails.getEmail());
      returnUserValue.setFirstName(userDetails.getFirstName());
      returnUserValue.setLastName(userDetails.getLastName()
      );

      String userId = UUID.randomUUID().toString();
      returnUserValue.setUserId(userId);        // sets UUID as UserId

      if (usersMap == null) usersMap = new HashMap<>();
      usersMap.put(userId, returnUserValue);    // temp-save into usersMap-HashMap<'userId'-KEY, returnUserValue-VALUE>

      return returnUserValue;
   }
}
