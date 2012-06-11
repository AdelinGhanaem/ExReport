package com.clouway.exreport.client.expensesdashboard.view;

import com.clouway.exreport.client.expensesdashboard.ExpenseReporterDashboardPresenter;
import com.clouway.exreport.client.expensesdashboard.ExpenseReporterService;
import com.clouway.exreport.client.expensesdashboard.ExpenseReporterServiceAsync;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.ListDataProvider;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesReporterDashboardViewImpl implements ExpenseReporterDashBoardView {

  interface ExpensesReporterDashboardViewImplUiBinder extends UiBinder<HTMLPanel, ExpensesReporterDashboardViewImpl> {
  }

  private AsyncDataProvider<Year> yearAsyncDataProvider;


  private static ExpensesReporterDashboardViewImplUiBinder ourUiBinder = GWT.create(ExpensesReporterDashboardViewImplUiBinder.class);

  private ExpenseReporterDashboardPresenter presenter;

  private ExpenseReporterServiceAsync async = GWT.create(ExpenseReporterService.class);

  HTMLPanel maiPanel;

  @UiField(provided = true)
  CellTree cellTree;


  private ExpensesTreeViewModel viewModel = new ExpensesTreeViewModel(null);

  public ExpensesReporterDashboardViewImpl() {

    presenter = new ExpenseReporterDashboardPresenter(this, async);

    presenter.getAllExpensesYears();


    yearAsyncDataProvider = new AsyncDataProvider<Year>() {
      @Override
      protected void onRangeChanged(HasData<Year> display) {
        Window.alert("onRangeChanged ... is fired ... !");
        presenter.getAllExpensesYears();
      }
    };

    viewModel.setYearListDataProvider(yearAsyncDataProvider);
    cellTree = new CellTree(viewModel, null);
    maiPanel = ourUiBinder.createAndBindUi(this);

  }


  @Override
  public void renderTodaysExpense(List<Expense> expenses) {
  }


  @Override
  public void notifyUserOfFutureDate() {

  }

  @Override
  public void notifyUserOfDateDiscrepancy() {

  }

  @Override
  public void showConnectionErrorMessage() {

  }

  @Override
  public Widget asWidget() {
    return maiPanel;
  }

  @Override
  public void showExpensesYears(ArrayList<Year> yearList) {
    yearAsyncDataProvider.updateRowCount(yearList.size(), true);
    yearAsyncDataProvider.updateRowData(0, yearList);
  }

  @Override
  public void showMonthsOfExpenses(ArrayList<Month> months) {
    viewModel.setMonthListDataProvider(new ListDataProvider<Month>(months));
  }

  @Override
  public void showDaysExpenses(ArrayList<Day> days) {
    viewModel.setDayListDataProvider(new ListDataProvider<Day>(days));
  }
}