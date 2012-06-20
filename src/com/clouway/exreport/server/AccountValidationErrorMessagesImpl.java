package com.clouway.exreport.server;

import com.clouway.exreport.client.accountcreation.AccountValidationErrorMessages;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountValidationErrorMessagesImpl implements AccountValidationErrorMessages {
  @Override
  public String invalidEmailForm() {
    return "invalid email form";

  }

  @Override
  public String emptyEmail() {
    return "emptyEmail";

  }

  @Override
  public String shortPassword() {
    return "shortPassword";

  }

  @Override
  public String emailPreviouslyReserved() {
    return "email previouslyReserved";

  }
}
