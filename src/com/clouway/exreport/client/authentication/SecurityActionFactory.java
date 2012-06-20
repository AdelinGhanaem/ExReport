package com.clouway.exreport.client.authentication;

import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecurityActionFactory {
  public <T extends Action<? extends Response>> SecurityAction<T> createSecurity(T t);

}
