package com.clouway.exreport.client.comunication;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
@RemoteServiceRelativePath("service")
public interface ActionDispatcherService extends RemoteService {

  public <T extends Response> T dispatch(Action<T> action);

//  public <E extends Response, R extends SecurityResponse<E>, A extends Action<E>> R dispatch(SecurityAction<A> action);

}
