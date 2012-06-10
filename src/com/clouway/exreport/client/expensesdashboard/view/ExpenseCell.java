package com.clouway.exreport.client.expensesdashboard.view;

import com.clouway.exreport.shared.Expense;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpenseCell extends AbstractCell<Expense> {
  @Override
  public void render(Context context, Expense expense, SafeHtmlBuilder sb) {


    if (expense != null) {
      sb.appendHtmlConstant("<div class=\"expensesCell\"> <div class=\"name\">" + expense.getName() + "</div> <div class=\"prise\">" + expense.getPrice() + "</div></div>");
    }
  }
}
