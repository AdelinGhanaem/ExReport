package com.clouway.exreport.server.security;

import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.dispatch.ActionDispatcher;
import com.evo.gad.dispatch.ActionHandler;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityActionHandler<R extends Response, E extends SecurityResponse<R>, A extends Action<R>, S extends SecurityAction<A>> implements ActionHandler<S, E> {


  private ActionDispatcher dispatcher;
  private AuthenticatedUsersRepository authorizedUsersRepository;

  @Inject
  public SecurityActionHandler(ActionDispatcher dispatcher, AuthenticatedUsersRepository repository) {
    this.dispatcher = dispatcher;
    this.authorizedUsersRepository = repository;
  }

  public SecurityActionHandler() {

  }

  @Override
  public E handle(S action) {

    Token securityToke = action.getSecurityToke();

    if (securityToke == null) {
      return (E) new SecurityResponse<R>(null);
    }

    if (!authorizedUsersRepository.isAuthorized(securityToke)) {
      return (E) new SecurityResponse<R>(null);
    }

    R response = dispatcher.dispatch(action.getAction());

    return (E) new SecurityResponse<R>(response);

  }

}
