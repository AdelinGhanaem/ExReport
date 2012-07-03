package com.clouway.exreport.client.authentication;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface LogoutEventHandler extends EventHandler {
  void onLogout(LogoutEvent event);
}
