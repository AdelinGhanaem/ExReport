package com.clouway.exreport.client.expensesreporting.addingexpenses;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.addingexpenses.view.AddExpensesView;
import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.client.security.SecurityActionFactory;
import com.clouway.exreport.shared.actions.AddExpenseAction;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.AddExpenseResponse;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.google.web.bindery.event.shared.SimpleEventBus;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnFailure;
import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
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
  EventBus hasHandlers;

  private AddExpensesPresenterImpl addExpensesPresenterImpl;

  @Mock
  private AddExpensesView view;

  @Mock
  SecurityActionFactory factory;
  Token token;

  @Before
  public void setUp() {

    initMocks(this);

    hasHandlers = spy(new SimpleEventBus());

    addExpensesPresenterImpl = new AddExpensesPresenterImpl(actionDispatcherServiceAsync, hasHandlers, view, factory);
  }

//  @Test
//  public void shouldAddExpenseAndFireExpenseAddedEventOnSuccess() throws ParseException {
//
//    Date date = dateTimeFormat.parse("2012-06-02");
//
//    Expense expense = new Expense("Food", 12d, date);
//
//    AddExpenseResponse expenseResponse = new AddExpenseResponse(expense);
//
//    doOnSuccess(expenseResponse).when(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));
//
//    addExpensesPresenterImpl.addExpense(expense);
//
//    verify(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));
//
//    verify(hasHandlers).fireEvent(any(ExpenseAddedEvent.class));
//
//  }

  @Test
  public void anExpenseIsAddedWhenDateIsCurrentDate() {

    Date date = new Date();

    Expense expense = new Expense("Food", 12d, date);

    AddExpenseResponse expenseResponse = new AddExpenseResponse(expense);

    SecurityResponse<AddExpenseResponse> securityResponse = new SecurityResponse<AddExpenseResponse>(expenseResponse, token);

    AddExpenseAction<AddExpenseResponse> action = new AddExpenseAction<AddExpenseResponse>();

    SecurityAction<AddExpenseAction<AddExpenseResponse>> securityAction = new SecurityAction<AddExpenseAction<AddExpenseResponse>>(action, token);

    when(factory.createSecurityAction(isA(AddExpenseAction.class))).thenReturn(securityAction);

    doOnSuccess(securityResponse).when(actionDispatcherServiceAsync).dispatch(any(SecurityAction.class), any(GotResponse.class));

    addExpensesPresenterImpl.addExpense(expense);

    verify(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(hasHandlers).fireEvent(any(ExpenseAddedEvent.class));

  }

//  //
////  //TODO:When testing date in the future make sure the date is always in the future !!;
//  @Test
//  public void anExpenseCanNotBeAddedInFutureDate() throws ParseException {
//
//    Date futureDate = dateTimeFormat.parse("2013-03-12");
//
//    Expense expense = new Expense("House Rent", 12d, futureDate);
//
//    addExpensesPresenterImpl.addExpense(expense);
//
//    verify(actionDispatcherServiceAsync, never()).dispatch(any(Action.class), any(AsyncCallback.class));
//
//    verify(hasHandlers, never()).fireEvent(any(ExpenseAddedEvent.class));
//
//    verify(view).notifyExpenseCanNotBeAddedInFutureDate();
//
//  }


  //


  @Test
  public void expensePriceCanNotBeNegative() {

    Date date = new Date();

    Expense expense = new Expense("HousPrice", -12, date);

    addExpensesPresenterImpl.addExpense(expense);

    verify(actionDispatcherServiceAsync, never()).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(hasHandlers, never()).fireEvent(any(ExpenseAddedEvent.class));

    verify(view).notifyNegativeExpensePriceValue();

  }

  //TODO:i think that the Expense should encapsulate the date, but we will see ... !

  @Test
  public void userIsNotifiedOfConnectionError() {

    Date date = new Date();

    Expense expense = new Expense("Computer fix", 200, date);

    doOnFailure(new Throwable()).when(actionDispatcherServiceAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    addExpensesPresenterImpl.addExpense(expense);

    verify(view).notifyUserOfConnectionError();

  }


}
