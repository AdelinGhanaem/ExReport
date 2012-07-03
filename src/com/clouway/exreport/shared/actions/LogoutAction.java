package com.clouway.exreport.shared.actions;

import com.clouway.exreport.shared.entites.Token;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class LogoutAction<LogoutResponse extends Response> implements Action<LogoutResponse> {

  private  Token token;

  public LogoutAction(Token token) {

    this.token = token;
  }

  public LogoutAction() {
  }

  public Token getToken() {
    return token;
  }



}
