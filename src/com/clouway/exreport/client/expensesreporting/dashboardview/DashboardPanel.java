package com.clouway.exreport.client.expensesreporting.dashboardview;

import com.clouway.exreport.client.security.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.core.client.GWT;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Label;
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
  HTMLPanel centerPanel;
  @UiField
  Label nameLabel;

  @Inject
  SecurityTokenProvider provider;

  HTMLPanel rootPanel;


  private Token token;

  public DashboardPanel() {

    rootPanel = ourUiBinder.createAndBindUi(this);

    initWidget(rootPanel);
  }


  public void addWidget(Widget widget) {
    centerPanel.add(widget);
  }

  public Widget asWidget() {
    return this;
  }

  public void setToken(Token token) {

    nameLabel.setText(token.getUser());

  }


}