package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.client.security.SecurityActionFactory;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.clouway.exreport.shared.actions.FetchDaysAction;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchMonthsAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.reponses.FetchDaysResponse;
import com.clouway.exreport.shared.reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

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

  public void fetchExpensesBetween(Date firstDate, Date secondDate) {

    if (firstDate.before(secondDate) || firstDate.equals(secondDate)) {
      SecurityAction<FetchExpensesAction<FetchExpensesResponse>> securityAction = securityActionFactory.createSecurityAction(new FetchExpensesAction<FetchExpensesResponse>(firstDate, secondDate));
      asynchService.dispatch(securityAction, new GotResponse<SecurityResponse<FetchExpensesResponse>>() {
        @Override
        public void gotResponse(SecurityResponse<FetchExpensesResponse> result) {
          view.updateExpenses(result.getResponse().getExpenses());
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


  public void getAllExpensesYears() {

    SecurityAction<FetchYearsAction<FetchYearsResponse>> securityAction = securityActionFactory.createSecurityAction(new FetchYearsAction<FetchYearsResponse>());
    asynchService.dispatch(securityAction, new GotResponse<SecurityResponse<FetchYearsResponse>>() {
      @Override
      public void gotResponse(SecurityResponse<FetchYearsResponse> result) {
        view.showExpensesYears(result.getResponse().getYears());
      }

      @Override
      public void onFailure(Throwable caught) {
        view.showConnectionErrorMessage();
      }
    });
  }


  public void getMonthsOf(int year) {
    SecurityAction<FetchMonthsAction<FetchMonthsResponse>> securityAction = securityActionFactory.createSecurityAction(new FetchMonthsAction<FetchMonthsResponse>(year));
    asynchService.dispatch(securityAction, new GotResponse<SecurityResponse<FetchMonthsResponse>>() {
      @Override
      public void gotResponse(SecurityResponse<FetchMonthsResponse> result) {
        view.showMonthsOfExpenses(result.getResponse().getMonths());
      }
    });
  }

  public void getAllExpensesDays(int year, int month) {

    SecurityAction<FetchDaysAction<FetchDaysResponse>> securityAction = securityActionFactory.createSecurityAction(new FetchDaysAction<FetchDaysResponse>(year, month));

    asynchService.dispatch(securityAction, new GotResponse<SecurityResponse<FetchDaysResponse>>() {
      @Override
      public void gotResponse(SecurityResponse<FetchDaysResponse> result) {
        view.showDaysExpenses(result.getResponse().getDays());
      }
    });
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
  }
}
