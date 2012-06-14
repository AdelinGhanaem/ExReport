package com.clouway.exreport.client.comunication;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.RemoteService;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface ActionDispatcherService extends RemoteService {

  <T extends Response> T dispatch(Action<T> action);

}
