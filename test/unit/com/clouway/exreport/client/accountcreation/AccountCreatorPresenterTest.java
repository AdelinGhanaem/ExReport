package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.client.accountcreation.view.AccountCreatorView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.shared.Account;
import com.evo.gad.shared.Action;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;

import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorPresenterTest {

  @Mock
  private ActionDispatcherServiceAsync service;
  @Mock
  private HasHandlers handlers;

  private AccountCreatorPresenter accountCreatorPresenter;

  @Mock
  private AccountCreatorView view;

  @Mock
  AccountValidationErrorMessages errorMessages;

  @Before
  public void setUp() {

    initMocks(this);
    accountCreatorPresenter = new AccountCreatorPresenter(service, handlers, view, errorMessages);
  }

  @Test
  public void createsNewAccountAndFiresEventWhenResponseIsReturned() {

    Account account = new Account("email@mail.com", "123567");

    AccountCreatedResponse accountResponse = new AccountCreatedResponse(account, new ArrayList<String>());

    doOnSuccess(accountResponse).when(service).dispatch(any(Action.class), any(AsyncCallback.class));

    accountCreatorPresenter.create(account);

    verify(service).dispatch(any(CreateAccountAction.class), any(GotResponse.class));

    verify(handlers).fireEvent(any(AccountCreatedEvent.class));
  }


  @Test
  public void showErrorsAndDoesNotFireEventWhenReturnedResponseHasErrors() {

    Account account = new Account("email@mail.com", "123567");

    String errorMessage = "errors";

    ArrayList<String> errors = new ArrayList<String>();

    errors.add(errorMessage);

    AccountCreatedResponse accountResponse = new AccountCreatedResponse(null, errors);

    doOnSuccess(accountResponse).when(service).dispatch(any(Action.class), any(AsyncCallback.class));

    accountCreatorPresenter.create(account);

    verify(service).dispatch(any(CreateAccountAction.class), any(GotResponse.class));

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
