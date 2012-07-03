package com.clouway.exreport.client.expensesreporting.dashboardview;

import com.clouway.exreport.client.authentication.LogoutEvent;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.github.gwtbootstrap.client.ui.Brand;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

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

  @UiField
  Brand usernameLabel;

  @UiField
  Button logout;

  @Inject
  SecurityTokenProvider provider;

  @Inject
  EventBus eventBus;

  HTMLPanel rootPanel;

  public DashboardPanel() {
    rootPanel = ourUiBinder.createAndBindUi(this);
    initWidget(rootPanel);
  }

  public void setExpensesReportingPanel(Widget widget) {
    expensesPanel.add(widget);
  }

  public void setAddingExpensesPanel(Widget widget) {
    addingExpenses.add(widget);
  }

  public void setUsernameLabel(String username) {
    Window.alert(username);
    usernameLabel.setText("Username: " + username);
  }

  @UiHandler("logout")
  public void onClick(ClickEvent event) {
    eventBus.fireEvent(new LogoutEvent(provider.getToken()));
  }

}