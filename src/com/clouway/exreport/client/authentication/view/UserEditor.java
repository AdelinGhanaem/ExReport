package com.clouway.exreport.client.authentication.view;

import com.clouway.exreport.shared.entites.User;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTMLPanel;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserEditor extends Composite implements Editor<User> {

  interface UserEditorUiBinder extends UiBinder<HTMLPanel, UserEditor> {
  }

  private static UserEditorUiBinder ourUiBinder = GWT.create(UserEditorUiBinder.class);

  public UserEditor() {

    HTMLPanel rootElement = ourUiBinder.createAndBindUi(this);
    
    initWidget(rootElement);
  }
}