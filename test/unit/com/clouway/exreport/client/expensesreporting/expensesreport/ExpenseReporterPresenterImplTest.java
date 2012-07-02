package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.client.security.SecurityActionFactory;
import com.clouway.exreport.shared.actions.FetchDaysAction;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.entites.Year;
import com.clouway.exreport.shared.reponses.FetchDaysResponse;
import com.clouway.exreport.shared.reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
import com.evo.gad.shared.Response;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnFailure;
import static com.clouway.exreport.client.expensesreporting.TestingAsyncCallbacksHelper.doOnSuccess;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Matchers.isA;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseReporterPresenterImplTest {


  @Mock
  ExpenseReporterView view;

  @Mock
  ActionDispatcherServiceAsync service;

  @Mock
  SecurityActionFactory factory;

  private ExpenseReporterPresenterImpl presenter;

  private Token token;

  ArrayList<Expense> expenses = new ArrayList<Expense>();


  //TODO:Don't forget to change to MessagesContainer;

  //TODO:Try to figure out how to capture the ArrayList result... may be something with capture(... ) !

  @Before
  public void setUp() {

    initMocks(this);


    presenter = new ExpenseReporterPresenterImpl(view, service, factory);

  }

  private DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");


  @Test
  public void returnsExpensesBetweenTwoGivenDates() throws ParseException {

    Date firstDate = dateFormat.parse("2012-01-06");

    Date secondDate = dateFormat.parse("2012-06-01");

    SecurityResponse<FetchExpensesResponse> response = new SecurityResponse<FetchExpensesResponse>(new FetchExpensesResponse(expenses), token);

    FetchExpensesAction<FetchExpensesResponse> fetchExpensesAction = new FetchExpensesAction<FetchExpensesResponse>(firstDate, secondDate);

    SecurityAction<FetchExpensesAction<FetchExpensesResponse>> securityAction = new SecurityAction<FetchExpensesAction<FetchExpensesResponse>>(fetchExpensesAction, new Token());

    when(factory.createSecurityAction(isA(FetchExpensesAction.class))).thenReturn(securityAction);

    doOnSuccess(response).when(service).dispatchSecurityAction(eq(securityAction), any(GotResponse.class));

    presenter.fetchExpensesBetween(firstDate, secondDate);

    verify(service).dispatchSecurityAction(eq(securityAction), any(GotResponse.class));

    verify(view).updateExpenses(expenses);

  }

  @Test
  public void notifiesUserWhenConnectionErrorOccurs() {

    Date firstDate = new Date();

    Date endDate = new Date();

    doOnFailure(new Throwable()).when(service).dispatchSecurityAction(any(SecurityAction.class), any(GotResponse.class));

    presenter.fetchExpensesBetween(firstDate, endDate);

    verify(view).showConnectionErrorMessage();

  }


  @Test
  public void returnsAllYearOfExpensesAndListThemInTheView() {

    ArrayList<Year> yearList = new ArrayList<Year>();

    FetchYearsAction<FetchYearsResponse> fetchYearsAction = new FetchYearsAction<FetchYearsResponse>();

    FetchYearsResponse yearsResponse = new FetchYearsResponse(yearList);

    SecurityAction<FetchYearsAction<FetchYearsResponse>> securityAction =
            new SecurityAction<FetchYearsAction<FetchYearsResponse>>(fetchYearsAction, token);

    SecurityResponse<FetchYearsResponse> securityResponse = new SecurityResponse<FetchYearsResponse>(yearsResponse, token);

    when(factory.createSecurityAction(isA(FetchYearsAction.class))).thenReturn(securityAction);

    doOnSuccess(securityResponse).when(service).dispatchSecurityAction(eq(securityAction), any(GotResponse.class));

    presenter.getAllExpensesYears();

    verify(service).dispatchSecurityAction(eq(securityAction), any(AsyncCallback.class));

    verify(view).showExpensesYears(yearList);

  }

  @Test
  public void notifiesUserWhenErrorOccursWhileRequiringYearsOfExpenses() {

    doOnFailure(new Throwable()).when(service).dispatchSecurityAction(any(SecurityAction.class), any(AsyncCallback.class));

    presenter.getAllExpensesYears();

    verify(service).dispatchSecurityAction(any(SecurityAction.class), any(AsyncCallback.class));

    verify(view).showConnectionErrorMessage();
  }

  @Test
  public void showTheReturnedExpensesSum() {
    ArrayList<Expense> returnedExpenses = new ArrayList<Expense>();
    for (int i = 0; i < 5; i++) {
      returnedExpenses.add(new Expense("name" + i, 2, new Date()));
    }
    FetchExpensesResponse response = new FetchExpensesResponse(returnedExpenses);

    SecurityResponse<FetchExpensesResponse> securityResponse = stubSecurityActionCreation(response,
            new FetchExpensesAction<FetchExpensesResponse>(new Date(), new Date()));

    doOnSuccess(securityResponse).when(service).dispatchSecurityAction(isA(SecurityAction.class), isA(GotResponse.class));

    presenter.fetchExpensesBetween(new Date(), new Date());

    verify(service).dispatchSecurityAction(isA(SecurityAction.class), isA(GotResponse.class));

    verify(view).showExpensesSum(10d);
  }

  private <R extends Response, A extends Action<R>, S extends SecurityAction<A>, P extends SecurityResponse<R>> P stubSecurityActionCreation(R response, A action) {
    SecurityResponse<R> securityResponse = new SecurityResponse<R>(response);
    SecurityAction<A> securityAction = new SecurityAction<A>(action, new Token());
    when(factory.createSecurityAction(any(Action.class))).thenReturn(securityAction);
    return (P) securityResponse;
  }

  @Test
  public void shouldShowAllMonthsOfAYear() {
    presenter.getMonthsOf(2012);
    verify(view).showMonthsOfExpenses(isA(ArrayList.class));
  }
}
  