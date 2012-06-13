package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.shared.Account;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatedEvent extends GwtEvent<AccountCreatedEventHandler> {
  public static Type<AccountCreatedEventHandler> TYPE = new Type<AccountCreatedEventHandler>();


  public AccountCreatedEvent(Account account) {

  }

  public Type<AccountCreatedEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(AccountCreatedEventHandler handler) {
    handler.onAccountCreated(this);
  }
}