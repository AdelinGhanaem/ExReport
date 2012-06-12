package com.clouway.exreport.client.expensesdashboard.expensesreport;

import com.clouway.exreport.client.expensesdashboard.expensesreport.view.ExpenseReporterDashBoardView;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.user.client.rpc.AsyncCallback;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
public class DashBoardExpensePresenterTest {


  @Mock
  ExpenseReporterDashBoardView reporterDashBoardView;

  @Mock
  ExpenseReporterServiceAsync reporterAsync;

  private ExpenseReporterPresenter reporterPresenter;

  //TODO:Don't forget to change to MessagesContainer;
  @Before
  public void setUp() {
    initMocks(this);
    reporterPresenter = new ExpenseReporterPresenter(reporterDashBoardView, reporterAsync);
  }

  private DateFormat dateFormat = new SimpleDateFormat("yy-MM-dd");


  @Test
  public void returnsExpensesOfTheGivenDateAndUpdatesView() throws ParseException {

    ArrayList<Expense> expenses = new ArrayList<Expense>();

    Date date = dateFormat.parse("2012-06-01");

    doOnSuccess(expenses).when(reporterAsync).getExpensesFor(any(Date.class), any(AsyncCallback.class));

    reporterPresenter.showExpensesFor(date);

    verify(reporterAsync).getExpensesFor(eq(date), any(AsyncCallback.class));

    verify(reporterDashBoardView).updateExpenses(expenses);
  }


  @Test
  public void returnsExpensesBetweenTwoGivenDates() throws ParseException {
    ArrayList<Expense> expenses = new ArrayList<Expense>();

    Date firstDate = dateFormat.parse("2012-01-06");

    Date secondDate = dateFormat.parse("2012-06-01");

    doOnSuccess(expenses).when(reporterAsync).getExpensesBetween(eq(firstDate), eq(secondDate), any(AsyncCallback.class));

    reporterPresenter.fetchExpensesBetween(firstDate, secondDate);

    verify(reporterAsync).getExpensesBetween(eq(firstDate), eq(secondDate), any(AsyncCallback.class));

    verify(reporterDashBoardView).updateExpenses(expenses);
  }


  @Test
  public void notifiesUserWhenDataIsInFuture() throws ParseException {

    Date futureDate = dateFormat.parse("2013-01-01");

    reporterPresenter.showExpensesFor(futureDate);

    verify(reporterAsync, never()).getExpensesFor(eq(futureDate), any(AsyncCallback.class));

    verify(reporterDashBoardView).notifyUserOfFutureDate();

  }


  @Test
  public void notifiesUserWhenStartDateIsAfterEndDate() throws ParseException {

    Date startDate = dateFormat.parse("2012-06-01");

    Date endDate = dateFormat.parse("2010-06-01");

    reporterPresenter.fetchExpensesBetween(startDate, endDate);

    verify(reporterAsync, never()).getExpensesBetween(eq(startDate), eq(endDate), any(AsyncCallback.class));

    verify(reporterDashBoardView).notifyUserOfDateDiscrepancy();

  }


  @Test
  public void notifiesUserWhenConnectionErrorOccurs() {

    Date firstDate = new Date();

    Date endDate = new Date();

    doOnFailure(new Throwable()).when(reporterAsync).getExpensesBetween(eq(firstDate), eq(endDate), any(AsyncCallback.class));

    reporterPresenter.fetchExpensesBetween(firstDate, endDate);

    verify(reporterDashBoardView).showConnectionErrorMessage();

  }


  @Test
  public void returnsAllYearOfExpensesAndListThemInTheView() {

    ArrayList<Year> yearList = new ArrayList<Year>();

    doOnSuccess(yearList).when(reporterAsync).getYearsOfExpenses(any(AsyncCallback.class));

    reporterPresenter.getAllExpensesYears();

    verify(reporterAsync).getYearsOfExpenses(any(AsyncCallback.class));

    verify(reporterDashBoardView).showExpensesYears(yearList);

  }

  @Test
  public void notifiesUserWhenErrorOccursWhileRequiringYearsOfExpenses() {

    doOnFailure(new Throwable()).when(reporterAsync).getYearsOfExpenses(any(AsyncCallback.class));

    reporterPresenter.getAllExpensesYears();

    verify(reporterAsync).getYearsOfExpenses(any(AsyncCallback.class));

    verify(reporterDashBoardView).showConnectionErrorMessage();
  }


  @Test
  public void returnsAllExpensesMonthsOfTheYear() {
    int year = 2012;
    ArrayList<Month> months = new ArrayList<Month>();

    doOnSuccess(months).when(reporterAsync).getMonthOf(eq(year), any(AsyncCallback.class));

    reporterPresenter.getMonthsOf(year);

    verify(reporterAsync).getMonthOf(eq(year), any(AsyncCallback.class));

    verify(reporterDashBoardView).showMonthsOfExpenses(months);

  }

  @Test
  public void returnsAllExpensesDays() {

    ArrayList<Day> days = new ArrayList<Day>();

    doOnSuccess(days).when(reporterAsync).getDaysOf(eq(2012), eq(6), any(AsyncCallback.class));

    reporterPresenter.getAllExpensesDays(2012, 6);

    verify(reporterAsync).getDaysOf(eq(2012), eq(6), any(AsyncCallback.class));

    verify(reporterDashBoardView).showDaysExpenses(days);

  }


}
  