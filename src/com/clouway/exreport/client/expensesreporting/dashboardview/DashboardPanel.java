package com.clouway.exreport.client.expensesreporting.dashboardview;

import com.clouway.exreport.shared.SecurityTokenProvider;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class DashboardPanel extends Composite {


  interface CompositePanelUiBinder extends UiBinder<HTMLPanel, DashboardPanel> {

  }

  private static CompositePanelUiBinder ourUiBinder = GWT.create(CompositePanelUiBinder.class);


  @UiField
  HorizontalPanel expensesPanel;
  @UiField
  HorizontalPanel addingExpenses;

  @Inject
  SecurityTokenProvider provider;

  HTMLPanel rootPanel;

  public DashboardPanel() {
    rootPanel = ourUiBinder.createAndBindUi(this);
    initWidget(rootPanel);
  }

  public void setExpensesReportingPanel(Widget widget) {
    expensesPanel.add(widget);
  }

  public void setAddingExpensesPanel(Widget widget) {
    addingExpenses.add(widget );
  }

}