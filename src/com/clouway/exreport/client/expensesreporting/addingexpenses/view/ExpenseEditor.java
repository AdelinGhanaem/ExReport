package com.clouway.exreport.client.expensesreporting.addingexpenses.view;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseEditor extends Composite implements Editor<Expense> {

  interface ExpenseEditorUiBinder extends UiBinder<HTMLPanel, ExpenseEditor> {
  }


  private static ExpenseEditorUiBinder ourUiBinder = GWT.create(ExpenseEditorUiBinder.class);
  @UiField
  TextBox name;

  @Ignore
  @UiField
  TextBox expensePrice;

  LeafValueEditor<Double> price = new LeafValueEditor<Double>() {
    @Override
    public void setValue(Double value) {
      expensePrice.setValue(String.valueOf(value));
    }

    @Override
    public Double getValue() {
      return Double.valueOf(expensePrice.getText());
    }
  };


  public ExpenseEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }


}