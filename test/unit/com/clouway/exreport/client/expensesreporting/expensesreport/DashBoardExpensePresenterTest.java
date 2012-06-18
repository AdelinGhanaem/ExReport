package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.shared.Actions.FetchDaysAction;
import com.clouway.exreport.shared.Actions.FetchExpensesAction;
import com.clouway.exreport.shared.Actions.FetchMonthsAction;
import com.clouway.exreport.shared.Actions.FetchYearsAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.Reponses.FetchDaysResponse;
import com.clouway.exreport.shared.Reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.Reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.Reponses.FetchYearsResponse;
import com.clouway.exreport.shared.entites.Year;
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
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DashBoardExpensePresenterTest {


  @Mock
  ExpenseReporterView reporterView;

  @Mock
  ActionDispatcherServiceAsync reporterAsync;

  private ExpenseReporterPresenter reporterPresenter;

  ArrayList<Expense> expenses = new ArrayList<Expense>();

  private FetchExpensesResponse fetchExpensesResponse = new FetchExpensesResponse(expenses);

  //TODO:Don't forget to change to MessagesContainer;
  //TODO:Try to figure out how to capture the ArrayList result... may be something with capture(... ) !  
  @Before
  public void setUp() {

    initMocks(this);

    reporterPresenter = new ExpenseReporterPresenter(reporterView, reporterAsync);

  }

  private DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");


  @Test
  public void returnsExpensesOfTheGivenDateAndUpdatesView() throws ParseException {

//    ArrayList<Expense> expensesreporting = new ArrayList<Expense>();
//
//    Date date = dateFormat.parse("2012-06-01");
//
//    doOnSuccess(expensesreporting).when(reporterAsync).getExpensesFor(any(Date.class), any(AsyncCallback.class));
//
////    reporterPresenter.showExpensesFor(date);
//
//    verify(reporterAsync).getExpensesFor(eq(date), any(AsyncCallback.class));
//
//    verify(reporterView).updateExpenses(expensesreporting);
  }


  @Test
  public void returnsExpensesBetweenTwoGivenDates() throws ParseException {

    Date firstDate = dateFormat.parse("2012-01-06");

    Date secondDate = dateFormat.parse("2012-06-01");

    doOnSuccess(fetchExpensesResponse).when(reporterAsync).dispatch(any(Action.class), any(GotResponse.class));

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

    doOnSuccess(new FetchYearsResponse(yearList)).when(reporterAsync).dispatch(any(FetchYearsAction.class), any(AsyncCallback.class));

    reporterPresenter.getAllExpensesYears();

    verify(reporterAsync).dispatch(any(FetchExpensesAction.class), any(AsyncCallback.class));

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
  