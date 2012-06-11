package com.clouway.exreport.client.expensesdashboard.view;

import com.clouway.exreport.client.expensesdashboard.ExpenseReporterDashboardPresenter;
import com.clouway.exreport.client.expensesdashboard.ExpenseReporterService;
import com.clouway.exreport.client.expensesdashboard.ExpenseReporterServiceAsync;
import com.clouway.exreport.client.expensesdashboard.view.cells.DayCell;
import com.clouway.exreport.client.expensesdashboard.view.cells.MonthCell;
import com.clouway.exreport.client.expensesdashboard.view.cells.YearCell;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.shared.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.HTMLPanel;
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
public class ExpensesReporterDashboardViewImpl implements ExpenseReporterDashBoardView, TreeViewModel {

  interface ExpensesReporterDashboardViewImplUiBinder extends UiBinder<HTMLPanel, ExpensesReporterDashboardViewImpl> {

  }

  private AsyncDataProvider<Year> yearAsyncDataProvider;

  private AsyncDataProvider<Month> monthAsyncDataProvider;

  private AsyncDataProvider<Day> dayAsyncDataProvider;

  private static ExpensesReporterDashboardViewImplUiBinder ourUiBinder = GWT.create(ExpensesReporterDashboardViewImplUiBinder.class);

  private ExpenseReporterDashboardPresenter presenter;

  private ExpenseReporterServiceAsync async = GWT.create(ExpenseReporterService.class);

  HTMLPanel maiPanel;

  private Year currentYear;

  private Month currentMonth;

  final SingleSelectionModel<Day> singleSelectionModel = new SingleSelectionModel<Day>();

  @UiField(provided = true)
  CellTree cellTree;

  @UiField
  CellTable<Expense> expensesCellTable;


  public ExpensesReporterDashboardViewImpl() {

    presenter = new ExpenseReporterDashboardPresenter(this, async);

    singleSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {

      @Override
      public void onSelectionChange(SelectionChangeEvent event) {

        Day day = singleSelectionModel.getSelectedObject();

        DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("YYYY/MM/DD");

        Date date = new Date(day.getDay(), day.getMonth(), day.getYear());

        presenter.showExpensesFor(date);

      }
    });

    yearAsyncDataProvider = new AsyncDataProvider<Year>() {
      @Override
      protected void onRangeChanged(HasData<Year> display) {
        presenter.getAllExpensesYears();
      }
    };

    monthAsyncDataProvider = new AsyncDataProvider<Month>() {
      @Override
      protected void onRangeChanged(HasData<Month> display) {
        presenter.getMonthsOf(currentYear.getYear());
      }
    };

    dayAsyncDataProvider = new AsyncDataProvider<Day>() {
      @Override
      protected void onRangeChanged(HasData<Day> display) {
        presenter.getAllExpensesDays(currentYear.getYear(), currentMonth.getMonth());
      }
    };


    cellTree = new CellTree(this, null);





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
    return null;
  }

  @Override
  public boolean isLeaf(Object value) {
    return value instanceof Day;
  }


  @Override
  public void updateExpenses(ArrayList<Expense> expenses) {
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
    return maiPanel;
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


}