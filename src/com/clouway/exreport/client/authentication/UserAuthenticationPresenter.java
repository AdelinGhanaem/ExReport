package com.clouway.exreport.client.authentication;

import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.actions.UserAuthenticationAction;
import com.clouway.exreport.shared.entites.User;
import com.clouway.exreport.shared.reponses.UserAuthenticationResponse;
import com.google.gwt.event.shared.HasHandlers;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationPresenter {


  private final UserAuthenticationView view;
  private final ActionDispatcherServiceAsync service;
  private final HasHandlers handlers;


  public UserAuthenticationPresenter(UserAuthenticationView view, ActionDispatcherServiceAsync service, HasHandlers handlers) {
    this.view = view;
    this.service = service;
    this.handlers = handlers;
  }

  public void authenticate(User user) {
    service.dispatch(new UserAuthenticationAction<UserAuthenticationResponse>(user), new GotResponse<UserAuthenticationResponse>() {
      @Override
      public void gotResponse(UserAuthenticationResponse result) {
        handlers.fireEvent(new UserAuthenticatedEvent(result));
      }
    });
  }
}
