package com.clouway.exreport.shared.actions;

import com.clouway.exreport.shared.entites.User;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationAction<UserAuthenticationResponse extends Response> implements Action<UserAuthenticationResponse> ,IsSerializable {

  private  User user;

  public UserAuthenticationAction() {

  }

  public UserAuthenticationAction(User user) {
    this.user = user;
  }

  public User getUser() {
    return user;
  }
}
