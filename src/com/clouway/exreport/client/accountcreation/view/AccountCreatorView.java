package com.clouway.exreport.client.accountcreation.view;

import java.util.ArrayList;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountCreatorView {


  void showMessage(String messages);

  void showMessages(ArrayList<String> errors);
}
