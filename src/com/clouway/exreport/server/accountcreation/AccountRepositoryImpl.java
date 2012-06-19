package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.entites.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Ivan Lazov <darkpain1989@gmail.com>
 */
public class AccountRepositoryImpl implements AccountRepository {

  private List<Account> accountList = new ArrayList<Account>();


  @Override
  public void persis(Account account) {
    accountList.add(account);

  }

  @Override
  public Account getAccountByEmail(String email) {
    for (Account account : accountList) {
      if (account.getEmail().equals(email)) {

        return account;


      }
    }
    return null;

  }

  @Override
  public Account getAccount(String username, String password) {
    return null;  //To change body of implemented methods use File | Settings | File Templates.
  }

}
