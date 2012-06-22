package com.clouway.exreport.server.comunication;

import com.clouway.exreport.client.comunication.ActionDispatcherService;
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

  @Inject
  public ActionDispatcherServiceImpl(ActionHandlerRepository repository) {


    this.repository = repository;
  }

  public <T extends Response> T dispatch(Action<T> action) {
//    return (T) new UserAuthenticationResponse(new Token("adelin"));
    return (T) repository.getActionHandler(action.getClass()).handle(action);
  }
}
