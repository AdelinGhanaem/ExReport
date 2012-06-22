package com.clouway.exreport.client.expensesreporting.expensesreport.view;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.cells.DayCell;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.cells.MonthCell;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.cells.YearCell;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.entites.Year;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.SimplePager;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import java.util.ArrayList;
import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesReporterViewImpl extends Composite implements ExpenseReporterView, TreeViewModel {


  interface ExpensesReporterDashboardViewImplUiBinder extends UiBinder<HTMLPanel, ExpensesReporterViewImpl> {

  }

  private AsyncDataProvider<Year> yearAsyncDataProvider;

  private AsyncDataProvider<Month> monthAsyncDataProvider;

  private AsyncDataProvider<Day> dayAsyncDataProvider;

  private static ExpensesReporterDashboardViewImplUiBinder ourUiBinder = GWT.create(ExpensesReporterDashboardViewImplUiBinder.class);

  private ExpenseReporterPresenter expenseReporterPresenter;

  private ActionDispatcherServiceAsync async;

  HTMLPanel maiPanel;

  private Year currentYear;

  private Month currentMonth;

  final SingleSelectionModel<Day> singleSelectionModel = new SingleSelectionModel<Day>();


  @UiField
  CellTable<Expense> expensesCellTable;

  @UiField
  ScrollPanel cellTreeScrollPanel;

  @UiField
  HorizontalPanel cellTreePanel;

  @UiField
  HorizontalPanel panel;


  public ExpensesReporterViewImpl() {

    singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

      @Override
      public void onSelectionChange(SelectionChangeEvent event) {

        Day day = singleSelectionModel.getSelectedObject();

        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("YYYY/MM/DD");

        Date date = new Date(day.getDay(), day.getMonth(), day.getYear());

      }
    });

    SimplePager.Resources pagerResources = GWT.create(SimplePager.Resources.class);

    maiPanel = ourUiBinder.createAndBindUi(this);

    expensesCellTable.addColumn(new Column<Expense, String>(new TextCell()) {
      @Override
      public String getValue(Expense object) {
        return object.getName();
      }
    });

    expensesCellTable.addColumn(new Column<Expense, String>(new TextCell()) {
      @Override
      public String getValue(Expense object) {
        return String.valueOf(object.getPrice());
      }
    });
    initWidget(maiPanel);
  }

  @Override
  public <T> NodeInfo<?> getNodeInfo(T value) {
    if (value == null) {
      return new DefaultNodeInfo<Year>(yearAsyncDataProvider, new YearCell());
    }

    if (value instanceof Year) {

      currentYear = (Year) value;

      return new DefaultNodeInfo<Month>(monthAsyncDataProvider, new MonthCell());

    }
    if (value instanceof Month) {

      currentMonth = (Month) value;

      return new DefaultNodeInfo<Day>(dayAsyncDataProvider, new DayCell(), singleSelectionModel, null);
    }
    if (value instanceof Day) {
      Day day = (Day) value;
      Window.alert(String.valueOf(day.getDay()));

//      expenseReporterPresenter.fetchExpensesBetween(day,day);

    }
    return null;
  }

  @Override
  public boolean isLeaf(Object value) {
    return value instanceof Day;
  }


  @Override
  public void updateExpenses(ArrayList<Expense> expenses) {
    expensesCellTable.setVisibleRange(0, expenses.size());
    expensesCellTable.setRowData(0, expenses);
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
    return panel;
  }

  @Override
  public void showExpensesYears(ArrayList<Year> yearList) {

    yearAsyncDataProvider.updateRowCount(yearList.size(), true);

    yearAsyncDataProvider.updateRowData(0, yearList);

  }

  @Override
  public void showMonthsOfExpenses(ArrayList<Month> months) {

    monthAsyncDataProvider.updateRowCount(months.size(), true);

    monthAsyncDataProvider.updateRowData(0, months);

  }

  @Override
  public void showDaysExpenses(ArrayList<Day> days) {

    dayAsyncDataProvider.updateRowCount(days.size(), true);

    dayAsyncDataProvider.updateRowData(0, days);

  }

  @Override
  public void setExpenseReporterPresenter(final ExpenseReporterPresenter expenseReporterPresenter) {

    this.expenseReporterPresenter = expenseReporterPresenter;

    yearAsyncDataProvider = new AsyncDataProvider<Year>() {
      @Override
      protected void onRangeChanged(HasData<Year> display) {
        expenseReporterPresenter.getAllExpensesYears();
      }
    };

    monthAsyncDataProvider = new AsyncDataProvider<Month>() {
      @Override
      protected void onRangeChanged(HasData<Month> display) {
        expenseReporterPresenter.getMonthsOf(currentYear.getYear());
      }
    };

    dayAsyncDataProvider = new AsyncDataProvider<Day>() {
      @Override
      protected void onRangeChanged(HasData<Day> display) {
        expenseReporterPresenter.getAllExpensesDays(currentYear.getYear(), currentMonth.getMonth());
      }
    };

    expenseReporterPresenter.getAllExpensesYears();

    CellTree cellTree = new CellTree(this, null);

    cellTreePanel.add(cellTree);

  }
}