package com.clouway.exreport.client.navigation;

import com.google.gwt.user.client.ui.Widget;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface View<T extends Presenter> {

  public void setExpenseReporterPresenter(T t);

  public Widget asWidget();


}
