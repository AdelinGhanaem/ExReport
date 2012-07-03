package com.clouway.exreport.client.expensesreporting.expensesreport.view;

import com.clouway.exreport.client.expensesreporting.expensesreport.ExpenseReporterPresenter;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.cells.DayCell;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.cells.MonthCell;
import com.clouway.exreport.client.expensesreporting.expensesreport.view.cells.YearCell;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.entites.Expense;
import com.clouway.exreport.shared.entites.Month;
import com.clouway.exreport.shared.entites.Year;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.SelectionChangeEvent;
import com.google.gwt.view.client.SingleSelectionModel;
import com.google.gwt.view.client.TreeViewModel;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

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

  private ExpenseReporterPresenter presenter;

  HTMLPanel maiPanel;


  final SingleSelectionModel<Day> daySelectionModel = new SingleSelectionModel<Day>();

  final SingleSelectionModel<Month> monthSelectionModel = new SingleSelectionModel<Month>();

  final SingleSelectionModel<Year> yearSelectionModel = new SingleSelectionModel<Year>();

  @UiField
  CellTable<Expense> expensesCellTable;

  @UiField
  HorizontalPanel cellTreeScrollPanel;

  private int lastClickedYear;

  private int lastClickedMonth;

  @UiField
  HorizontalPanel panel;

  @UiField
  Label expensesSum;

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

  DateTimeFormat dateTimeFormat = DateTimeFormat.getFormat("yyyy/MM/dd");

  public ExpensesReporterViewImpl() {


    daySelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        Day day = daySelectionModel.getSelectedObject();
        String stringDate = day.getYear() + "/" + day.getMonth() + "/" + day.getDay();
        Date date = dateTimeFormat.parse(stringDate);
        presenter.fetchExpensesBetween(date, date);
      }
    });

    monthSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        Month month = monthSelectionModel.getSelectedObject();
        int lastDayOfTheMonth = monthDaysMap.get(month.getMonth());
        Date firstDate = dateTimeFormat.parse(month.getYear() + "/" + month.getMonth() + "/" + 1);
        Date secondDate = dateTimeFormat.parse(month.getYear() + "/" + month.getMonth() + "/" + lastDayOfTheMonth);
        presenter.fetchExpensesBetween(firstDate, secondDate);
      }
    });

    yearSelectionModel.addSelectionChangeHandler(new SelectionChangeEvent.Handler() {
      @Override
      public void onSelectionChange(SelectionChangeEvent event) {
        Year year = yearSelectionModel.getSelectedObject();
        Date firstDate = dateTimeFormat.parse(year.getYear() + "/" + 1 + "/" + 1);
        Date secondDate = dateTimeFormat.parse(year.getYear() + "/" + 12 + "/" + 31);
        presenter.fetchExpensesBetween(firstDate, secondDate);
      }
    });

    maiPanel = ourUiBinder.createAndBindUi(this);

    expensesCellTable.addColumn(new Column<Expense, String>(new TextCell()) {
      @Override
      public String getValue(Expense object) {
        return object.getName();
      }
    }, "Title:");

    expensesCellTable.addColumn(new Column<Expense, Number>(new NumberCell()) {
      @Override
      public Double getValue(Expense object) {
        return object.getPrice();
      }
    }, "Price:");

    expensesCellTable.addColumn(new Column<Expense, Date>(new DateCell()) {
      @Override
      public Date getValue(Expense object) {
        return object.getDate();
      }
    }, "Date:");
    initWidget(maiPanel);
  }

  @Override
  public <T> NodeInfo<?> getNodeInfo(T value) {
    if (value == null) {
      return new DefaultNodeInfo<Year>(yearAsyncDataProvider, new YearCell(), yearSelectionModel, null);
    }
    if (value instanceof Year) {
      lastClickedYear = ((Year) value).getYear();
      return new DefaultNodeInfo<Month>(monthAsyncDataProvider, new MonthCell(), monthSelectionModel, null);
    }
    if (value instanceof Month) {
      lastClickedMonth = ((Month) value).getMonth();
      return new DefaultNodeInfo<Day>(dayAsyncDataProvider, new DayCell(), daySelectionModel, null);
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
  public void showExpensesSum(double sum) {
    expensesSum.setText(String.valueOf(sum));
  }

  @Override
  public void setPresenter(final ExpenseReporterPresenter presenter) {
    this.presenter = presenter;
    initialize(presenter);
  }

  private void initialize(final ExpenseReporterPresenter presenter) {

    yearAsyncDataProvider = new AsyncDataProvider<Year>() {
      @Override
      protected void onRangeChanged(HasData<Year> display) {
        presenter.getAllExpensesYears();
      }
    };

    monthAsyncDataProvider = new AsyncDataProvider<Month>() {
      @Override
      protected void onRangeChanged(HasData<Month> display) {
        presenter.getMonthsOf(lastClickedYear);
      }
    };

    dayAsyncDataProvider = new AsyncDataProvider<Day>() {
      @Override
      protected void onRangeChanged(HasData<Day> display) {
        presenter.getAllExpensesDays(lastClickedYear, lastClickedMonth);
      }
    };

    presenter.getAllExpensesYears();

    CellTree cellTree = new CellTree(this, null);


    cellTreeScrollPanel.add(cellTree);
  }

  @Override
  public Widget asWidget() {
    return panel;
  }
}