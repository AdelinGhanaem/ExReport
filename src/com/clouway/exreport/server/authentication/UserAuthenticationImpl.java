package com.clouway.exreport.server.authentication;

import com.clouway.exreport.server.accountcreation.AccountRepository;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.entites.User;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationImpl implements UserAuthentication {

  private AccountRepository repository;
  private final AuthenticatedUsersRepository authenticatedUsersRepository;

  @Inject
  public UserAuthenticationImpl(AccountRepository repository, AuthenticatedUsersRepository authenticatedUsersRepository) {
    this.repository = repository;
    this.authenticatedUsersRepository = authenticatedUsersRepository;
  }

  public Token authenticate(User user) {
    Account account = repository.getAccount(user.getUsername(), user.getPassword());
    if (account != null) {
      Token token = new Token(user.getUsername());
      authenticatedUsersRepository.addToken(token, account.getId());
      return new Token(account.getEmail());
    }
    return null;
  }

}
