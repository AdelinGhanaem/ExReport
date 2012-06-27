package com.clouway.exreport.server.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.google.appengine.api.memcache.MemcacheService;
import com.google.appengine.api.memcache.MemcacheServiceFactory;
import com.google.appengine.tools.development.testing.LocalMemcacheServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class AuthenticatedUsersRepositoryImplTest {

  private LocalServiceTestHelper helper = new LocalServiceTestHelper(new LocalMemcacheServiceTestConfig());

  private MemcacheService service;

  AuthenticatedUsersRepository authenticatedUsersRepository;

  @Before
  public void setUp() throws Exception {

    helper.setUp();

    service = MemcacheServiceFactory.getMemcacheService();

    authenticatedUsersRepository = new AuthenticatedUsersRepositoryImpl(service);


  }

  @After
  public void tearDown() throws Exception {
    helper.tearDown();
  }

  @Test
  public void returnsTrueIfTokenIsPreviouslyPersisted() {

    Token token = new Token("User");

    service.put(token.getUser(), token);

    boolean isAuthenticated = authenticatedUsersRepository.isAuthorized(token);

    assertThat(isAuthenticated, is(equalTo(true)));

  }


  @Test
  public void returnsFalseWhenTokeIsNotPreviouslyPersisted() {

    Token token = new Token("User");

    boolean isAuthenticated = authenticatedUsersRepository.isAuthorized(token);

    assertThat(isAuthenticated, is(equalTo(false)));
  }


  @Test
  public void tokenIsPersistedCorrectly() {

    Token token = new Token("user");

    authenticatedUsersRepository.persist(token);

    Token returnedToken = (Token) service.get(token.getUser());

    assertThat(returnedToken, is(notNullValue()));

    assertThat(returnedToken.getUser(), is(equalTo(token.getUser())));

  }


}
