package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.SecurityTokenProvider;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityActionFactoryImpl implements SecurityActionFactory {

  private SecurityTokenProvider provider;

  @Inject
  SecurityActionFactoryImpl(SecurityTokenProvider provider) {
    this.provider = provider;
  }

  @Override
  public <T extends Response, A extends Action<T>> SecurityAction<T> createSecurityAction(A a) {
     return  new SecurityAction<T>(a,provider.getToken());

  }

}
