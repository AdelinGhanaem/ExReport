package com.clouway.exreport.client.accountcreation;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountValidationErrorMessagesImpl implements AccountValidationErrorMessages {
  @Override
  public String invalidEmailForm() {
    return "The entered email form is invalid\n please enter valid email form. Example:example@mail.com";
  }


  @Override
  public String emptyEmail() {
    return "You can not create account without email";
  }

  @Override
  public String shortPassword() {
    return "please enter password 6 chars long or more";
  }

  @Override
  public String emailPreviouslyReserved() {
    return "Sorry,the email you have entered is previously reserved.\n Try another email. ";
  }

}