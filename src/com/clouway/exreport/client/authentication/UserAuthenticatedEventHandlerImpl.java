package com.clouway.exreport.client.authentication;

import com.google.gwt.user.client.Cookies;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticatedEventHandlerImpl implements UserAuthenticatedEventHandler {

  @Override
  public void onUserAuthenticated(UserAuthenticatedEvent event) {

    Cookies.setCookie("username", event.getResult().getToken().getUser());
  }
}
