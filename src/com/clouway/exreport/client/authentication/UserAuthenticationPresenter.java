package com.clouway.exreport.client.authentication;

import com.clouway.exreport.client.navigation.Presenter;
import com.clouway.exreport.shared.entites.User;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserAuthenticationPresenter extends Presenter {

  public void authenticate(User user);
}
