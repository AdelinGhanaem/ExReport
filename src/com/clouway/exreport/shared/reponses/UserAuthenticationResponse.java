package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Token;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationResponse implements Response {

  private Token token;

  public UserAuthenticationResponse(Token token) {
    this.token = token;
  }

  public Token getToken() {

    return token;
  }
}
