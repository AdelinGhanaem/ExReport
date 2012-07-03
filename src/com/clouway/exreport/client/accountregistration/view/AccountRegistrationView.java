package com.clouway.exreport.client.accountregistration.view;

import com.clouway.exreport.client.accountregistration.AccountRegistrationPresenterImpl;
import com.google.gwt.user.client.ui.Widget;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountRegistrationView {


  void showMessage(String messages);

  void showMessages(ArrayList<String> errors);

  void setPresenter(AccountRegistrationPresenterImpl presenter);

  Widget asWidget();
}
