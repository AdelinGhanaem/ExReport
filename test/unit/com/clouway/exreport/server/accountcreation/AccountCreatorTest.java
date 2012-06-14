package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.Account;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;


/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */

public class AccountCreatorTest {


  @Mock
  AccountRepository repository;

  @Mock
  AccountValidator accountValidator;

  AccountCreator accountCreator;

  @Before
  public void setUp() {

    initMocks(this);

    accountCreator = new AccountCreator(repository, accountValidator);

  }

  @Test
  public void createsANewAccountAndReturnsTheNewCreateAccount() {

    Account account = new Account("mail@mail.com", "123456");

    List<String> errorMessages = new ArrayList<String>();

    when(repository.getAccountByEmail(account.getEmail())).thenReturn(null);


    Account createdAccount = accountCreator.create(account, errorMessages);

    verify(repository).getAccountByEmail(account.getEmail());

    verify(repository).persis(account);

    assertThat(createdAccount, is(notNullValue()));

    assertThat(createdAccount.getEmail(), is(equalTo(account.getEmail())));

    assertThat(errorMessages.size(), is(equalTo(0)));
  }


  @Test
  public void accountWithPreviouslyReservedEmailIsNotCreated() {

    Account account = new Account("mail@mail.com", "123456");

    when(repository.getAccountByEmail(account.getEmail())).thenReturn(account);

    List<String> errorMessages = new ArrayList<String>();

    Account createdAccount = accountCreator.create(account, errorMessages);

    verify(repository).getAccountByEmail(account.getEmail());

    verify(repository, never()).persis(account);

    assertThat(createdAccount, is(nullValue()));

    assertThat(errorMessages.size(), is(equalTo(1)));

  }


  @Test
  public void accountWithInvalidEmailFormIsNotPersisted() {

    String invalidEmailForm = "bla bla bla";

    Account account = new Account(invalidEmailForm, "1234567");

    String errorMessage = "Invalid email form. the provided email should the form mail@mail.com";

    List<String> errorMessages = new ArrayList<String>();

    errorMessages.add(errorMessage);

    Account createdAccount = accountCreator.create(account, errorMessages);

    when(accountValidator.validateAccount(account)).thenReturn(errorMessages);

    verify(repository, never()).getAccountByEmail(invalidEmailForm);

    verify(repository, never()).persis(account);

    assertThat(createdAccount, is(nullValue()));

    assertThat(errorMessages.size(), is(1));

    assertThat(errorMessages.contains(errorMessage), is(true));
  }


  @Test
  public void accountWithAnotherInvalidEmailFormIsNotPersisted() {

    String invalidEmailForm = "guchi guchi";

    Account account = new Account(invalidEmailForm, "1234567");

    String errorMessage = "Invalid email form. the provided email should the form mail@mail.com";

    List<String> errorMessages = new ArrayList<String>();

    errorMessages.add(errorMessage);

    when(accountValidator.validateAccount(account)).thenReturn(errorMessages);

    Account createdAccount = accountCreator.create(account, new ArrayList<String>());

    verify(repository, never()).getAccountByEmail(invalidEmailForm);

    verify(repository, never()).persis(account);

    assertThat(createdAccount, is(nullValue()));

    assertThat(errorMessages.size(), is(1));

    assertThat(errorMessages.contains(errorMessage), is(true));
  }


  @Test
  public void accountWithShortPasswordIsNotPersisted() {

    String shortPassword = "12345";

    Account account = new Account("mail@mail.com", shortPassword);

    String errorMessage = "short password your password should not be less than 6 chars";

    List<String> errorMessages = new ArrayList<String>();

    errorMessages.add(errorMessage);

    when(accountValidator.validateAccount(account)).thenReturn(errorMessages);

    Account createdAccount = accountCreator.create(account, new ArrayList<String>());

    verify(repository, never()).getAccountByEmail(shortPassword);

    verify(repository, never()).persis(account);

    assertThat(createdAccount, is(nullValue()));

    assertThat(errorMessages.size(), is(1));

    assertThat(errorMessages.contains(errorMessage), is(true));

  }


}
