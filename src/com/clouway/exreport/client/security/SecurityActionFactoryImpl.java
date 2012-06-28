package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
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

  public <T extends Action<? extends Response>> SecurityAction<T> createSecurityAction(T t) {
    Token token = provider.getToken();
    return new SecurityAction<T>(t, token);
  }

}
