package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.shared.actions.AddExpenseAction;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.reponses.AddExpenseResponse;
import com.google.gwt.event.shared.HasHandlers;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpensesPresenterImpl implements AddExpensesPresenter {

  private final ActionDispatcherServiceAsync addingExpensesServiceAsync;

  private final HasHandlers hasHandlers;

  private final AddExpensesView view;

  public AddExpensesPresenterImpl(ActionDispatcherServiceAsync addingExpensesServiceAsync, HasHandlers hasHandlers, AddExpensesView view) {

    this.addingExpensesServiceAsync = addingExpensesServiceAsync;
    this.hasHandlers = hasHandlers;
    this.view = view;
  }

  public void addExpense(Expense expense) {

    Date currentDate = new Date();

    //here we are making expense to tell us whether its price is valid ot not, but we don't ask it! according to "tell don't ask"
    if (expense.isPriceValid()) {
      AddExpenseAction<AddExpenseResponse> addExpenseAction = new AddExpenseAction<AddExpenseResponse>(expense);
      addingExpensesServiceAsync.dispatch(addExpenseAction, new GotResponse<AddExpenseResponse>() {
        @Override
        public void gotResponse(AddExpenseResponse result) {
          hasHandlers.fireEvent(new ExpenseAddedEvent(result.getExpense()));
        }

        @Override
        public void onFailure(Throwable caught) {
          view.notifyUserOfConnectionError();
//          Window.alert(caught.getMessage());
        }
      });
    } else {
      view.notifyNegativeExpensePriceValue();
    }
  }
}
