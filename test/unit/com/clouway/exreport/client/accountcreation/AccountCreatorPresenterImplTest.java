package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.client.accountcreation.view.AccountCreatorView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.AccountValidationErrorMessages;
import com.clouway.exreport.shared.actions.CreateAccountAction;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.reponses.CreateAccountResponse;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorPresenterImplTest {

  @Mock
  private ActionDispatcherServiceAsync service;

  @Mock
  private EventBus handlers;

  private AccountCreatorPresenterImpl accountCreatorPresenter;

  @Mock
  private AccountCreatorView view;

  @Mock
  AccountValidationErrorMessages errorMessages;

  @Before
  public void setUp() {

    initMocks(this);
    handlers = spy(new SimpleEventBus());
    accountCreatorPresenter = new AccountCreatorPresenterImpl(service, handlers, view, errorMessages);
  }

  @Test
  public void createsNewAccountAndFiresEventWhenResponseIsReturned() {

    Account account = new Account("Adelin@mail.com", "123567");

    CreateAccountResponse createAccountResponse = new CreateAccountResponse(account, new ArrayList<String>());

    doOnSuccess(createAccountResponse).when(service).dispatch(isA(CreateAccountAction.class), isA(GotResponse.class));

    accountCreatorPresenter.create(account);

    verify(service).dispatch(isA(CreateAccountAction.class), isA(GotResponse.class));

    verify(handlers).fireEvent(any(AccountCreatedEvent.class));
  }


  @Test
  public void showErrorsAndDoesNotFireEventWhenReturnedResponseHasErrors() {

    Account account = new Account("Adelin@mail.com", "123567");

    String errorMessage = "errors";

    ArrayList<String> errors = new ArrayList<String>();

    errors.add(errorMessage);

    CreateAccountResponse createAccountResponse = new CreateAccountResponse(null, errors);

    doOnSuccess(createAccountResponse).when(service).dispatch(isA(CreateAccountAction.class), isA(GotResponse.class));

    accountCreatorPresenter.create(account);

    verify(service).dispatch(isA(CreateAccountAction.class), isA(GotResponse.class));

    verify(view).showMessages(errors);

    verify(handlers, never()).fireEvent(any(AccountCreatedEvent.class));
  }


  @Test
  public void accountWithInvalidEmailFormIsNotCreated() {

    String invalidEmailForm = "bla bla bla ";

    when(errorMessages.invalidEmailForm()).thenReturn(invalidEmailForm);

    Account account = new Account(invalidEmailForm, "12345678");

    accountCreatorPresenter.create(account);

    verify(service, never()).dispatch(any(CreateAccountAction.class), any(GotResponse.class));

    verify(handlers, never()).fireEvent(any(AccountCreatedEvent.class));

    verify(errorMessages).invalidEmailForm();

    verify(view).showMessage(invalidEmailForm);
  }


  @Test
  public void accountWithEmptyOrPasswordLessThanSizeCharsIsNotCreated() {

    String shortPassword = "231";

    String shotPasswordMessage = "aksljdsad";

    when(errorMessages.shortPassword()).thenReturn(shotPasswordMessage);

    accountCreatorPresenter.create(new Account("mail@mail.com", "123"));

    verify(service, never()).dispatch(any(CreateAccountAction.class), any(GotResponse.class));

    verify(handlers, never()).fireEvent(any(AccountCreatedEvent.class));

    verify(errorMessages).shortPassword();

    verify(view).showMessage(shotPasswordMessage);

  }





}
