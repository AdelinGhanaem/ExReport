package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.shared.actions.FetchYearsAction;
import com.clouway.exreport.shared.reponses.FetchYearsResponse;
import com.clouway.exreport.shared.entites.Year;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchYearsActionHandlerTest extends ExpensesActionHandlerTest {


  @Test
  public void fetchesAllYearsAndReturnsAResponseContainsTheFetchedYears() {
    final int year = 2012;

    List<Year> yearList = new ArrayList<Year>() {{
      add(new Year(year));
    }};

    FetchYearsActionHandler handler = new FetchYearsActionHandler(service);

    when(service.getDeclaredYears()).thenReturn(yearList);

    FetchYearsResponse response = handler.handle(new FetchYearsAction());

    assertThat(response, is(notNullValue()));

    assertThat(response.getYears(), is(notNullValue()));

    assertThat(response.getYears().get(0), is(notNullValue()));

    assertThat(response.getYears().get(0).getYear(), is(year));

    verify(service).getDeclaredYears();
  }
}
