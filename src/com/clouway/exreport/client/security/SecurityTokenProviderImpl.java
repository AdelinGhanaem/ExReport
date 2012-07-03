package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.user.client.Cookies;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityTokenProviderImpl implements SecurityTokenProvider {


  @Override
  public Token getToken() {
    Token token = null;
    String user = Cookies.getCookie("username");
    if (user != null) {
      token = new Token(user);
    }
    return token;
  }


  public void setToken(Token token) {
    Cookies.setCookie("username", token.getUser());
  }

  @Override
  public void clearToken() {
      Cookies.removeCookie("username");
  }
}
