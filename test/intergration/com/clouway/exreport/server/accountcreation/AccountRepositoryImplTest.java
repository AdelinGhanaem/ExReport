package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.shared.entites.Account;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AccountRepositoryImplTest {

  private LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

  private DatastoreService service;

  AccountRepository repository;

  private String accountEntityKind = "Account";

  @Before
  public void setUp() throws Exception {
    helper.setUp();
    service = DatastoreServiceFactory.getDatastoreService();
    repository = new AccountRepositoryImpl(service);
  }

  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

  @Test
  public void accountIsReturnedByUsernameAndPassword() {
    String username = "username";
    String password = "password";
    Entity entity = new Entity("Account");
    entity.setProperty("username", username);
    entity.setProperty("password", password);
    service.put(entity);
    Account returnedAccount = repository.getAccount(username, password);
    assertThat(returnedAccount, is(notNullValue()));
    assertThat(username, is(equalTo(returnedAccount.getEmail())));
    assertThat(password, is(equalTo(returnedAccount.getPassword())));
  }

  @Test
  public void returnsNullWhenAccountDoesNotExist() {

    String username = "username";

    String password = "password";

    Entity entity = new Entity("Account");

    entity.setProperty("username", username);

    entity.setProperty("password", password);

    Account returnedAccount = repository.getAccount(username, password);

    assertThat(returnedAccount, is(nullValue()));

  }


  @Test
  public void accountIsPersisted() {

    String username = "user";

    String password = "password";

    repository.persis(new Account(username, password));

    Query query = new Query(accountEntityKind);

    query.addFilter("name", Query.FilterOperator.EQUAL,username);

    query.addFilter("password", Query.FilterOperator.EQUAL, password);

    Entity entity = service.prepare(query).asSingleEntity();

    assertThat(entity, is(notNullValue()));

    assertThat((String) entity.getProperty("name"), is(equalTo("user")));

    assertThat((String) entity.getProperty("password"), is(equalTo("password")));
  }

}
