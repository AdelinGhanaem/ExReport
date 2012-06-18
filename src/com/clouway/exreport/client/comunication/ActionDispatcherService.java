package com.clouway.exreport.client.comunication;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.ActionHandlerNotBoundException;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@RemoteServiceRelativePath("service")
public interface ActionDispatcherService extends RemoteService {

  <T extends Response> T dispatch(Action<T> action) throws ActionHandlerNotBoundException;

}
