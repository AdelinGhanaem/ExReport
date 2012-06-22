package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.entites.Account;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountRepositoryImpl implements AccountRepository {

  private List<Account> accountList = new ArrayList<Account>();

  public AccountRepositoryImpl() {
    accountList.add(new Account("mail", "123"));
  }

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
    for (Account account : accountList) {
      if (username.equals(account.getEmail()) && password.equals(account.getPassword())) {
        return account;
      }
    }
    return null;

  }

}
