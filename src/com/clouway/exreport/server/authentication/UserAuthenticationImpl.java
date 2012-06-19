package com.clouway.exreport.server.authentication;

import com.clouway.exreport.server.accountcreation.AccountRepository;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.entites.User;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationImpl implements UserAuthentication{

  private AccountRepository repository;

  @Inject
  public UserAuthenticationImpl(AccountRepository repository) {
    this.repository = repository;
  }

  public Token authenticate(User user) {
    Account account = repository.getAccount(user.getUsername(), user.getPassword());
    if (account != null) {
      return new Token(account.getEmail());
    }
    return null;
  }

}
