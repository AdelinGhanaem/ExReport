package com.clouway.exreport.server;

import com.clouway.exreport.server.authentication.AuthenticatedUsersRepository;
import com.clouway.exreport.server.comunication.ActionDispatcherServiceImpl;
import com.clouway.exreport.server.expensesreporting.ExpensesRepository;
import com.clouway.exreport.server.expensesreporting.ExpensesRepositoryImpl;
import com.clouway.exreport.shared.entites.Token;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.google.inject.Provider;

import javax.inject.Inject;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class ExpensesRepositoryProvider implements Provider<ExpensesRepository> {

  @Inject
  private AuthenticatedUsersRepository repository;

  @Inject
  private DatastoreService datastoreService;

  @Override
  public ExpensesRepository get() {

    Token tokenThreadLocal = ActionDispatcherServiceImpl.tokenThreadLocal.get();

    String accountKey = repository.getTokenKey(tokenThreadLocal);

    Key key = KeyFactory.stringToKey(accountKey);

    return new ExpensesRepositoryImpl(datastoreService, key);

  }
}
