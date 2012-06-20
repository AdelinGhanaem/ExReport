package com.clouway.exreport.client.authentication.view;

import com.clouway.exreport.client.authentication.UserAuthenticationPresenter;
import com.clouway.exreport.client.navigation.View;
import com.clouway.exreport.shared.entites.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationViewImpl implements UserAuthenticationView, View<UserAuthenticationPresenter> {


  interface UserAuthenticationViewImplUiBinder extends UiBinder<HTMLPanel, UserAuthenticationViewImpl> {

  }

  interface Driver extends SimpleBeanEditorDriver<User, UserEditor> {
  }

  private static UserAuthenticationViewImplUiBinder ourUiBinder = GWT.create(UserAuthenticationViewImplUiBinder.class);

  private UserAuthenticationPresenter presenter;

  @UiField
  UserEditor editor;

  public UserAuthenticationViewImpl() {
    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
  }

  @Override
  public void emptyUsername() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void emptyPassword() {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setExpenseReporterPresenter(UserAuthenticationPresenter userAuthenticationPresenter) {
    presenter = userAuthenticationPresenter;
  }

}