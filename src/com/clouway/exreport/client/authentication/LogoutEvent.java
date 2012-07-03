package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class LogoutEvent extends GwtEvent<LogoutEventHandler> {
  public static Type<LogoutEventHandler> TYPE = new Type<LogoutEventHandler>();
  private final Token token;

  public LogoutEvent(Token token) {

    this.token = token;
  }

  public Token getToken() {
    return token;
  }

  public Type<LogoutEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(LogoutEventHandler handler) {
    handler.onLogout(this);
  }
}
