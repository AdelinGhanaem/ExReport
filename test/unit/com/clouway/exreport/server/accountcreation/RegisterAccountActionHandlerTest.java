package com.clouway.exreport.server.accountcreation;

import com.clouway.exreport.server.accountcreation.actionhandlers.RegisterAccountActionHandler;
import com.clouway.exreport.shared.actions.RegisterAccountAction;
import com.clouway.exreport.shared.entites.Account;
import com.clouway.exreport.shared.reponses.RegisterAccountResponse;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class RegisterAccountActionHandlerTest {


  @Mock
  AccountCreator accountCreator;

  private RegisterAccountActionHandler registerAccountActionHandler;

  @Before

  public void setUp() {

    initMocks(this);

    registerAccountActionHandler = new RegisterAccountActionHandler(accountCreator);

  }


  //happy path
  @Test
  public void createsAnAccountAndReturnsResponseContainingTheCreatedAccountWithEmptyErrorsList() {

    Account account = new Account("mail@mail.com", "passw123");

    List<String> erros = new ArrayList<String>();

    when(accountCreator.create(account, erros)).thenReturn(account);

    RegisterAccountResponse responseRegister = registerAccountActionHandler.handle(new RegisterAccountAction(account));

    assertThat(responseRegister, is(notNullValue()));

    Account responseAccount = responseRegister.getAccount();

    assertThat(responseAccount, is(notNullValue()));

    assertThat(responseAccount.getEmail(), is(equalTo("mail@mail.com")));

    verify(accountCreator).create(eq(account), any(List.class));

    assertThat(responseRegister.getErrors().size(), is(0));
  }
     //TODO:do i need to test what is going to happen in each test scenario again ?? like invalid email form, short password .... ect ... ?


}
