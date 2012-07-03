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

  public static ThreadLocal<Token> tokenThreadLocal = new ThreadLocal<Token>();

  @Inject
  public ActionDispatcherServiceImpl(ActionHandlerRepository repository, AuthenticatedUsersRepository authorizedUsersRepository, SecurityTokenProvider tokenProvider) {

    this.repository = repository;

    this.authorizedUsersRepository = authorizedUsersRepository;

    this.tokenProvider = tokenProvider;

  }

  public <T extends Response> T dispatch(Action<T> action) {

    Action<T> original = action;

    if (action instanceof SecurityAction) {
      SecurityAction<T> securedAction = (SecurityAction<T>) action;

      Token token = securedAction.getSecurityToke();

      if (token == null) {
        return (T) new SecurityResponse<T>(null);
      }

      String key = authorizedUsersRepository.getTokenKey(token);

      if (key == null) {
        return (T) new SecurityResponse<T>(null);
      }

      tokenProvider.setToken(token);
      tokenThreadLocal.set(token);
      original = securedAction.getAction();

      Response response = (T) repository.getActionHandler(original.getClass()).handle(original);
      return (T) new SecurityResponse(response);
    }

    Response response = repository.getActionHandler(original.getClass()).handle(original);
    return (T) response;


  }

}
