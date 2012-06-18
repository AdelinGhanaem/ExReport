package com.clouway.exreport.server.comunication;

import com.evo.gad.dispatch.ActionDispatcher;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.ActionHandlerNotBoundException;
import com.evo.gad.shared.Response;
import com.google.gwt.user.server.rpc.RemoteServiceServlet;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ActionDispatcherServiceImpl extends RemoteServiceServlet implements ActionDispatcher {

  private  ActionHandlerRepository repository;

  public ActionDispatcherServiceImpl(ActionHandlerRepository repository) {

    this.repository = repository;
  }


  public ActionDispatcherServiceImpl() {

  }

  @Override
  public <T extends Response> T dispatch(Action<T> action) throws ActionHandlerNotBoundException {


    return (T) repository.getActionHandler(action.getClass()).handle(action);


  }


}
