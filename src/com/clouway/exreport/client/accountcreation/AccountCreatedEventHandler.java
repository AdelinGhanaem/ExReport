package com.clouway.exreport.client.accountcreation;

import com.google.gwt.event.shared.EventHandler;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountCreatedEventHandler extends EventHandler {
  void onAccountCreated(AccountCreatedEvent event);
}
