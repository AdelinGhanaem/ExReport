package com.clouway.exreport.client.authentication;

import com.clouway.exreport.client.authentication.view.UserAuthenticationView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.actions.UserAuthenticationAction;
import com.clouway.exreport.shared.entites.User;
import com.clouway.exreport.shared.reponses.UserAuthenticationResponse;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationPresenterImpl implements UserAuthenticationPresenter {


  private final UserAuthenticationView view;

  private ActionDispatcherServiceAsync service;

  private final EventBus handlers;

  @Inject
  public UserAuthenticationPresenterImpl(UserAuthenticationView view, ActionDispatcherServiceAsync service, EventBus handlers) {
    this.view = view;
    this.service = service;
    this.handlers = handlers;
  }

  public void authenticate(User user) {
    //    handlers.fireEvent(new UserAuthenticatedEvent(new Token("user")));
    service.dispatch(new UserAuthenticationAction<UserAuthenticationResponse>(user), new GotResponse<UserAuthenticationResponse>() {
      @Override
      public void gotResponse(UserAuthenticationResponse result) {
        if (result.getToken() == null) {
          Window.alert("Invalid username or password");
        }
        handlers.fireEvent(new UserAuthenticatedEvent(result.getToken()));
      }
    });
  }
}
