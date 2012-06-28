package com.clouway.exreport.shared;

import com.clouway.exreport.shared.entites.Token;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface SecurityTokenProvider {

  public Token getToken();

  public void setToken(Token token);

}
