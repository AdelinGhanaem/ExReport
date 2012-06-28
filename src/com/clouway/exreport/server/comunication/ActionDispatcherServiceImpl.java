package com.clouway.exreport.server.comunication;

import com.clouway.exreport.client.comunication.ActionDispatcherService;
import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.shared.SecurityTokenProvider;
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


  private ActionHandlerRepository repository;

  private AuthenticatedUsersRepository authorizedUsersRepository;

  private SecurityTokenProvider tokenProvider;

  @Inject
  public ActionDispatcherServiceImpl(ActionHandlerRepository repository, AuthenticatedUsersRepository authorizedUsersRepository, SecurityTokenProvider tokenProvider) {
    this.repository = repository;
    this.authorizedUsersRepository = authorizedUsersRepository;
    this.tokenProvider = tokenProvider;
  }

  public <T extends Response> T dispatch(Action<T> action) {
    return (T) repository.getActionHandler(action.getClass()).handle(action);
  }


  public <E extends Response, R extends SecurityResponse<E>, A extends Action<E>> R dispatchSecurityAction(SecurityAction<A> action) {

    Token securityToken = action.getSecurityToke();

    if (securityToken == null) {
      return (R) new SecurityResponse<R>(null);
    }

    if (authorizedUsersRepository.getTokenKey(securityToken)==null) {
      return (R) new SecurityResponse<R>(null);
    }

    tokenProvider.setToken(securityToken);

    E response = this.dispatch(action.getAction());

    return (R) new SecurityResponse<E>(response);

  }
}
