package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.authentication.SecurityAction;
import com.clouway.exreport.client.authentication.SecurityActionFactory;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.clouway.exreport.shared.actions.FetchDaysAction;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchMonthsAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.reponses.FetchDaysResponse;
import com.clouway.exreport.shared.reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseReporterPresenterImpl extends AbstractActivity implements ExpenseReporterPresenter {

  private final ExpenseReporterView view;

  private final ActionDispatcherServiceAsync asynchService;

  private SecurityActionFactory securityActionFactory;

  @Inject
  public ExpenseReporterPresenterImpl(ExpenseReporterView view, ActionDispatcherServiceAsync reporterAsync, SecurityActionFactory securityActionFactory) {
    this.view = view;
    this.asynchService = reporterAsync;
    this.securityActionFactory = securityActionFactory;
  }


//  public void showExpensesFor(Date date) {
//
//    Date todaysDate = new Date();
//
//    if (todaysDate.before(date)) {
//      view.notifyUserOfFutureDate();
//    } else {
//      asynchService.dispatch(new FetchExpensesAction(date));
//    }
//  }

  public void fetchExpensesBetween(Date firstDate, Date secondDate) {

    if (firstDate.before(secondDate) || firstDate.equals(secondDate)) {
      SecurityAction<FetchExpensesAction<FetchExpensesResponse>> securityAction = securityActionFactory.createSecurity(new FetchExpensesAction<FetchExpensesResponse>(firstDate, secondDate));

      asynchService.dispatch(securityAction, new GotResponse<SecurityResponse<FetchExpensesResponse>>() {
        @Override
        public void gotResponse(SecurityResponse<FetchExpensesResponse> result) {
          view.updateExpenses(result.getResponse().getExpenses());
        }
      });
    } else {
      view.notifyUserOfDateDiscrepancy();
    }

  }


  public void getAllExpensesYears() {
    SecurityAction<FetchYearsAction<FetchYearsResponse>> securityAction = securityActionFactory.createSecurity(new FetchYearsAction<FetchYearsResponse>());

    asynchService.dispatch(securityAction, new GotResponse<SecurityResponse<FetchYearsResponse>>() {
      @Override
      public void gotResponse(SecurityResponse<FetchYearsResponse> result) {
        view.showExpensesYears(result.getResponse().getYears());
      }
    });
  }


  public void getMonthsOf(int year) {


    asynchService.dispatch(new FetchMonthsAction<FetchMonthsResponse>(year), new GotResponse<FetchMonthsResponse>() {

      @Override
      public void gotResponse(FetchMonthsResponse result) {
        view.showMonthsOfExpenses(result.getMonths());
      }
    });

//    asynchService.getMonthOf(year, new AsyncCallback<ArrayList<Month>>() {
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

    asynchService.dispatch(new FetchDaysAction<FetchDaysResponse>(year, month), new GotResponse<FetchDaysResponse>() {
      @Override
      public void gotResponse(FetchDaysResponse result) {
        ArrayList<Day> days = result.getDays();
        view.showDaysExpenses(days);
      }
    });


//    asynchService.getDaysOf(year, month, new AsyncCallback<ArrayList<Day>>() {
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

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
  }
}
