package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.user.client.Cookies;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecurityTokenProvider {

  public Token getToken();

  public void setToken(Token token);

}
