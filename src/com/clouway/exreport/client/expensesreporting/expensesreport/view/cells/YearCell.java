package com.clouway.exreport.client.expensesreporting.expensesreport.view.cells;

import com.clouway.exreport.shared.entites.Year;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;

/**
* @author Adelin Ghanayem adelin.ghanaem@clouway.com
*/
public class YearCell extends AbstractCell<Year> {
  @Override
  public void render(Context context, Year value, SafeHtmlBuilder sb) {
    if (value != null) {
      sb.appendEscaped(String.valueOf("Year: " + value.getYear()));
    }
  }
}
