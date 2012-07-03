package com.clouway.exreport.client.accountregistration;

import com.clouway.exreport.client.accountregistration.view.AccountRegistrationView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.AccountValidationErrorMessages;
import com.clouway.exreport.shared.actions.RegisterAccountAction;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.reponses.RegisterAccountResponse;
import com.google.gwt.regexp.shared.RegExp;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountRegistrationPresenterImpl implements AccountRegistrationPresenter {

  private final ActionDispatcherServiceAsync service;

  private final EventBus handlers;

  private final AccountRegistrationView view;

  private final AccountValidationErrorMessages errorMessages;

  @Inject
  public AccountRegistrationPresenterImpl(ActionDispatcherServiceAsync service, EventBus handlers, AccountRegistrationView view, AccountValidationErrorMessages errorMessages) {
    this.service = service;
    this.handlers = handlers;
    this.view = view;
    this.errorMessages = errorMessages;
  }

  public void register(Account account) {
    String validationErrors = "";
    validationErrors = validateAccount(account);
    if ("".equals(validationErrors)) {
      service.dispatch(new RegisterAccountAction<RegisterAccountResponse>(account), new GotResponse<RegisterAccountResponse>() {
        @Override
        public void gotResponse(RegisterAccountResponse result) {
          if (result.getErrors().size() > 0) {
            view.showMessages(result.getErrors());
          } else {
            handlers.fireEvent(new AccountRegisteredEvent(result.getAccount()));
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

