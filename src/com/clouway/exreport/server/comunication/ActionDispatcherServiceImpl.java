package com.clouway.exreport.server.comunication;

import com.clouway.exreport.client.comunication.ActionDispatcherService;
import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.dispatch.ActionDispatcher;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@Singleton
public class ActionDispatcherServiceImpl extends RemoteServiceServlet implements ActionDispatcherService, ActionDispatcher {


  ActionHandlerRepository repository;
  private AuthenticatedUsersRepository authorizedUsersRepository;


  @Inject
  public ActionDispatcherServiceImpl(ActionHandlerRepository repository, AuthenticatedUsersRepository authorizedUsersRepository) {
    this.repository = repository;
    this.authorizedUsersRepository = authorizedUsersRepository;
  }

  public <T extends Response> T dispatch(Action<T> action) {
    return (T) repository.getActionHandler(action.getClass()).handle(action);
  }


  public <E extends Response, R extends SecurityResponse<E>, A extends Action<E>> R dispatchSecurityAction(SecurityAction<A> action) {
    System.out.println(action.getAction().getClass());
    Token securityToke = action.getSecurityToke();

    if (securityToke == null) {
      return (R) new SecurityResponse<R>(null);
    }

    if (!authorizedUsersRepository.isAuthorized(securityToke)) {
      return (R) new SecurityResponse<R>(null);
    }

    E response = this.dispatch(action.getAction());

    return (R) new SecurityResponse<E>(response);

  }
}
