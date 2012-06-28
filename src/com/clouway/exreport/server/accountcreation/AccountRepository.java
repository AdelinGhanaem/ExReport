package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.entites.Account;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountRepository {

  Account persis(Account account);

  boolean isPreviouslyRegistered(String email);

  Account getAccount(String username, String password);
}
