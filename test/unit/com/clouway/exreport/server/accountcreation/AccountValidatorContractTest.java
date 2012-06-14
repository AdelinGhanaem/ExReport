package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.client.accountcreation.AccountValidationErrorMessages;
import com.clouway.exreport.shared.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountValidatorContractTest {


  private AccountValidator validator;

  @Mock
  AccountValidationErrorMessages errorMessages;


  @Before
  public void setUp() {

    initMocks(this);

    validator = new AccountValidatorImpl(errorMessages);

  }

  @Test
  public void returnsEmptyErrorsListWhenEmailHasValidForm() {

    String validEmailForm = "mail@mail.com";

    Account account = new Account(validEmailForm,"1234567");

    List<String> errors = validator.validateAccount(account);

    assertThat(errors.size(), is(0));

  }

  @Test
  public void indicatesWhenEmailHasNotValidForm() {

    String invalidEmailForm = "bla bla bla";

    String errorMessage = "an error messageeeeeeeeeee !!";

    Account account = new Account(invalidEmailForm, "1234567");

    when(errorMessages.invalidEmailForm()).thenReturn(errorMessage);

    List<String> errors = validator.validateAccount(account);

    assertThat(errors.size(), is(equalTo(1)));

    assertThat(errors.contains(errorMessage), is(true));

  }

  @Test
  public void indicatesWhenPasswordIsLessThanSixChars() {

    String shortPass = "1234";

    Account account = new Account("mail@mail.com", shortPass);

    String errorMessage = "short pass my nigga ... !";

    when(errorMessages.shortPassword()).thenReturn(errorMessage);

    List<String> errors = validator.validateAccount(account);

    assertThat(errors.size(), is(1));

    assertThat(errors.contains(errorMessage), is(true));

  }

  @Test
  public void returnsEmptyErrorsListWhenPasswordIsMoreThanSixChars() {

    String validEmailForm = "mail@mail.com";
    String password = "1234567";

    Account account = new Account(validEmailForm, password);

    List<String> errors = validator.validateAccount(account);

    assertThat(errors.size(), is(0));

  }




}
