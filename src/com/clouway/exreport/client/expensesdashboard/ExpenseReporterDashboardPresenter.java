package com.clouway.exreport.client.expensesdashboard;

import com.clouway.exreport.client.expensesdashboard.view.ExpenseReporterDashBoardView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.AcceptsOneWidget;

import java.util.ArrayList;
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

    public void getAllExpensesYears() {

        reporterAsync.getYearsOfExpenses(new AsyncCallback<ArrayList<Year>>() {
            @Override
            public void onFailure(Throwable caught) {
                reporterDashBoardView.showConnectionErrorMessage();
            }

            @Override
            public void onSuccess(ArrayList<Year> result) {
                reporterDashBoardView.showExpensesYears(result);
            }
        });
    }


    public void getMonthsOf(int year) {

        reporterAsync.getMonthOf(year, new AsyncCallback<ArrayList<Month>>() {
            @Override
            public void onFailure(Throwable caught) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onSuccess(ArrayList<Month> result) {
                reporterDashBoardView.showMonthsOfExpenses(result);
            }
        });
    }

    public void getAllExpensesDays(int year, int month) {

        reporterAsync.getDaysOf(year, month, new AsyncCallback<ArrayList<Day>>() {
            @Override
            public void onFailure(Throwable caught) {
                //To change body of implemented methods use File | Settings | File Templates.
            }

            @Override
            public void onSuccess(ArrayList<Day> result) {
                reporterDashBoardView.showDaysExpenses(result);
            }
        });

    }
}
