package com.clouway.exreport.client.accountregistration;

import com.clouway.exreport.client.accountregistration.view.AccountRegistrationView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.AccountValidationErrorMessages;
import com.clouway.exreport.shared.actions.RegisterAccountAction;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.reponses.RegisterAccountResponse;
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
public class AccountRegistrationPresenterImplTest {

  @Mock
  private ActionDispatcherServiceAsync service;

  @Mock
  private EventBus handlers;

  private AccountRegistrationPresenterImpl accountRegistrationPresenter;

  @Mock
  private AccountRegistrationView view;

  @Mock
  AccountValidationErrorMessages errorMessages;

  @Before
  public void setUp() {

    initMocks(this);
    handlers = spy(new SimpleEventBus());
    accountRegistrationPresenter = new AccountRegistrationPresenterImpl(service, handlers, view, errorMessages);
  }

  @Test
  public void createsNewAccountAndFiresEventWhenResponseIsReturned() {

    Account account = new Account("Adelin@mail.com", "123567");

    RegisterAccountResponse registerAccountResponse = new RegisterAccountResponse(account, new ArrayList<String>());

    doOnSuccess(registerAccountResponse).when(service).dispatch(isA(RegisterAccountAction.class), isA(GotResponse.class));

    accountRegistrationPresenter.register(account);

    verify(service).dispatch(isA(RegisterAccountAction.class), isA(GotResponse.class));

    verify(handlers).fireEvent(any(AccountRegisteredEvent.class));
  }


  @Test
  public void showErrorsAndDoesNotFireEventWhenReturnedResponseHasErrors() {

    Account account = new Account("Adelin@mail.com", "123567");

    String errorMessage = "errors";

    ArrayList<String> errors = new ArrayList<String>();

    errors.add(errorMessage);

    RegisterAccountResponse registerAccountResponse = new RegisterAccountResponse(null, errors);

    doOnSuccess(registerAccountResponse).when(service).dispatch(isA(RegisterAccountAction.class), isA(GotResponse.class));

    accountRegistrationPresenter.register(account);

    verify(service).dispatch(isA(RegisterAccountAction.class), isA(GotResponse.class));

    verify(view).showMessages(errors);

    verify(handlers, never()).fireEvent(any(AccountRegisteredEvent.class));
  }


  @Test
  public void accountWithInvalidEmailFormIsNotCreated() {

    String invalidEmailForm = "bla bla bla ";

    when(errorMessages.invalidEmailForm()).thenReturn(invalidEmailForm);

    Account account = new Account(invalidEmailForm, "12345678");

    accountRegistrationPresenter.register(account);

    verify(service, never()).dispatch(any(RegisterAccountAction.class), any(GotResponse.class));

    verify(handlers, never()).fireEvent(any(AccountRegisteredEvent.class));

    verify(errorMessages).invalidEmailForm();

    verify(view).showMessage(invalidEmailForm);
  }


  @Test
  public void accountWithEmptyOrPasswordLessThanSizeCharsIsNotCreated() {

    String shortPassword = "231";

    String shotPasswordMessage = "aksljdsad";

    when(errorMessages.shortPassword()).thenReturn(shotPasswordMessage);

    accountRegistrationPresenter.register(new Account("mail@mail.com", "123"));

    verify(service, never()).dispatch(any(RegisterAccountAction.class), any(GotResponse.class));

    verify(handlers, never()).fireEvent(any(AccountRegisteredEvent.class));

    verify(errorMessages).shortPassword();

    verify(view).showMessage(shotPasswordMessage);

  }





}
