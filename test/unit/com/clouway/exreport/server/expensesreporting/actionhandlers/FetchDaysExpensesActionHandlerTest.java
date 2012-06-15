package com.clouway.exreport.server.expensesreporting.actionhandlers;

import com.clouway.exreport.shared.Day;
import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Adelin Ghanayem adelin.ghanaem@clouway.com
 */
public class FetchDaysExpensesActionHandlerTest extends ExpensesActionHandlerTest {


  @Test
  public void fetchesDaysAndReturnsResponseContainingTheFetchedDays() throws ParseException {
    //TODO:How to get all the days of a month ..... !

    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");

    int year = 2012;

    int moth = 3;

    int day = 1;

    List<Day> expensesDays = new ArrayList<Day>();
    expensesDays.add(new Day(day, moth, year));


    Date first = simpleDateFormat.parse(year + "-" + moth + "-" + 1);

    Date lastDate = simpleDateFormat.parse(year + "-" + moth + "-" + 1);

    FetchDaysActionHandler fetchDaysActionHandler = new FetchDaysActionHandler();

//    when(service.ge(first, lastDate)).
//
//    FetchDaysResponse response = fetchDaysActionHandler.handle(new FetchDaysAction(2001, 3));
//    assertThat(response, is(notNullValue()));
//    assertThat(response.getDays(), is(notNullValue()));
//    assertThat(response.getDays().get(0), is(notNull()));
//    assertThat(response.getDays().get(0).getDay(), is(day));
//    assertThat(response.getDays().get(0).getMonth(), is(moth));
//    assertThat(response.getDays().get(0).getYear(), is(year));
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
