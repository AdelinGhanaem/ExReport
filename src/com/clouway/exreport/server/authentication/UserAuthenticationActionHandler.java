package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.actions.UserAuthenticationAction;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.UserAuthenticationResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationActionHandler implements ActionHandler<UserAuthenticationAction, UserAuthenticationResponse> {


  private final UserAuthentication authentication;

  @Inject
  public UserAuthenticationActionHandler(UserAuthentication authentication) {

    this.authentication = authentication;
  }

  @Override
  public UserAuthenticationResponse handle(UserAuthenticationAction action) {

    Token token = authentication.authenticate(action.getUser());
    return new UserAuthenticationResponse(token);
  }
}
