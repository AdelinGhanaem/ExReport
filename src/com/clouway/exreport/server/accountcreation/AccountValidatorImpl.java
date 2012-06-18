package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.client.accountcreation.AccountValidationErrorMessages;
import com.clouway.exreport.shared.entites.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountValidatorImpl implements AccountValidator {

  private final AccountValidationErrorMessages errorMessages;

  public AccountValidatorImpl(AccountValidationErrorMessages errorMessages) {


    this.errorMessages = errorMessages;

  }

  @Override
  public List<String> validateAccount(Account account) {

    List<String> returnedErrorMessages = new ArrayList<String>();
    //TODO:these ifs must be replaces with some kind of map ... !
    if (!Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").matcher(account.getEmail()).matches()) {
      returnedErrorMessages.add(errorMessages.invalidEmailForm());
    }
    if (account.getPassword().length() < 6) {
      returnedErrorMessages.add(errorMessages.shortPassword());
    }

    return returnedErrorMessages;
  }

}
