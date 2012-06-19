package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.clouway.exreport.client.navigation.Presenter;
import com.clouway.exreport.shared.actions.FetchDaysAction;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchMonthsAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.reponses.FetchDaysResponse;
import com.clouway.exreport.shared.reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseReporterPresenter extends AbstractActivity implements Presenter {

  private final ExpenseReporterView view;

  private final ActionDispatcherServiceAsync reporterAsync;

  public ExpenseReporterPresenter(ExpenseReporterView view, ActionDispatcherServiceAsync reporterAsync) {

    this.view = view;

    this.reporterAsync = reporterAsync;
  }


//  public void showExpensesFor(Date date) {
//
//    Date todaysDate = new Date();
//
//    if (todaysDate.before(date)) {
//      view.notifyUserOfFutureDate();
//    } else {
//      reporterAsync.dispatch(new FetchExpensesAction(date));
//    }
//  }

  public void fetchExpensesBetween(Date firstDate, Date secondDate) {

    if (firstDate.before(secondDate) || firstDate.equals(secondDate)) {

      reporterAsync.dispatch(new FetchExpensesAction<FetchExpensesResponse>(firstDate, secondDate), new GotResponse<FetchExpensesResponse>() {
        @Override
        public void gotResponse(FetchExpensesResponse result) {
          view.updateExpenses(result.getExpenses());
        }

        @Override
        public void onFailure(Throwable caught) {
          view.showConnectionErrorMessage();
        }
      });
    } else {
      view.notifyUserOfDateDiscrepancy();
    }

  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
  }

  public void getAllExpensesYears() {

    reporterAsync.dispatch(new FetchYearsAction<FetchYearsResponse>(), new GotResponse<FetchYearsResponse>() {
      @Override
      public void onFailure(Throwable caught) {
        view.showConnectionErrorMessage();
      }

      @Override
      public void gotResponse(FetchYearsResponse result) {
        view.showExpensesYears(result.getYears());
      }
    });

//    reporterAsync.getYearsOfExpenses(new AsyncCallback<ArrayList<Year>>() {
//      @Override
//      public void onFailure(Throwable caught) {
//        view.showConnectionErrorMessage();
//      }
//
//      @Override
//      public void onSuccess(ArrayList<Year> result) {
//        view.showExpensesYears(result);
//      }
//    });
  }


  public void getMonthsOf(int year) {


    reporterAsync.dispatch(new FetchMonthsAction<FetchMonthsResponse>(year), new GotResponse<FetchMonthsResponse>() {

      @Override
      public void gotResponse(FetchMonthsResponse result) {
        view.showMonthsOfExpenses(result.getMonths());
      }
    });

//    reporterAsync.getMonthOf(year, new AsyncCallback<ArrayList<Month>>() {
//      @Override
//      public void onFailure(Throwable caught) {
//        //To change body of implemented methods use File | Settings | File Templates.
//      }
//
//      @Override
//      public void onSuccess(ArrayList<Month> result) {
//        view.showMonthsOfExpenses(result);
//      }
//    });
  }

  public void getAllExpensesDays(int year, int month) {

    reporterAsync.dispatch(new FetchDaysAction<FetchDaysResponse>(year, month), new GotResponse<FetchDaysResponse>() {
      @Override
      public void gotResponse(FetchDaysResponse result) {
        ArrayList<Day> days = result.getDays();
        view.showDaysExpenses(days);
      }
    });


//    reporterAsync.getDaysOf(year, month, new AsyncCallback<ArrayList<Day>>() {
//      @Override
//      public void onFailure(Throwable caught) {
//        //To change body of implemented methods use File | Settings | File Templates.
//      }
//
//      @Override
//      public void onSuccess(ArrayList<Day> result) {
//        view.showDaysExpenses(result);
//      }
//    });
  }


}
