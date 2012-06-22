package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.client.security.SecurityActionFactory;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.shared.actions.FetchDaysAction;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchMonthsAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.entites.Token;
import com.clouway.exreport.shared.reponses.FetchDaysResponse;
import com.clouway.exreport.shared.reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.clouway.exreport.shared.entites.Year;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.evo.gad.shared.Action;
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
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DashBoardExpensePresenterTest {


  @Mock
  ExpenseReporterView reporterView;

  @Mock
  ActionDispatcherServiceAsync reporterAsync;

  @Mock
  SecurityActionFactory factory;

  private ExpenseReporterPresenterImpl reporterPresenter;

  private Token token;

  ArrayList<Expense> expenses = new ArrayList<Expense>();


  //TODO:Don't forget to change to MessagesContainer;
  //TODO:Try to figure out how to capture the ArrayList result... may be something with capture(... ) !

  @Before
  public void setUp() {

    initMocks(this);


    reporterPresenter = new ExpenseReporterPresenterImpl(reporterView, reporterAsync, factory);

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

    doOnSuccess(response).when(reporterAsync).dispatchSecurityAction(eq(securityAction), any(GotResponse.class));

    reporterPresenter.fetchExpensesBetween(firstDate, secondDate);

    verify(reporterAsync).dispatchSecurityAction(eq(securityAction), any(GotResponse.class));

    verify(reporterView).updateExpenses(expenses);

  }


  //  @Test
//  public void notifiesUserWhenDataIsInFuture() throws ParseException {
//
//    Date futureDate = dateFormat.parse("2013-01-01");
//
//    reporterPresenter.showExpensesFor(futureDate);
//
//    verify(reporterAsync, never()).getExpensesFor(eq(futureDate), any(AsyncCallback.class));
//
//    verify(reporterView).notifyUserOfFutureDate();
//
//  }
//
//
  @Test
  public void notifiesUserWhenStartDateIsAfterEndDate() throws ParseException {

    Date startDate = dateFormat.parse("2012-06-01");

    Date endDate = dateFormat.parse("2010-06-01");

    reporterPresenter.fetchExpensesBetween(startDate, endDate);

    verify(reporterAsync, never()).dispatch(any(Action.class), any(GotResponse.class));

    verify(reporterView).notifyUserOfDateDiscrepancy();

  }

  //
//
  @Test
  public void notifiesUserWhenConnectionErrorOccurs() {

    Date firstDate = new Date();

    Date endDate = new Date();

    doOnFailure(new Throwable()).when(reporterAsync).dispatchSecurityAction(any(SecurityAction.class), any(GotResponse.class));

    reporterPresenter.fetchExpensesBetween(firstDate, endDate);

    verify(reporterView).showConnectionErrorMessage();

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

    doOnSuccess(securityResponse).when(reporterAsync).dispatchSecurityAction(eq(securityAction), any(GotResponse.class));

    reporterPresenter.getAllExpensesYears();

    verify(reporterAsync).dispatchSecurityAction(eq(securityAction), any(AsyncCallback.class));

    verify(reporterView).showExpensesYears(yearList);

  }

  @Test
  public void notifiesUserWhenErrorOccursWhileRequiringYearsOfExpenses() {

    doOnFailure(new Throwable()).when(reporterAsync).dispatchSecurityAction(any(SecurityAction.class), any(AsyncCallback.class));

    reporterPresenter.getAllExpensesYears();

    verify(reporterAsync).dispatchSecurityAction(any(SecurityAction.class), any(AsyncCallback.class));

    verify(reporterView).showConnectionErrorMessage();
  }

  @Test
  public void returnsAllExpensesMonthsOfTheYear() {

    int year = 2012;

    ArrayList<Month> months = new ArrayList<Month>();

    FetchMonthsResponse fetchMonthsResponse = new FetchMonthsResponse(months);

    SecurityResponse<FetchMonthsResponse> securityResponse = new SecurityResponse<FetchMonthsResponse>(fetchMonthsResponse, token);

    FetchMonthsAction<FetchMonthsResponse> monthsAction = new FetchMonthsAction<FetchMonthsResponse>(year);

    SecurityAction<FetchMonthsAction<FetchMonthsResponse>> securityAction = new SecurityAction<FetchMonthsAction<FetchMonthsResponse>>(monthsAction, token);

    when(factory.createSecurityAction(isA(FetchMonthsAction.class))).thenReturn(securityAction);

    doOnSuccess(securityResponse).when(reporterAsync).dispatchSecurityAction(isA(SecurityAction.class), isA(GotResponse.class));

    reporterPresenter.getMonthsOf(year);

    verify(reporterAsync).dispatchSecurityAction(isA(SecurityAction.class), isA(GotResponse.class));

    verify(reporterView).showMonthsOfExpenses(months);

  }

  @Test
  public void returnsAllExpensesDays() {
    int year = 2012;

    int month = 6;

    ArrayList<Day> days = new ArrayList<Day>();

    FetchDaysResponse fetchDaysResponse = new FetchDaysResponse(days);

    FetchDaysAction<FetchDaysResponse> action = new FetchDaysAction<FetchDaysResponse>(year, month);

    SecurityResponse<FetchDaysResponse> response = new SecurityResponse<FetchDaysResponse>(fetchDaysResponse, token);

    SecurityAction<FetchDaysAction<FetchDaysResponse>> securityAction = new SecurityAction<FetchDaysAction<FetchDaysResponse>>(action, token);

    when(factory.createSecurityAction(isA(FetchDaysAction.class))).thenReturn(securityAction);

    doOnSuccess(response).when(reporterAsync).dispatchSecurityAction(eq(securityAction), isA(GotResponse.class));

    reporterPresenter.getAllExpensesDays(year, month);

    verify(reporterAsync).dispatchSecurityAction(eq(securityAction), isA(GotResponse.class));

    verify(reporterView).showDaysExpenses(days);
  }
}
  