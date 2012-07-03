package com.clouway.exreport.client.accountregistration;

import com.clouway.exreport.shared.entites.Account;
import com.google.gwt.event.shared.GwtEvent;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountRegisteredEvent extends GwtEvent<AccountRegisteredEventHandler> {
  public static Type<AccountRegisteredEventHandler> TYPE = new Type<AccountRegisteredEventHandler>();
  private final Account account;


  public AccountRegisteredEvent(Account account) {

    this.account = account;
  }

  public Account getAccount() {
    return account;
  }

  public Type<AccountRegisteredEventHandler> getAssociatedType() {
    return TYPE;
  }

  protected void dispatch(AccountRegisteredEventHandler handler) {
    handler.onAccountRegistered(this);
  }
}
