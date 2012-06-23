package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.client.accountcreation.view.AccountCreatorView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.actions.CreateAccountAction;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.reponses.CreateAccountResponse;
import com.google.gwt.regexp.shared.RegExp;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorPresenterImpl implements AccountCreatorPresenter {

  private final ActionDispatcherServiceAsync service;

  private final EventBus handlers;

  private final AccountCreatorView view;

  private final AccountValidationErrorMessages errorMessages;

  @Inject
  public AccountCreatorPresenterImpl(ActionDispatcherServiceAsync service, EventBus handlers, AccountCreatorView view, AccountValidationErrorMessages errorMessages) {
    this.service = service;
    this.handlers = handlers;
    this.view = view;
    this.errorMessages = errorMessages;
  }

  public void create(Account account) {
    String validationErrors = "";
    if ("".equals(validationErrors)) {
      service.dispatch(new CreateAccountAction<CreateAccountResponse>(account), new GotResponse<CreateAccountResponse>() {
        @Override
        public void gotResponse(CreateAccountResponse result) {
          if (result.getErrors().size() > 0) {
            view.showMessages(result.getErrors());
          } else {
            handlers.fireEvent(new AccountCreatedEvent(result.getAccount()));
          }
        }
      });
    } else {
      view.showMessage(validationErrors);
    }
  }


  private boolean isEmailValid(String email) {
    RegExp regExp = RegExp.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$");
    return regExp.test(email);
  }

  private String validateAccount(Account account) {
    StringBuilder stringBuilder = new StringBuilder();
    if (account.getEmail() != null && account.getPassword() != null) {
      if (!isEmailValid(account.getEmail())) {
        stringBuilder.append(errorMessages.invalidEmailForm());
      }
      if (passwordIsShort(account.getPassword())) {
        stringBuilder.append(errorMessages.shortPassword());
      }
    } else {
      stringBuilder.append(errorMessages.emptyEmailOrPassword());
    }
    return stringBuilder.toString();
  }

  private boolean passwordIsShort(String password) {
    return password.length() < 6;
  }

}

