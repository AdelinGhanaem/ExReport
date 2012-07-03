package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityAction<T extends Response> implements Action<SecurityResponse> {

  private Action<T> action;

  private  Token token;

  public SecurityAction() {

  }

  public SecurityAction(Action<T> action, Token token) {
    this.action = action;
    this.token = token;
  }

  public Action<T> getAction() {
    return action;
  }

  public Token getSecurityToke() {
    return token;
  }
}
