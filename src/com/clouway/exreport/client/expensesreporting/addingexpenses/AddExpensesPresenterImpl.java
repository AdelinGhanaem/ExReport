package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.client.security.SecurityActionFactory;
import com.clouway.exreport.shared.actions.AddExpenseAction;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.reponses.AddExpenseResponse;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpensesPresenterImpl implements AddExpensesPresenter {

  private final ActionDispatcherServiceAsync service;

  private final EventBus hasHandlers;

  private final AddExpensesView view;
  private final SecurityActionFactory factory;

  @Inject
  public AddExpensesPresenterImpl(ActionDispatcherServiceAsync service, EventBus hasHandlers, AddExpensesView view, SecurityActionFactory factory) {
    this.service = service;
    this.hasHandlers = hasHandlers;
    this.view = view;
    this.factory = factory;
  }

  public void addExpense(Expense expense) {

    view.disableAddButton();

    if (expense.isPriceValid()) {

      AddExpenseAction<AddExpenseResponse> addExpenseAction = new AddExpenseAction<AddExpenseResponse>(expense);

      SecurityAction<AddExpenseResponse> action = factory.createSecurityAction(addExpenseAction);

      service.dispatch(action, new GotResponse<SecurityResponse>() {
        @Override
        public void gotResponse(SecurityResponse result) {
          AddExpenseResponse response = (AddExpenseResponse) result.getResponse();
          hasHandlers.fireEvent(new ExpenseAddedEvent((response.getExpense())));
          view.enableAddButton();
        }

        @Override
        public void onFailure(Throwable caught) {
          view.notifyUserOfConnectionError();
        }
      });
    } else {

      view.notifyNegativeExpensePriceValue();

      view.enableAddButton();

    }
  }
}
