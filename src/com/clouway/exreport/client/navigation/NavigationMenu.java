package com.clouway.exreport.client.navigation;

import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.StackLayoutPanel;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class NavigationMenu extends Composite {


  interface NavigationMenuUiBinder extends UiBinder<StackLayoutPanel, NavigationMenu> {
  }

  private static NavigationMenuUiBinder ourUiBinder = GWT.create(NavigationMenuUiBinder.class);
  @UiField
  Anchor todayExpenses;
  @UiField
  Anchor thisWeekExpenses;
  @UiField
  Anchor thisMonthExpenses;

  public NavigationMenu() {
    StackLayoutPanel rootElement = ourUiBinder.createAndBindUi(this);
    initWidget(rootElement);
  }


}