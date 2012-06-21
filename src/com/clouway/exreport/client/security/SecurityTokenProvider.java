package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.entites.Token;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecurityTokenProvider {

  public Token getToken();

  public void setToken(Token token);

}
