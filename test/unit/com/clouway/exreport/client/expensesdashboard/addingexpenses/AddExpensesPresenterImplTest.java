package com.clouway.exreport.client.expensesdashboard.addingexpenses;

import com.clouway.exreport.client.expensesdashboard.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.shared.Expense;
import com.google.gwt.event.shared.HasHandlers;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.clouway.exreport.client.expensesdashboard.TestingAsyncCallbacksHelper.doOnFailure;
import static com.clouway.exreport.client.expensesdashboard.TestingAsyncCallbacksHelper.doOnSuccess;
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

  @Mock
  private AddingExpensesServiceAsync addingExpensesServiceAsync;

  @Mock
  HasHandlers hasHandlers;

  private AddExpensesPresenterImpl addExpensesPresenterImpl;

  @Mock
  private AddExpensesView view;


  @Before
  public void setUp() {
    initMocks(this);
    addExpensesPresenterImpl = new AddExpensesPresenterImpl(addingExpensesServiceAsync, hasHandlers, view);
  }

  @Test
  public void shouldAddExpenseAndFireExpenseAddedEvent() throws ParseException {


    Expense expense = new Expense("Food", 12d);

    Date date = dateTimeFormat.parse("2012-06-02");

    doOnSuccess(expense).when(addingExpensesServiceAsync).addExpense(eq(expense), eq(date), any(AsyncCallback.class));

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(addingExpensesServiceAsync).addExpense(eq(expense), eq(date), any(AsyncCallback.class));

    verify(hasHandlers).fireEvent(any(ExpenseAddedEvent.class));

  }

  @Test
  public void anExpenseIsAddedWhenDateIsCurrentDate() {

    Expense expense = new Expense("Food", 12d);

    Date date = new Date();

    doOnSuccess(expense).when(addingExpensesServiceAsync).addExpense(eq(expense), eq(date), any(AsyncCallback.class));

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(addingExpensesServiceAsync).addExpense(eq(expense), eq(date), any(AsyncCallback.class));

    verify(hasHandlers).fireEvent(any(ExpenseAddedEvent.class));

  }

  //TODO:When testing date in the future make sure the date is always in the future !!;
  @Test
  public void anExpenseCanNotBeAddedInFutureDate() throws ParseException {

    Expense expense = new Expense("House Rent", 12d);

    Date futureDate = dateTimeFormat.parse("2013-03-12");

    addExpensesPresenterImpl.addExpense(expense, futureDate);

    verify(addingExpensesServiceAsync, never()).addExpense(eq(expense), eq(futureDate), any(AsyncCallback.class));

    verify(hasHandlers, never()).fireEvent(any(ExpenseAddedEvent.class));

    verify(view).notifyExpenseCanNotBeAddedInFutureDate();

  }

  @Test
  public void expensePriceCanNotBeNegative() {

    Expense expense = new Expense("HousPrice", -12);

    Date date = new Date();

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(addingExpensesServiceAsync, never()).addExpense(eq(expense), eq(date), any(AsyncCallback.class));

    verify(hasHandlers, never()).fireEvent(any(ExpenseAddedEvent.class));

    verify(view).notifyNegativeExpensePriceValue();

  }

  //TODO:i think that the Expense should encapsulate the date, but we will see ... !

  @Test
  public void userIsNotifiedOfConnectionError() {

    Expense expense = new Expense("Computer fix", 200);

    Date date = new Date();

    doOnFailure(new Throwable()).when(addingExpensesServiceAsync).addExpense(eq(expense), eq(date), any(AsyncCallback.class));

    addExpensesPresenterImpl.addExpense(expense, date);

    verify(view).notifyUserOfConnectionError();

  }


}
