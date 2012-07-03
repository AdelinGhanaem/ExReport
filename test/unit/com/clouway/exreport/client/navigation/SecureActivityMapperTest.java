package com.clouway.exreport.client.navigation;

import com.clouway.exreport.client.navigation.activities.AuthenticationActivity;
import com.clouway.exreport.client.navigation.places.AuthenticationPlace;
import com.clouway.exreport.shared.SecurityTokenProvider;
import com.clouway.exreport.shared.entites.Token;
import com.google.gwt.activity.shared.Activity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class SecureActivityMapperTest {


  @Mock
  private SecurityTokenProvider provider;

  private SecureActivityMapper secureActivityMapper;

  @Before
  public void setUp() throws Exception {

    initMocks(this);

    List<Class> classes = new ArrayList<Class>();

    classes.add(SecurePlace.class);

    Map<Class<? extends Place>, Activity> classActivityMap = new HashMap<Class<? extends Place>, Activity>();

    classActivityMap.put(SecurePlace.class, new SecureActivity());

    classActivityMap.put(TestPlace.class, new TestActivity());

    classActivityMap.put(AuthenticationPlace.class,new AuthenticationActivity());

    secureActivityMapper = new SecureActivityMapper(classActivityMap, classes, provider);
  }

  @Test
  public void shouldReturnActivity() {

    Activity testActivity = secureActivityMapper.getActivity(new TestPlace());

    assertThat(testActivity, is(notNullValue()));

    assertEquals(testActivity.getClass(), TestActivity.class);

  }


  @Test
  public void shouldCheckForSecurityTokenWhenPlaceIsSecurePlace() {

    when(provider.getToken()).thenReturn(new Token("username"));

    Activity activity = secureActivityMapper.getActivity(new SecurePlace());

    verify(provider).getToken();

    assertThat(activity, is(notNullValue()));

    assertEquals(activity.getClass(), SecureActivity.class);

  }


  @Test
  public void shouldNotCheckForSecurityTokenWhenPlaceIsNotSecured() {

    when(provider.getToken()).thenReturn(new Token("username"));

    Activity activity = secureActivityMapper.getActivity(new TestPlace());

    verify(provider, never()).getToken();

    assertThat(activity, is(notNullValue()));


  }


  @Test
  public void returnAuthenticationActivityWhenPlaceIsSecuredAndTokenIsNotSet() {

    when(provider.getToken()).thenReturn(null);

    Activity activity = secureActivityMapper.getActivity(new SecurePlace());

    assertThat(activity, is(notNullValue()));

    assertEquals(activity.getClass(), AuthenticationActivity.class);
  }


  private class SecurePlace extends Place {

  }


  public class TestPlace extends Place {

  }

  public class TestActivity implements Activity {

    @Override
    public String mayStop() {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCancel() {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStop() {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }

  public class SecureActivity implements Activity {

    @Override
    public String mayStop() {
      return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onCancel() {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onStop() {
      //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
      //To change body of implemented methods use File | Settings | File Templates.
    }
  }


}
