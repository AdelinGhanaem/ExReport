package com.clouway.exreport.client.accountcreation.view;

import com.clouway.exreport.shared.Account;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.PasswordTextBox;
import com.google.gwt.user.client.ui.TextBox;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountEditor extends Composite implements Editor<Account> {


  interface AccountEditorUiBinder extends UiBinder<HTMLPanel, AccountEditor> {
  }

  private static AccountEditorUiBinder ourUiBinder = GWT.create(AccountEditorUiBinder.class);

  @UiField
  TextBox email;

  @UiField
  PasswordTextBox password;

  public AccountEditor() {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);

    initWidget(rootElement);

  }

}