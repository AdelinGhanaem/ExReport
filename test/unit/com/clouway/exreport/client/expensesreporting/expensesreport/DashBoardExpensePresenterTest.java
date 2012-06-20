package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.authentication.SecurityAction;
import com.clouway.exreport.client.authentication.SecurityActionFactory;
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
    doOnSuccess(response).when(reporterAsync).dispatch(any(SecurityAction.class), any(GotResponse.class));
    when(factory.createSecurity(new FetchExpensesAction<FetchExpensesResponse>())).
            thenReturn(new SecurityAction<FetchExpensesAction<FetchExpensesResponse>>(new FetchExpensesAction<FetchExpensesResponse>(firstDate, secondDate), new Token()));
    reporterPresenter.fetchExpensesBetween(firstDate, secondDate);

    verify(reporterAsync).dispatch(any(Action.class), any(GotResponse.class));

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

    doOnFailure(new Throwable()).when(reporterAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    reporterPresenter.fetchExpensesBetween(firstDate, endDate);

    verify(reporterView).showConnectionErrorMessage();

  }

  //
//
  @Test
  public void returnsAllYearOfExpensesAndListThemInTheView() {

    ArrayList<Year> yearList = new ArrayList<Year>();
    FetchYearsAction<FetchYearsResponse> fetchYearsAction = new FetchYearsAction<FetchYearsResponse>();
    SecurityAction<FetchYearsAction<FetchYearsResponse>> action =
            new SecurityAction<FetchYearsAction<FetchYearsResponse>>(new FetchYearsAction<FetchYearsResponse>(), token);
    when(factory.createSecurity(fetchYearsAction)).thenReturn(action);
    doOnSuccess(new SecurityResponse<FetchYearsResponse>(new FetchYearsResponse(yearList), token)).when(reporterAsync).dispatch(any(FetchYearsAction.class), any(AsyncCallback.class));

    reporterPresenter.getAllExpensesYears();

    verify(reporterAsync).dispatch(any(SecurityAction.class), any(AsyncCallback.class));

    verify(reporterView).showExpensesYears(yearList);

  }

  //
  @Test
  public void notifiesUserWhenErrorOccursWhileRequiringYearsOfExpenses() {

    doOnFailure(new Throwable()).when(reporterAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    reporterPresenter.getAllExpensesYears();

    verify(reporterAsync).dispatch(any(Action.class), any(AsyncCallback.class));

    verify(reporterView).showConnectionErrorMessage();
  }


  //
//
  @Test
  public void returnsAllExpensesMonthsOfTheYear() {
    int year = 2012;

    ArrayList<Month> months = new ArrayList<Month>();

    doOnSuccess(new FetchMonthsResponse(months)).when(reporterAsync).dispatch(any(FetchMonthsAction.class), any(GotResponse.class));

    reporterPresenter.getMonthsOf(year);

    verify(reporterAsync).dispatch(any(FetchMonthsAction.class), any(GotResponse.class));

    verify(reporterView).showMonthsOfExpenses(months);

  }


  //
  @Test
  public void returnsAllExpensesDays() {
    ArrayList<Day> days = new ArrayList<Day>();

    doOnSuccess(new FetchDaysResponse(days)).when(reporterAsync).dispatch(any(FetchDaysAction.class), any(GotResponse.class));

    reporterPresenter.getAllExpensesDays(2012, 6);

    verify(reporterAsync).dispatch(any(FetchDaysAction.class), any(GotResponse.class));

    verify(reporterView).showDaysExpenses(days);
  }


}
  