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
  public void returnsTokenKeyIfTokenIsPreviouslyPersisted() {
    Token token = new Token("User");
    String key = "account key";
    service.put(token, key);
    String returnedKey = authenticatedUsersRepository.getTokenKey(token);
    assertThat(returnedKey, is(equalTo(key)));
  }


  @Test
  public void returnsNullWhenTokeIsNotPreviouslyPersisted() {
    Token token = new Token("User");
    String returnedKey = authenticatedUsersRepository.getTokenKey(token);
    assertThat(returnedKey, is(equalTo(null)));
  }


  @Test
  public void userIsAddedCorrectly() {

    Token token = new Token("mail@mail.com");

    String userKey = "key";

    authenticatedUsersRepository.addToken(token, userKey);

    String key = (String) service.get(token);

    assertThat(key, is(notNullValue()));

    assertThat(key, is(userKey));

  }


}
