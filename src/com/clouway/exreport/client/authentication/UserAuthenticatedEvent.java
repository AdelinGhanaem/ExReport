package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticatedEvent extends GwtEvent<UserAuthenticatedEventHandler> {

  public static Type<UserAuthenticatedEventHandler> TYPE = new Type<UserAuthenticatedEventHandler>();

  public UserAuthenticatedEvent(Token token) {
    //To change body of created methods use File | Settings | File Templates.
  }


  public Type<UserAuthenticatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(UserAuthenticatedEventHandler handler) {
    handler.onUserAuthenticated(this);
  }
}
