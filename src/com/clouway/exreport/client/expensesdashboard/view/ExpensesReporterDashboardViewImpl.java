package com.clouway.exreport.client.expensesdashboard.view;

import com.clouway.exreport.client.expensesdashboard.ExpenseReporterDashboardPresenter;
import com.clouway.exreport.client.expensesdashboard.ExpenseReporterService;
import com.clouway.exreport.client.expensesdashboard.ExpenseReporterServiceAsync;
import com.clouway.exreport.shared.Day;
import com.clouway.exreport.shared.Expense;
import com.clouway.exreport.shared.Month;
import com.clouway.exreport.shared.Year;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.logical.shared.OpenEvent;
import com.google.gwt.event.logical.shared.OpenHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.cellview.client.CellTable;
import com.google.gwt.user.cellview.client.CellTree;
import com.google.gwt.user.cellview.client.TextColumn;
import com.google.gwt.user.cellview.client.TreeNode;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.ListDataProvider;
import com.google.gwt.view.client.MultiSelectionModel;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesReporterDashboardViewImpl implements ExpenseReporterDashBoardView {

    interface ExpensesReporterDashboardViewImplUiBinder extends UiBinder<HTMLPanel, ExpensesReporterDashboardViewImpl> {
    }

    private static ExpensesReporterDashboardViewImplUiBinder ourUiBinder = GWT.create(ExpensesReporterDashboardViewImplUiBinder.class);


    private ListDataProvider<Expense> listDataProvider = new ListDataProvider<Expense>();

    private ExpenseReporterDashboardPresenter presenter;

    private ExpenseReporterServiceAsync async = GWT.create(ExpenseReporterService.class);

    HTMLPanel maiPanel;

    @UiField(provided = true)
    CellTree cellTree;


    private ExpensesTreeViewModel viewModel = new ExpensesTreeViewModel(null);

    public ExpensesReporterDashboardViewImpl() {

        presenter = new ExpenseReporterDashboardPresenter(this, async);
        presenter.getAllExpensesYears();
        cellTree = new CellTree(viewModel, null);
        cellTree.addOpenHandler(new OpenHandler<TreeNode>() {
            @Override
            public void onOpen(OpenEvent<TreeNode> treeNodeOpenEvent) {
                TreeNode treeNode = treeNodeOpenEvent.getTarget();
                Object o = treeNode.getValue();
                Class aClass = o.getClass();
                Window.alert(aClass.getName());
            }
        });
        maiPanel = ourUiBinder.createAndBindUi(this);
    }


    @Override
    public void renderTodaysExpense(List<Expense> expenses) {
        listDataProvider.setList(expenses);
    }


    @Override
    public void notifyUserOfFutureDate() {

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

    @Override
    public void showExpensesYears(ArrayList<Year> yearList) {
        viewModel.setYearListDataProvider(new ListDataProvider<Year>(yearList));
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