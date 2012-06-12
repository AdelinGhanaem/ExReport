package com.clouway.exreport.client.dashboard;

import com.clouway.exreport.client.navigation.NavigationMenu;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.DivElement;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class CompositePanel extends Composite {
  interface CompositePanelUiBinder extends UiBinder<HTMLPanel, CompositePanel> {
  }

  private static CompositePanelUiBinder ourUiBinder = GWT.create(CompositePanelUiBinder.class);



  @UiField
  HTMLPanel centerPanel;

  @UiField
  NavigationMenu navigationMenu;


    HTMLPanel rootPanel;

  public CompositePanel() {
    rootPanel = ourUiBinder.createAndBindUi(this);
    initWidget(rootPanel);
  }


  public void addWidget(Widget widget) {
    centerPanel.add(widget);
  }

  public Widget asWidget() {
   return this;
  }

}