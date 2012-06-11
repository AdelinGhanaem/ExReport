package com.clouway.exreport.client.expensesdashboard.view.cells;

import com.clouway.exreport.shared.Month;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class MonthCell extends AbstractCell<Month> {

  @Override
  public void render(Context context, Month value, SafeHtmlBuilder sb) {
    sb.appendEscaped(String.valueOf("Month: " + value.getYear() + "/" + value.getMonth()
    ));
  }
}
