package com.clouway.exreport.client.expensesdashboard.view;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;

import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesReporterDashboardViewImpl implements ExpenseReporterDashBoardView {

  interface ExpensesReporterDashboardViewImplUiBinder extends UiBinder<HTMLPanel, ExpensesReporterDashboardViewImpl> {

  }

  private static ExpensesReporterDashboardViewImplUiBinder ourUiBinder = GWT.create(ExpensesReporterDashboardViewImplUiBinder.class);


  private ListDataProvider<Expense> listDataProvider = new ListDataProvider<Expense>();

  HTMLPanel maiPanel;

  @UiField
  ExpensesPager expensesPage;

  @UiField
  CellTable<Expense> expensesTable;

  public ExpensesReporterDashboardViewImpl() {

    maiPanel = ourUiBinder.createAndBindUi(this);

    listDataProvider.addDataDisplay(expensesTable);

    TextColumn<Expense> expenseName = new TextColumn<Expense>() {
      @Override
      public String getValue(Expense object) {
        return object.getName();
      }
    };

    TextColumn<Expense> expensePrice = new TextColumn<Expense>() {
      @Override
      public String getValue(Expense object) {
        return String.valueOf(object.getPrice());
      }
    };

    expensesTable.addColumn(expenseName, "Expense");

    expensesTable.addColumn(expensePrice, "Price");
  }


  @Override
  public void renderTodaysExpense(List<Expense> expenses) {

    expensesTable.setRowCount(expenses.size(), true);

    listDataProvider.setList(expenses);

    expensesTable.setRowData(0, expenses);


  }


  @Override
  public void notifyUserOfFutureDate() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void notifyUserOfDateDiscrepancy() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void showConnectionErrorMessage() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public Widget asWidget() {
    return maiPanel;
  }


}