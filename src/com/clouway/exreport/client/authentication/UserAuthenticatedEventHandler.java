package com.clouway.exreport.client.authentication;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserAuthenticatedEventHandler extends EventHandler {
  void onUserAuthenticated(UserAuthenticatedEvent event);
}
