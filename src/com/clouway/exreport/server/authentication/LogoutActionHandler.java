package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.actions.LogoutAction;
import com.clouway.exreport.shared.reponses.LogoutResponse;
import com.evo.gad.dispatch.ActionHandler;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class LogoutActionHandler implements ActionHandler<LogoutAction, LogoutResponse> {

  private final AuthenticatedUsersRepository repository;

  @Inject
  public LogoutActionHandler(AuthenticatedUsersRepository repository) {
    this.repository = repository;
  }

  @Override
  public LogoutResponse handle(LogoutAction action) {
    if (action.getToken() != null) {
      repository.deleteToken(action.getToken());
    }
    return new LogoutResponse();
  }

}
