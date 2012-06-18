package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.shared.Actions.FetchDaysAction;
import com.clouway.exreport.shared.entites.Day;
import com.clouway.exreport.shared.Reponses.FetchDaysResponse;
import org.junit.Test;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchDaysExpensesActionHandlerTest extends ExpensesActionHandlerTest {


  @Test
  public void fetches1DaysAndReturnsResponseContainingTheFetchedDays() throws ParseException {

    //TODO:How to get all the days of a month ..... !

    int year = 2012;

    int moth = 3;

    int day = 1;

    List<Day> expensesDays = new ArrayList<Day>();

    expensesDays.add(new Day(day, moth, year));

    FetchDaysActionHandler fetchDaysActionHandler = new FetchDaysActionHandler(service);

    when(service.getDays(year, moth)).thenReturn(expensesDays);

    FetchDaysResponse response = fetchDaysActionHandler.handle(new FetchDaysAction(year, moth));

    assertThat(response, is(notNullValue()));

    assertThat(response.getDays(), is(notNullValue()));

    assertThat(response.getDays().get(0), is(notNullValue()));

    assertThat(response.getDays().get(0).getDay(), is(day));

    assertThat(response.getDays().get(0).getMonth(), is(moth));

    assertThat(response.getDays().get(0).getYear(), is(year));

    //    Calendar calendar = new GregorianCalendar();
//    calendar.set(year, Calendar.JANUARY, 1);

//    DateTime march = new DateTime(year, 3, 1, 12, 0, 0, 000);

//    DateTime february = new DateTime(year, 2, 1, 12, 0, 0, 000);
//
//    int daysOfMonth = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
//    int febDays = february.dayOfMonth().getMaximumValue();

//    assertThat(String.valueOf(daysOfMonth),daysOfMonth, is(30));
//
//    assertThat(String.valueOf(febDays),daysOfMonth, is(28));
//    DateTime

//    Date date = simpleDateFormat.parse(year+"-"+moth+"-")

//    List<Day> dayList = new ArrayList<Day>();

//    when(

  }

}
