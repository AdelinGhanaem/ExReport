package com.clouway.exreport.server;

import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public  class SecurityTokenProviderImpl implements SecurityTokenProvider{

  private static Token token;

  public SecurityTokenProviderImpl() {
  }

  public Token getToken() {
    return token;
  }

  public void setToken(Token token) {
    this.token = token;
  }


}
