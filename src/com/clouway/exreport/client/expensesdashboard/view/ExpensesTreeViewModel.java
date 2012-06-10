package com.clouway.exreport.client.expensesdashboard.view;

import com.clouway.exreport.client.expensesdashboard.ExpenseReporterDashboardPresenter;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.SelectionModel;
import com.google.gwt.view.client.TreeViewModel;

/**
 * Created with IntelliJ IDEA.
 * User: Adio
 * Date: 6/10/12
 * Time: 12:57 AM
 * To change this template use File | Settings | File Templates.
 */
public class ExpensesTreeViewModel implements TreeViewModel {


    private SelectionModel<Expense> selectionModel;

    private ExpenseCell expenseCell;

    private ExpenseReporterDashboardPresenter presenter;


    private ListDataProvider<Year> yearListDataProvider = new ListDataProvider<com.clouway.exreport.shared.Year>();
    private ListDataProvider<Month> monthListDataProvider;
    private ListDataProvider<Day> dayListDataProvider;

    public void setYearListDataProvider(ListDataProvider<Year> yearListDataProvider) {
        this.yearListDataProvider = yearListDataProvider;
    }

    public void setMonthListDataProvider(ListDataProvider<com.clouway.exreport.shared.Month> monthListDataProvider) {
        this.monthListDataProvider = monthListDataProvider;
    }

    public void setDayListDataProvider(ListDataProvider<com.clouway.exreport.shared.Day> dayListDataProvider) {
        this.dayListDataProvider = dayListDataProvider;
    }

    public ExpensesTreeViewModel(final SelectionModel<Expense> selectionModel) {
        this.selectionModel = selectionModel;
        expenseCell = new ExpenseCell();
    }

    @Override
    public <T> NodeInfo<?> getNodeInfo(T value) {

        if (value == null) {
            return new DefaultNodeInfo<Year>(yearListDataProvider, new YearCell());
        }
        if (value instanceof Month) {
            return new DefaultNodeInfo<Month>(monthListDataProvider, new MonthCell());
        }
        if (value instanceof Day) {
            return new DefaultNodeInfo<Day>(dayListDataProvider, new DayCell());
        }
        return null;
    }

    @Override
    public boolean isLeaf(Object value) {
        return value instanceof Day;
    }


    private class YearCell extends AbstractCell<Year> {
        @Override
        public void render(Context context, Year value, SafeHtmlBuilder sb) {
            if (value != null) {
                sb.appendEscaped(String.valueOf("Year: " + value.getYear()));

            }
        }
    }

    private class MonthCell extends AbstractCell<Month> {

        @Override
        public void render(Context context, Month value, SafeHtmlBuilder sb) {
            sb.appendEscaped(String.valueOf("Month: " + value.getMonthNumber()));
        }

    }

    private class DayCell extends AbstractCell<Day> {
        @Override
        public void render(Context context, Day value, SafeHtmlBuilder sb) {
            sb.appendEscaped(String.valueOf("Month: " + value.getDay()));
        }
    }
}
