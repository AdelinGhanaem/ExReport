package com.clouway.exreport.shared.reponses;

import com.clouway.exreport.shared.entites.Token;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.IsSerializable;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationResponse implements Response,IsSerializable {

  private Token token;


  public UserAuthenticationResponse(Token token) {
    this.token = token;
  }

  public UserAuthenticationResponse() {
  }

  public Token getToken() {
    return token;
  }
}
