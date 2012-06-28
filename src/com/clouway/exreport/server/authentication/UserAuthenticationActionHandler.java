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
  private final AuthenticatedUsersRepository repository;

  @Inject
  public UserAuthenticationActionHandler(UserAuthentication authentication, AuthenticatedUsersRepository repository) {
    this.authentication = authentication;
    this.repository = repository;
  }

  @Override
  public UserAuthenticationResponse handle(UserAuthenticationAction action) {
    Token token = authentication.authenticate(action.getUser());

    if (token != null) {
//      repository.addToken(token);
    }

    return new UserAuthenticationResponse(token);
  }
}
