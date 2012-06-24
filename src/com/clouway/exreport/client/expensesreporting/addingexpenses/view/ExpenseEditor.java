package com.clouway.exreport.client.expensesreporting.addingexpenses.view;

import com.clouway.exreport.shared.entites.Expense;
import com.github.gwtbootstrap.client.ui.Button;
import com.github.gwtbootstrap.client.ui.WellForm;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.TextBox;

import java.util.Date;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseEditor extends Composite implements Editor<Expense> {

  interface ExpenseEditorUiBinder extends UiBinder<HTMLPanel, ExpenseEditor> {
  }


  private static ExpenseEditorUiBinder ourUiBinder = GWT.create(ExpenseEditorUiBinder.class);

  @UiField
  com.github.gwtbootstrap.client.ui.TextBox name;

  @Ignore
  @UiField
  com.github.gwtbootstrap.client.ui.TextBox expensePrice;

  @Ignore
  @UiField
  com.github.gwtbootstrap.client.ui.TextBox expenseDate;


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


  LeafValueEditor<Date> date = new LeafValueEditor<Date>() {

    Date date = new Date();

    @Override
    public void setValue(Date value) {
      expenseDate.setValue(String.valueOf(new Date()));
    }

    @Override
    public Date getValue() {
      return new Date();
    }
  };


  public ExpenseEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
    expenseDate.setText("2012/03/06");
  }
}