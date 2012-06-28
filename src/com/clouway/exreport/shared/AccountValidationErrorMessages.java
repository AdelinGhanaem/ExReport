package com.clouway.exreport.shared;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountValidationErrorMessages {

  public String invalidEmailForm();

  String emptyEmail();

  String shortPassword();

  String emailPreviouslyReserved();

  String emptyEmailOrPassword();
}
