package com.clouway.exreport.client.expensesreporting.addingexpenses.view;

import com.clouway.exreport.client.expensesreporting.addingexpenses.AddExpensesPresenter;
import com.clouway.exreport.shared.entites.Expense;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.github.gwtbootstrap.client.ui.constants.ButtonType;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AddExpensesViewImpl extends Composite implements AddExpensesView ,Editor<Expense> {


  interface AddExpensesViewImplUiBinder extends UiBinder<HTMLPanel, AddExpensesViewImpl> {

  }

  private static AddExpensesViewImplUiBinder ourUiBinder = GWT.create(AddExpensesViewImplUiBinder.class);

  interface Driver extends SimpleBeanEditorDriver<Expense, ExpenseEditor> {
  }

  private AddExpensesPresenter presenter;

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
  public void setPresenter(AddExpensesPresenter addExpensesPresenter) {

    presenter = addExpensesPresenter;

  }


  @Override
  public void notifyNegativeExpensePriceValue() {

  }

  @Override
  public void notifyUserOfConnectionError() {
  }

  @UiHandler("save")
  public void onClick(ClickEvent event) {
    Expense returnedExpense = expenseDriver.flush();
    presenter.addExpense(returnedExpense);
  }
}