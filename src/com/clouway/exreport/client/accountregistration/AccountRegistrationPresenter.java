package com.clouway.exreport.client.accountregistration;

import com.clouway.exreport.client.navigation.Presenter;
import com.clouway.exreport.shared.entites.Account;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface AccountRegistrationPresenter extends Presenter {

  public void register(Account account);

}
