package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.client.accountcreation.AccountValidationErrorMessages;
import com.clouway.exreport.shared.Account;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorImpl implements AccountCreator{

  private final AccountRepository repository;
  private final AccountValidator validator;
  private final AccountValidationErrorMessages errorMessages;

  public AccountCreatorImpl(AccountRepository repository, AccountValidator validator, AccountValidationErrorMessages errorMessages) {
    this.repository = repository;
    this.validator = validator;

    this.errorMessages = errorMessages;
  }




  public Account create(Account account, List<String> retirnedErros) {

    retirnedErros.addAll(validator.validateAccount(account));
    if (retirnedErros.size() == 0) {
      if (repository.getAccountByEmail(account.getEmail()) != null) {
        retirnedErros.add(errorMessages.emailPreviouslyReserved());
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

