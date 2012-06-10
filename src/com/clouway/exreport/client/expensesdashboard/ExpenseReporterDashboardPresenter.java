package com.clouway.exreport.client.expensesdashboard;

import com.clouway.exreport.client.expensesdashboard.view.ExpenseReporterDashBoardView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.clouway.exreport.shared.Expense;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseReporterDashboardPresenter extends AbstractActivity {

  private final ExpenseReporterDashBoardView reporterDashBoardView;
  private final ExpenseReporterServiceAsync reporterAsync;

  public ExpenseReporterDashboardPresenter(ExpenseReporterDashBoardView reporterDashBoardView, ExpenseReporterServiceAsync reporterAsync) {
    this.reporterDashBoardView = reporterDashBoardView;
    this.reporterAsync = reporterAsync;
  }


  public void showExpensesFor(Date date) {

    Date todaysDate = new Date();
    if (todaysDate.before(date)) {
      reporterDashBoardView.notifyUserOfFutureDate();
    } else {
      reporterAsync.getExpensesFor(date, new AsyncCallback<List<Expense>>() {
        @Override
        public void onFailure(Throwable caught) {
        }

        @Override
        public void onSuccess(List<Expense> result) {
          reporterDashBoardView.renderTodaysExpense(result);
        }
      });
    }


  }

  public void showExpensesBetween(Date firstDate, Date secondDate) {


    if (firstDate.before(secondDate) || firstDate.equals(secondDate)) {
      reporterAsync.getExpensesBetween(firstDate, secondDate, new AsyncCallback<List<Expense>>() {
        @Override
        public void onFailure(Throwable caught) {
          reporterDashBoardView.showConnectionErrorMessage();
        }

        @Override
        public void onSuccess(List<Expense> result) {
          reporterDashBoardView.renderTodaysExpense(result);
        }
      });
    } else {
      reporterDashBoardView.notifyUserOfDateDiscrepancy();
    }

  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(reporterDashBoardView.asWidget());
  }


}
