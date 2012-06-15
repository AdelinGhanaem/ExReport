package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Reponses.AddExpenseResponse;
import com.evo.gad.shared.Action;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnFailure;
import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */

public class AddExpensesPresenterImplTest {

  private SimpleDateFormat dateTimeFormat = new SimpleDateFormat("yy-MM-DD");

  //  private AddingExpensesServiceAsync addingExpensesServiceAsync;
  @Mock
  ActionDispatcherServiceAsync actionDispatcherServiceAsync;

  @Mock
  HasHandlers hasHandlers;

  private AddExpensesPresenterImpl addExpensesPresenterImpl;

  @Mock
  private AddExpensesView view;


  @Before
  public void setUp() {
    initMocks(this);
    addExpensesPresenterImpl = new AddExpensesPresenterImpl(actionDispatcherServiceAsync, hasHandlers, view);
  }

  @Test
  public void shouldAddExpenseAndFireExpenseAddedEvent() throws ParseException {

    Expense expense = new Expense("Food", 12d);

    AddExpenseResponse expenseResponse = new AddExpenseResponse(expense);

    Date date = dateTimeFormat.parse("2012-06-02");

    doOnSuccess(expenseResponse).when(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(hasHandlers).fireEvent(any(ExpenseAddedEvent.class));

  }

  @Test
  public void anExpenseIsAddedWhenDateIsCurrentDate() {

    Expense expense = new Expense("Food", 12d);

    AddExpenseResponse expenseResponse = new AddExpenseResponse(expense);

    Date date = new Date();

    doOnSuccess(expenseResponse).when(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(hasHandlers).fireEvent(any(ExpenseAddedEvent.class));

  }
//
//  //TODO:When testing date in the future make sure the date is always in the future !!;
  @Test
  public void anExpenseCanNotBeAddedInFutureDate() throws ParseException {

    Expense expense = new Expense("House Rent", 12d);

    Date futureDate = dateTimeFormat.parse("2013-03-12");

    addExpensesPresenterImpl.addExpense(expense, futureDate);

    verify(actionDispatcherServiceAsync,never()).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(hasHandlers, never()).fireEvent(any(ExpenseAddedEvent.class));

    verify(view).notifyExpenseCanNotBeAddedInFutureDate();

  }




//
  @Test
  public void expensePriceCanNotBeNegative() {

    Expense expense = new Expense("HousPrice", -12);

    Date date = new Date();

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(actionDispatcherServiceAsync,never()).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(hasHandlers, never()).fireEvent(any(ExpenseAddedEvent.class));

    verify(view).notifyNegativeExpensePriceValue();

  }

  //TODO:i think that the Expense should encapsulate the date, but we will see ... !

  @Test
  public void userIsNotifiedOfConnectionError() {

    Expense expense = new Expense("Computer fix", 200);

    Date date = new Date();

    doOnFailure(new Throwable()).when(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(view).notifyUserOfConnectionError();

  }


}
