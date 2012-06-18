package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.shared.Actions.FetchMonthsAction;
import com.clouway.exreport.shared.Reponses.FetchMonthsResponse;
import com.clouway.exreport.shared.entites.Month;
import org.junit.Test;

import java.util.ArrayList;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsEqual.equalTo;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchMonthsActionHandlerTest extends ExpensesActionHandlerTest {

  //TODO:All the responses must have a list indicating errors .... !

  @Test
  public void fetchesMonthsAndReturnsResponseContainsTheFetchedDays() {

    int year = 2012;

    int month = 03;

    final Month returnedMonth = new Month(year, month);

    when(service.getMonths(year)).thenReturn(new ArrayList<Month>() {{
      add(returnedMonth);
    }});
    FetchMonthsActionHandler handler = new FetchMonthsActionHandler(service);

    FetchMonthsResponse response = handler.handle(new FetchMonthsAction(year));

    assertThat(response, is(notNullValue()));

    assertThat(response.getMonths(), is(notNullValue()));

    assertThat(response.getMonths().get(0), is(notNullValue()));

    assertThat(response.getMonths().get(0).getMonth(), is(equalTo(month)));

    assertThat(response.getMonths().get(0).getYear(), is(equalTo(year)));

    verify(service).getMonths(year);
  }


}
