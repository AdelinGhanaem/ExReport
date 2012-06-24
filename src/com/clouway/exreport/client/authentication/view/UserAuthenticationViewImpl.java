package com.clouway.exreport.client.authentication.view;

import com.clouway.exreport.client.authentication.UserAuthenticationPresenter;
import com.clouway.exreport.client.navigation.InjectablePlaceController;
import com.clouway.exreport.client.navigation.places.NewRegistrationPlace;
import com.clouway.exreport.shared.entites.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationViewImpl extends Composite implements UserAuthenticationView {


  interface UserAuthenticationViewImplUiBinder extends UiBinder<HTMLPanel, UserAuthenticationViewImpl> {

  }

  interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {
  }

  Driver driver = GWT.create(Driver.class);

  private static UserAuthenticationViewImplUiBinder ourUiBinder = GWT.create(UserAuthenticationViewImplUiBinder.class);

  private UserAuthenticationPresenter presenter;

  @UiField
  UserEditor editor;

//  @UiField
//  VerticalPanel panel;

  @UiField
  com.github.gwtbootstrap.client.ui.Button logIn;

  @UiField
  com.github.gwtbootstrap.client.ui.Button newAccount;

  @Inject
  InjectablePlaceController controller;

  HTMLPanel rootElement;


  public UserAuthenticationViewImpl() {

     rootElement = ourUiBinder.createAndBindUi(this);

    initWidget(rootElement);

    driver.initialize(editor);

    User user = new User();

    driver.edit(user);
  }


  @Override
  public void emptyUsername() {

  }

  @Override
  public void emptyPassword() {

  }

  @Override
  public void setPresenter(UserAuthenticationPresenter userAuthenticationPresenter) {
    presenter = userAuthenticationPresenter;
  }


  @UiHandler("logIn")
  public void onClick(ClickEvent event) {
    User user = driver.flush();
    presenter.authenticate(user);

  }

  @UiHandler("newAccount")
  public void onNewRegistration(ClickEvent event) {
    controller.goTo(new NewRegistrationPlace());
  }


}