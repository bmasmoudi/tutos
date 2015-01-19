package com.bmasmoudi.tutos.date.joda;

import static org.fest.assertions.Assertions.assertThat;

import java.util.Calendar;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.junit.Test;

public class JodaTest {

	@Test
	public void testJoda() throws Exception {
		// date Actuelle
		DateTime dt = new DateTime();
		// date construite à partir d'une dtae java.util
		DateTime nowFromJavaUtil = new DateTime(Calendar.getInstance().getTime());
		// comprarison de dates avec Joda
		assertThat(nowFromJavaUtil.isAfter(dt)).isFalse();
		System.out.println(dt.getDayOfMonth());
	}

	@Test
	public void tes_difference_en_jours_entre_2_dates() throws Exception {
		DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy HH");
		DateTime start = formatter.parseDateTime("31/12/2014 17");
		DateTime end = formatter.parseDateTime("01/01/2015 01");
		assertThat(difference_en_jours_entre_2_dates(start, end)).isEqualTo(1);

	}

	public int difference_en_jours_entre_2_dates(final DateTime start, final DateTime end) {
		return Days.daysBetween(start.withTimeAtStartOfDay(), end.withTimeAtStartOfDay()).getDays();
	}

}
