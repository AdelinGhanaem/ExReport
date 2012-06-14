package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.Account;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreator {

  private final AccountRepository repository;
  private final AccountValidator validator;

  public AccountCreator(AccountRepository repository, AccountValidator validator) {

    this.repository = repository;
    this.validator = validator;
  }


  public Account create(Account account, List<String> errorMessages) {
    errorMessages.addAll(validator.validateAccount(account));
    if (errorMessages.size() == 0) {
      if (repository.getAccountByEmail(account.getEmail()) != null) {
        errorMessages.add("the email is previously reserved");
        return null;
      } else {
        repository.persis(account);
        return account;
      }
    } else {
      return null;
    }
  }


}

