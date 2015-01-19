package com.bmasmoudi.tutos.date;

import static org.fest.assertions.Assertions.assertThat;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;

import org.junit.Test;

public class DateFormattingTest {

	/** The locale where the tests are for. */
	private static final Locale locale = Locale.ENGLISH;

	@Test
	public void String_to_date_with_english_formatting_test() throws Exception {
		SimpleDateFormat formater = new SimpleDateFormat("MMMyyyy", locale);
		Date dateFromStr = formater.parse("Dec2010");
		assertThat(new GregorianCalendar(2010, Calendar.DECEMBER, 1).getTime()).isEqualTo(dateFromStr);
	}

	@Test
	public void String_to_date_with_english_formatting_test2() throws Exception {
		SimpleDateFormat formater = new SimpleDateFormat("ddMMMyyyy", locale);
		Date dateFromStr = formater.parse("01JAN2010");
		assertThat(new GregorianCalendar(2010, Calendar.JANUARY, 1).getTime()).isEqualTo(dateFromStr);
	}

	@Test
	public void date_to_String_with_english_formatting_test() {
		Date firstDecember = new GregorianCalendar(2010, Calendar.DECEMBER, 1).getTime();
		assertThat("Dec 2010").isEqualTo(new SimpleDateFormat("MMM yyyy", locale).format(firstDecember));
	}

}
