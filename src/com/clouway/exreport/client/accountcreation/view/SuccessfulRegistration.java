package com.clouway.exreport.client.accountcreation.view;

import com.clouway.exreport.client.navigation.InjectablePlaceController;
import com.clouway.exreport.client.navigation.Presenter;
import com.clouway.exreport.client.navigation.View;
import com.clouway.exreport.client.navigation.places.DashboardPlace;
import com.github.gwtbootstrap.client.ui.Button;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;

import javax.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SuccessfulRegistration implements View {


  interface SuccessfulRegistrationUiBinder extends UiBinder<HTMLPanel, SuccessfulRegistration> {
  }

  private static SuccessfulRegistrationUiBinder ourUiBinder = GWT.create(SuccessfulRegistrationUiBinder.class);

  private HTMLPanel rootElement;

  @UiField
  VerticalPanel container;

  @Inject
  InjectablePlaceController controller;

  @UiField
  Button dashBoard;

  public SuccessfulRegistration() {

    rootElement = ourUiBinder.createAndBindUi(this);

  }

  @Override
  public void setPresenter(Presenter presenter) {

  }


  @Override
  public Widget asWidget() {
    return rootElement;
  }


  @UiHandler("dashBoard")
  public void onClick(ClickEvent event) {
    controller.goTo(new DashboardPlace());
  }

}