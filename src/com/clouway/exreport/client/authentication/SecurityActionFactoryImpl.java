package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityActionFactoryImpl implements SecurityActionFactory{

  private SecurityTokenProvider provider;

  SecurityActionFactoryImpl(SecurityTokenProvider provider) {
    this.provider = provider;
  }

    public <T extends Action<? extends Response>> SecurityAction<T> createSecurity(T t) {
    return new SecurityAction<T>(t, provider.getToken());
  }

}
