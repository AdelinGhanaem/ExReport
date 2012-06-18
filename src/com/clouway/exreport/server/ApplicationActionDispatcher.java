package com.clouway.exreport.server;

import com.evo.gad.dispatch.ActionDispatcher;
import com.evo.gad.dispatch.ActionHandlerRepository;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.ActionHandlerNotBoundException;
import com.evo.gad.shared.Response;
import com.google.inject.Inject;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/18/12
 * Time: 8:04 AM
 * To change this template use File | Settings | File Templates.
 */
public class ApplicationActionDispatcher implements ActionDispatcher {

  @Inject
  private ActionHandlerRepository repository;


  @Override
  public <T extends Response> T dispatch(Action<T> action) throws ActionHandlerNotBoundException {
    System.out.println(action.toString());
    return (T) repository.getActionHandler(action.getClass()).handle(action);
  }
}
