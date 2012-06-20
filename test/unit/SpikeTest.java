import com.evo.gad.shared.Action;
import org.junit.Test;

import static junit.framework.Assert.assertTrue;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SpikeTest {


  @Test
  public void testInstanceOfReturnsTrueWhenActionIsInstanceOfSecurityAction() {
    EditExpenseAction action = new EditExpenseAction(new SecurityToken("12"));

    assertTrue((action instanceof Action));

    System.out.println(action.getClass());

    Class<? extends Action> aClass = action.getClass();

//    assertThat(aClass, is(equalTo(actionClass.getClass())));

  }

  private class SecurityAction implements Action {
    private SecurityToken token;

    public void setToken(SecurityToken token) {
      this.token = token;
    }
  }


  public class EditExpenseAction extends SecurityAction {

    public EditExpenseAction(SecurityToken token) {

    }
  }


  private class SecurityToken {

    private String securityToken;

    private SecurityToken(String securityToken) {
      this.securityToken = securityToken;
    }

    public String getSecurityToken() {
      return securityToken;
    }
  }
}
