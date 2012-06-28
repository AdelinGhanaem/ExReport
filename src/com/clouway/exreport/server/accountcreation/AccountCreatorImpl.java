package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.shared.AccountValidationErrorMessages;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.entites.Token;
import com.google.inject.Inject;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorImpl implements AccountCreator {

  private final AccountRepository repository;

  private final AccountValidator validator;

  private final AccountValidationErrorMessages errorMessages;
  private final AuthenticatedUsersRepository authenticatedUsersRepository;

  @Inject
  public AccountCreatorImpl(AccountRepository repository, AccountValidator validator, AccountValidationErrorMessages errorMessages, AuthenticatedUsersRepository authenticatedUsersRepository) {
    this.repository = repository;
    this.validator = validator;
    this.errorMessages = errorMessages;
    this.authenticatedUsersRepository = authenticatedUsersRepository;
  }


  public Account create(Account account, List<String> retirnedErros) {

    retirnedErros.addAll(validator.validateAccount(account));
    if (retirnedErros.size() == 0) {
      if (repository.isPreviouslyRegistered(account.getEmail())) {
        retirnedErros.add(errorMessages.emailPreviouslyReserved());
        return null;
      } else {
        Account persistedAccount = repository.persis(account);
        authenticatedUsersRepository.addToken(new Token(persistedAccount.getEmail()), persistedAccount.getId());
        return account;
      }
    } else {
      return null;
    }
  }

}

