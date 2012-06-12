package com.clouway.exreport.client.expensesreporting.addingexpenses.view;

import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenter;
import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenterImpl;
import com.clouway.exreport.shared.Expense;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpensesViewImpl extends Composite implements AddExpensesView {


    interface AddExpensesViewImplUiBinder extends UiBinder<HTMLPanel, AddExpensesViewImpl> {

    }

    private static AddExpensesViewImplUiBinder ourUiBinder = GWT.create(AddExpensesViewImplUiBinder.class);

    interface Driver extends SimpleBeanEditorDriver<Expense, ExpenseEditor> {
    }

    private AddExpensesPresenter presenter = new AddExpensesPresenterImpl(null, null, this);

    @UiField
    Button save;

    @UiField
    ExpenseEditor expenseEditor;

    private HTMLPanel rootElement;

    private Driver expenseDriver = GWT.create(Driver.class);

    public AddExpensesViewImpl() {

        rootElement = ourUiBinder.createAndBindUi(this);

        initWidget(rootElement);

        expenseDriver.initialize(expenseEditor);

        Expense expense = new Expense();

        expenseDriver.edit(expense);

    }


    @Override
    public void notifyNegativeExpensePriceValue() {

    }

    @Override
    public void notifyExpenseCanNotBeAddedInFutureDate() {

    }

    @Override
    public void notifyUserOfConnectionError() {

    }


    @UiHandler("save")
    public void onClick(ClickEvent event) {




        Expense returnedExpense = expenseDriver.flush();

        Window.alert(returnedExpense.getName() + "/" + returnedExpense.getPrice());

//        presenter.addExpense(expense, new Date());
    }

    public Widget asWidget() {
        return rootElement;
    }

}