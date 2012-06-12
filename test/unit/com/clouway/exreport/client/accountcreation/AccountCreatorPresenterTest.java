package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.shared.Account;
import org.junit.Test;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorPresenterTest {


  @Test
  public void persistNewAccount() {
    AccountCreatorPresenter accountCreatorPresenter = new AccountCreatorPresenter();
    Account account = new Account();
    accountCreatorPresenter.create(account);
  }
}
