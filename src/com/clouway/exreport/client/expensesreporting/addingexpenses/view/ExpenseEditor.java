package com.clouway.exreport.client.expensesreporting.addingexpenses.view;

import com.clouway.exreport.shared.entites.Expense;
import com.github.gwtbootstrap.client.ui.TextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.editor.client.LeafValueEditor;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.logical.shared.ValueChangeEvent;
import com.google.gwt.event.logical.shared.ValueChangeHandler;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.datepicker.client.DatePicker;

import java.util.Date;

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

  @Ignore
  @UiField
  TextBox expenseDate;

  @UiField
  DatePicker datePicker;

  private Date pickedDate;

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

    @Override
    public void setValue(Date value) {
      expenseDate.setValue(value.toString());
    }

    @Override
    public Date getValue() {
      return pickedDate;
    }
  };


  public ExpenseEditor() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
    datePicker.addValueChangeHandler(new ValueChangeHandler<Date>() {
      @Override
      public void onValueChange(ValueChangeEvent<Date> dateValueChangeEvent) {
        expenseDate.setText(dateValueChangeEvent.getValue().toString());
        pickedDate = dateValueChangeEvent.getValue();
        datePicker.setVisible(false);
      }
    });

  }


  @UiHandler("expenseDate")
  public void onClick(ClickEvent event) {
    datePicker.setVisible(true);
  }


}