package com.clouway.exreport.client.authentication.view;

import com.clouway.exreport.client.authentication.UserAuthenticationPresenter;
import com.clouway.exreport.client.navigation.View;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public interface UserAuthenticationView extends View<UserAuthenticationPresenter> {

  void emptyUsername();

  void emptyPassword();

  void incorrectUsernameOrPassword();
}
