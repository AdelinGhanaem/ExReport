package com.clouway.exreport.client.accountregistration;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountRegisteredEventHandler extends EventHandler {
  void onAccountRegistered(AccountRegisteredEvent event);
}
