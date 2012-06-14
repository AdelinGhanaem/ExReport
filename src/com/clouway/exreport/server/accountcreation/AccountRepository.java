package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.Account;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountRepository {

  void persis(Account account);

  Account getAccountByEmail(String email);
}
