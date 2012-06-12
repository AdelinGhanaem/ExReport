package com.clouway.exreport.client.expensesdashboard.addexpenses;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpensesPresenter {

  private final AddingExpensesServiceAsync addingExpensesServiceAsync;
  private final HasHandlers hasHandlers;
  private final AddExpensesView view;

  public AddExpensesPresenter(AddingExpensesServiceAsync addingExpensesServiceAsync, HasHandlers hasHandlers, AddExpensesView view) {

    this.addingExpensesServiceAsync = addingExpensesServiceAsync;
    this.hasHandlers = hasHandlers;
    this.view = view;
  }

  public void addExpense(com.clouway.exreport.shared.Expense expense, Date date) {

    Date currentDate = new Date();
    if (date.before(currentDate) || date.equals(currentDate)) {
      //here we are making expense to tell us whether its price is valid ot not, but we don't ask it! according to "tell don't ask"
      if (expense.isPriceValid()) {
        addingExpensesServiceAsync.addExpense(expense, date, new AsyncCallback<Expense>() {
          @Override
          public void onFailure(Throwable caught) {

          }
          @Override
          public void onSuccess(Expense result) {
            hasHandlers.fireEvent(new ExpenseAddedEvent(result));
          }
        });
      } else {
        view.notifyNegativeExpensePriceValue();
      }
    } else {
      view.notifyExpenseCanNotBeAddedInFutureDate();
    }


  }
}
