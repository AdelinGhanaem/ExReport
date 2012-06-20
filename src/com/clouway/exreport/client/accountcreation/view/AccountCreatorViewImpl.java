package com.clouway.exreport.client.accountcreation.view;

import com.clouway.exreport.client.accountcreation.AccountCreatorPresenterImpl;
import com.clouway.exreport.client.navigation.View;
import com.clouway.exreport.shared.entites.Account;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountCreatorViewImpl extends Composite implements AccountCreatorView, View<AccountCreatorPresenterImpl> {


  interface Driver extends SimpleBeanEditorDriver<Account, AccountEditor> {

  }

  interface AccountCreatorViewImplUiBinder extends UiBinder<HTMLPanel, AccountCreatorViewImpl> {
  }


  Driver driver = GWT.create(Driver.class);

  private static AccountCreatorViewImplUiBinder ourUiBinder = GWT.create(AccountCreatorViewImplUiBinder.class);


  private AccountCreatorPresenterImpl presenter;


  @UiField
  AccountEditor accountEditor;

  @UiField
  Button create;

  public AccountCreatorViewImpl() {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    initWidget(rootElement);

    Account account = new Account();

    driver.initialize(accountEditor);

    driver.edit(account);

  }

  @UiHandler("create")
  public void onCreate(ClickEvent event) {

    Account editedAccount = driver.flush();

    Window.alert(editedAccount.getEmail() + "/" + editedAccount.getPassword());

    presenter.create(editedAccount);

  }

  @Override
  public void showMessage(String messages) {

  }

  @Override
  public void showMessages(ArrayList<String> errors) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setPresenter(AccountCreatorPresenterImpl presenter) {
    //To change body of implemented methods use File | Settings | File Templates.
  }

  @Override
  public void setExpenseReporterPresenter(AccountCreatorPresenterImpl accountCreatorPresenter) {
    presenter = accountCreatorPresenter;
  }

}