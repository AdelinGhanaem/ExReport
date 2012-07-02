package com.clouway.exreport.client.expensesreporting.expensesreport;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.ExpenseReporterView;
import com.clouway.exreport.client.navigation.AbstractActivity;
import com.clouway.exreport.client.security.SecurityAction;
import com.clouway.exreport.client.security.SecurityActionFactory;
import com.clouway.exreport.shared.actions.FetchExpensesAction;
import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.reponses.FetchExpensesResponse;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.clouway.exreport.shared.reponses.SecurityResponse;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseReporterPresenterImpl extends AbstractActivity implements ExpenseReporterPresenter {

  private final ExpenseReporterView view;

  private final ActionDispatcherServiceAsync service;

  private SecurityActionFactory securityActionFactory;

  HashMap<Integer, Integer> monthDaysMap = new HashMap<Integer, Integer>() {{
    put(1, 31);
    put(2, 29);
    put(3, 31);
    put(4, 30);
    put(5, 31);
    put(6, 30);
    put(7, 31);
    put(8, 31);
    put(9, 30);
    put(10, 31);
    put(11, 30);
    put(12, 31);
  }};

  @Inject
  public ExpenseReporterPresenterImpl(ExpenseReporterView view, ActionDispatcherServiceAsync reporterAsync, SecurityActionFactory securityActionFactory) {
    this.view = view;
    this.service = reporterAsync;
    this.securityActionFactory = securityActionFactory;
  }

  public void fetchExpensesBetween(Date firstDate, Date secondDate) {

    if (firstDate.before(secondDate) || firstDate.equals(secondDate)) {
      SecurityAction<FetchExpensesAction<FetchExpensesResponse>> securityAction = securityActionFactory.createSecurityAction(new FetchExpensesAction<FetchExpensesResponse>(firstDate, secondDate));
      service.dispatchSecurityAction(securityAction, new GotResponse<SecurityResponse<FetchExpensesResponse>>() {
        @Override
        public void gotResponse(SecurityResponse<FetchExpensesResponse> result) {
          ArrayList<Expense> expenses = result.getResponse().getExpenses();
          double sum = 0;
          for (Expense expense : result.getResponse().getExpenses()) {
            sum += expense.getPrice();
          }
          view.updateExpenses(result.getResponse().getExpenses());
          view.showExpensesSum(sum);
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
    service.dispatchSecurityAction(securityAction, new GotResponse<SecurityResponse<FetchYearsResponse>>() {
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
    ArrayList<Month> months = new ArrayList<Month>();
    for (int i = 1; i <= 12; i++) {
      months.add(new Month(year, i));
    }
    view.showMonthsOfExpenses(months);
  }

  public void getAllExpensesDays(int year, int month) {
    int daysNumber = monthDaysMap.get(month);
    ArrayList<Day> days = new ArrayList<Day>();
    for (int i = 1; i <= daysNumber; i++) {
      days.add(new Day(i, month, year));
    }
    view.showDaysExpenses(days);
  }

  @Override
  public void start(AcceptsOneWidget panel, EventBus eventBus) {
    panel.setWidget(view.asWidget());
  }
}
