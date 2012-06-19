package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.reponses.UserAuthenticationResponse;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticatedEvent extends GwtEvent<UserAuthenticatedEventHandler> {

  public static Type<UserAuthenticatedEventHandler> TYPE = new Type<UserAuthenticatedEventHandler>();

  private final UserAuthenticationResponse result;

  public UserAuthenticatedEvent(UserAuthenticationResponse result) {

    this.result = result;
  }

  public Type<UserAuthenticatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(UserAuthenticatedEventHandler handler) {
    handler.onUserAuthenticated(this);
  }
}
