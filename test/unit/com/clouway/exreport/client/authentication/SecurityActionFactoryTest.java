package com.clouway.exreport.client.authentication;

import com.clouway.exreport.shared.entites.Token;
import com.evo.gad.shared.Action;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecurityActionFactoryTest {


  private class TestAction implements Action {

  }

  @Test
  public void createsSecurityActionContainsDesiredAction() {
//
//    TestAction action = new TestAction();
//
//    SecurityActionFactoryImpl securityActionFactory = new SecurityActionFactoryImpl(new SecurityTokenProvider() {
//      @Override
//      public Token getToken() {
//        return null;  //To change body of implemented methods use File | Settings | File Templates.
//      }
//
//      @Override
//      public void setToken(Token token) {
//        //To change body of implemented methods use File | Settings | File Templates.
//      }
//    });
//
//    SecurityAction securityAction = securityActionFactory.createSecurityAction(new TestAction());
//
//
//    assertThat(securityAction, is(notNullValue()));
//
//
//    assertThat(securityAction.getAction(), is(notNullValue()));
//
//    assertThat((TestAction) securityAction.getAction(), is(action));

  }


}
