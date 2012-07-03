package com.clouway.exreport.client.authentication;

import com.clouway.exreport.client.authentication.view.UserAuthenticationView;
import com.clouway.exreport.client.comunication.ActionDispatcherServiceAsync;
import com.clouway.exreport.client.comunication.GotResponse;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.actions.LogoutAction;
import com.clouway.exreport.shared.actions.UserAuthenticationAction;
import com.clouway.exreport.shared.entites.User;
import com.clouway.exreport.shared.reponses.LogoutResponse;
import com.clouway.exreport.shared.reponses.UserAuthenticationResponse;
import com.google.gwt.user.client.Window;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class UserAuthenticationPresenterImpl implements UserAuthenticationPresenter, LogoutEventHandler {


  private final UserAuthenticationView view;

  private ActionDispatcherServiceAsync service;

  private final EventBus handlers;
  private final SecurityTokenProvider provider;

  @Inject
  public UserAuthenticationPresenterImpl(UserAuthenticationView view, ActionDispatcherServiceAsync service,
                                         EventBus handlers, SecurityTokenProvider provider) {
    this.view = view;
    this.service = service;
    this.handlers = handlers;
    this.provider = provider;
  }

  public void authenticate(User user) {
    if (isValid(user)) {
      service.dispatch(new UserAuthenticationAction<UserAuthenticationResponse>(user), new GotResponse<UserAuthenticationResponse>() {
        @Override
        public void gotResponse(UserAuthenticationResponse result) {
          if (result.getToken() == null) {
            view.incorrectUsernameOrPassword();
          } else {
            handlers.fireEvent(new UserAuthenticatedEvent(result.getToken()));
          }
        }

        @Override
        public void onFailure(Throwable caught) {
          Window.alert("Connection error !!");
        }
      });
    }

  }

  private boolean isValid(User user) {
    if ("".equals(user.getUsername())) {
      view.emptyUsername();
      return false;
    }
    if ("".equals(user.getPassword())) {
      view.emptyPassword();
      return false;
    }
    return true;
  }

  @Override
  public void onLogout(LogoutEvent event) {

    service.dispatch(new LogoutAction<LogoutResponse>(provider.getToken()), new GotResponse<LogoutResponse>() {
      @Override
      public void gotResponse(LogoutResponse result) {
        provider.clearToken();
      }
    });
  }
}
