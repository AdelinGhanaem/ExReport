package com.clouway.exreport.client.expensesdashboard.view.cells;

import com.clouway.exreport.shared.Day;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DayCell extends AbstractCell<Day> {
  @Override
  public void render(Context context, Day value, SafeHtmlBuilder sb) {
    sb.appendEscaped(String.valueOf("Day: " + value.getYear() + "/" + value.getMonth() + "/" + value.getDay()));
  }
}
