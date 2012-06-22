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

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpensesPresenterImpl implements AddExpensesPresenter {

  private final ActionDispatcherServiceAsync addingExpensesServiceAsync;

  private final EventBus hasHandlers;

  private final AddExpensesView view;
  private final SecurityActionFactory factory;

  @Inject
  public AddExpensesPresenterImpl(ActionDispatcherServiceAsync addingExpensesServiceAsync, EventBus hasHandlers, AddExpensesView view, SecurityActionFactory factory) {
    this.addingExpensesServiceAsync = addingExpensesServiceAsync;

    this.hasHandlers = hasHandlers;

    this.view = view;
    this.factory = factory;
  }

  public void addExpense(Expense expense) {

    Date currentDate = new Date();

    //here we are making expense to tell us whether its price is valid ot not, but we don't ask it! according to "tell don't ask"
    if (expense.isPriceValid()) {

      AddExpenseAction<AddExpenseResponse> addExpenseAction = new AddExpenseAction<AddExpenseResponse>(expense);

      SecurityAction<AddExpenseAction<AddExpenseResponse>> action = factory.createSecurityAction(addExpenseAction);

      addingExpensesServiceAsync.dispatch(action, new GotResponse<SecurityResponse<AddExpenseResponse>>() {
        @Override
        public void gotResponse(SecurityResponse<AddExpenseResponse> result) {
          hasHandlers.fireEvent(new ExpenseAddedEvent(result.getResponse().getExpense()));
        }
        @Override
        public void onFailure(Throwable caught) {
          view.notifyUserOfConnectionError();
        }
      });
    } else {
      view.notifyNegativeExpensePriceValue();
    }
  }
}
