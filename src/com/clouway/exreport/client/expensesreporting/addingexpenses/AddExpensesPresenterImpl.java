package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.accountcreation.GotResponse;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.shared.Expense;
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

  public void addExpense(Expense expense, Date date) {

    Date currentDate = new Date();
    if (date.before(currentDate) || date.equals(currentDate)) {
      //here we are making expense to tell us whether its price is valid ot not, but we don't ask it! according to "tell don't ask"
      if (expense.isPriceValid()) {

        AddExpenseAction<AddExpenseResponse> addExpenseAction = new AddExpenseAction<AddExpenseResponse>(expense, date);

        addingExpensesServiceAsync.dispatch(addExpenseAction, new GotResponse<AddExpenseResponse>() {
          @Override
          public void gotResponse(AddExpenseResponse result) {
            hasHandlers.fireEvent(new ExpenseAddedEvent(result.getExpense()));
          }

          @Override
          public void onFailure(Throwable caught) {
            view.notifyUserOfConnectionError();
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
