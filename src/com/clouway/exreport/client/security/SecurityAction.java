package com.clouway.exreport.client.security;

import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityAction<A extends Action<? extends Response>> implements Action<SecurityResponse> {

  private A action;

  private  Token token;

  public SecurityAction() {
  }

  public SecurityAction(A action, Token token) {
    this.action = action;
    this.token = token;
  }

  public A getAction() {
    return action;
  }

  public Token getSecurityToke() {
    return token;
  }
}
