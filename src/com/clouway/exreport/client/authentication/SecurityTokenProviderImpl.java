package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.entites.Token;

import javax.servlet.http.Cookie;

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
