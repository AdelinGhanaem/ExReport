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

import java.text.SimpleDateFormat;
import java.util.Calendar;
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
//  public void addsExpenseAndFireExpenseAddedEventOnSuccess() throws ParseException {
//
//    Date date = dateTimeFormat.parse("2012-06-02");
//
//    Expense expense = new Expense("Food", 12d, date);
//
//    AddExpenseResponse addExpenseResponse = new AddExpenseResponse(expense);
//
//    SecurityAction<AddExpenseAction<AddExpenseResponse>> securityAction = new SecurityAction<AddExpenseAction<AddExpenseResponse>>();
//
//    SecurityResponse<AddExpenseResponse> securityResponse = new SecurityResponse<AddExpenseResponse>(addExpenseResponse);
//
//    when(factory.createSecurityAction(isA(AddExpenseAction.class))).thenReturn(securityAction);
//
//    doOnSuccess(securityResponse).when(actionDispatcherServiceAsync).dispatch(isA(AddExpenseAction.class), isA(GotResponse.class));
//
//    addExpensesPresenterImpl.addExpense(expense);
//
//    verify(actionDispatcherServiceAsync).dispatch(isA(SecurityAction.class), any(GotResponse.class));
//
//    verify(hasHandlers).fireEvent(isA(ExpenseAddedEvent.class));
//
//  }

  @Test
  public void addsExpenseAndFireExpenseAddedEventOnSuccess() {

    Date date = new Date();

    Expense expense = new Expense("Food", 12d, date);

    AddExpenseResponse expenseResponse = new AddExpenseResponse(expense);

    SecurityResponse<AddExpenseResponse> securityResponse = new SecurityResponse<AddExpenseResponse>(expenseResponse, token);

    AddExpenseAction<AddExpenseResponse> action = new AddExpenseAction<AddExpenseResponse>();

    SecurityAction<AddExpenseAction<AddExpenseResponse>> securityAction = new SecurityAction<AddExpenseAction<AddExpenseResponse>>(action, token);

    when(factory.createSecurityAction(isA(AddExpenseAction.class))).thenReturn(securityAction);

    doOnSuccess(securityResponse).when(actionDispatcherServiceAsync).dispatchSecurityAction(isA(SecurityAction.class), isA(GotResponse.class));

    addExpensesPresenterImpl.addExpense(expense);

    verify(actionDispatcherServiceAsync).dispatchSecurityAction(isA(SecurityAction.class), isA(GotResponse.class));

    verify(hasHandlers).fireEvent(any(ExpenseAddedEvent.class));

  }

  @Test
  public void disablesAddButtonOnAddExpense() {
    addExpensesPresenterImpl.addExpense(new Expense());
    verify(view).disableAddButton();
  }

  //TODO:When testing date in the future make sure the date is always in the future !!;

  @Test
  public void expensePriceCanNotBeNegative() {

    Date date = new Date();

    Expense expense = new Expense("HousPrice", -12, date);

    addExpensesPresenterImpl.addExpense(expense);

    verify(actionDispatcherServiceAsync, never()).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(hasHandlers, never()).fireEvent(any(ExpenseAddedEvent.class));

    verify(view).notifyNegativeExpensePriceValue();

  }

  @Test
  public void userIsNotifiedOfConnectionError() {

    Date date = new Date();

    Expense expense = new Expense("Computer fix", 200, date);

    doOnFailure(new Throwable()).when(actionDispatcherServiceAsync).dispatchSecurityAction(any(SecurityAction.class), isA(GotResponse.class));

    addExpensesPresenterImpl.addExpense(expense);

    verify(view).notifyUserOfConnectionError();
  }


  @Test
  public void shouldUpdatedTableOnExpensesAdded() {

    Date date = new Date();

    Expense expense = new Expense("computer mouse", 12d, date);

  }
}
