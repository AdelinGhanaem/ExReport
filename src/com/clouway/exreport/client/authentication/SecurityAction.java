package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityAction<A extends Action<? extends Response>> implements Action<SecurityResponse> {

  private A action;

  private Token securityToke;

  public SecurityAction(A action, Token token) {
    this.action = action;
  }

  public A getAction() {
    return action;
  }

  public Token getSecurityToke() {
    return securityToke;
  }
}
