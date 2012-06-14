package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.client.accountcreation.view.AccountCreatorView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.shared.Account;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.regexp.shared.RegExp;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorPresenter {

  private final ActionDispatcherServiceAsync service;

  private final HasHandlers handlers;

  private final AccountCreatorView view;
  private final AccountValidationErrorMessages errorMessages;

  public AccountCreatorPresenter(ActionDispatcherServiceAsync service, HasHandlers handlers, AccountCreatorView view, AccountValidationErrorMessages errorMessages) {
    this.service = service;
    this.handlers = handlers;
    this.view = view;
    this.errorMessages = errorMessages;
  }

  public void create(Account account) {
    String validationErrors = validateAccount(account);
    if ("".equals(validationErrors)) {
      service.dispatch(new CreateAccountAction<AccountCreatedResponse>(account), new GotResponse<AccountCreatedResponse>() {
        @Override
        public void gotResponse(AccountCreatedResponse result) {
          handlers.fireEvent(new AccountCreatedEvent(result.getAccount()));
        }
      });
    } else {
      view.showMessage(validationErrors);
    }
  }


  private boolean isEmailValid(String email) {
    return RegExp.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$").test(email);
  }

  private String validateAccount(Account account) {
    StringBuilder stringBuilder = new StringBuilder();
    if (!isEmailValid(account.getEmail())) {
      stringBuilder.append(errorMessages.invalidEmailForm());
    }
    if (passwordIsShort(account.getPassword())) {
      stringBuilder.append(errorMessages.shortPassword());
    }
    return stringBuilder.toString();
  }

  private boolean passwordIsShort(String password) {
    return password.length() < 6;
  }

}

