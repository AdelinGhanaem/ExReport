package com.clouway.exreport.shared.actions;

import com.clouway.exreport.shared.entites.User;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationAction<UserAuthenticationResponse> implements Action<Response> {

  private  User user;

  public UserAuthenticationAction(User user) {

    this.user = user;
  }

  public User getUser() {
    return user;
  }
}