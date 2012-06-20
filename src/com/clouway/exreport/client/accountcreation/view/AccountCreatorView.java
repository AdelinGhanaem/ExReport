package com.clouway.exreport.client.accountcreation.view;

import com.clouway.exreport.client.accountcreation.AccountCreatorPresenterImpl;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountCreatorView {


  void showMessage(String messages);

  void showMessages(ArrayList<String> errors);

  void setPresenter(AccountCreatorPresenterImpl presenter);

  Widget asWidget();
}
