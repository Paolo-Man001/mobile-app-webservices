package com.paolomanlunas.app.ws.userservice;

import com.paolomanlunas.app.ws.ui.model.request.UserDetailsRequestModel;
import com.paolomanlunas.app.ws.ui.model.response.UserRest;


public interface UserService {
   UserRest createUser(UserDetailsRequestModel userDetails);
}
