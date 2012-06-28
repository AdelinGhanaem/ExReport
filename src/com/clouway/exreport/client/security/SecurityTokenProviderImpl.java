package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityTokenProviderImpl implements SecurityTokenProvider {


  private Token token;

  @Override
  public Token getToken() {
    return token;
  }


  @Override
  public void setToken(Token token) {
    this.token = token;

  }
}
