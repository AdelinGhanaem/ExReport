package com.clouway.exreport.client.accountcreation;

import com.clouway.exreport.client.navigation.Presenter;
import com.clouway.exreport.shared.entites.Account;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountCreatorPresenter extends Presenter {

  public void create(Account account);

}
