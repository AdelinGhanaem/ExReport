package com.clouway.exreport.client.security;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecurityActionFactory {
  
  public <T extends Response,A extends Action<T>>SecurityAction<T> createSecurityAction(A a);

}
