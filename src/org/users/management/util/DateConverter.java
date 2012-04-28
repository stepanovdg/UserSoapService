package org.users.management.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.users.management.constants.LocaleArgs;
import org.users.management.model.User;

/**
 * Converts date to ru <-> en locales
 * 
 * @author Dzmitry_Stsiapanau
 * 
 */
public final class DateConverter {
	private DateConverter() {

	}

	public static User convertDate(User user, String simpleDate, Locale locale)
			throws ParseException {
		if (simpleDate != null) {
			DateFormat dateFormat = DateFormat.getDateInstance(
					DateFormat.SHORT, locale);
			String currentSimpleDate = dateFormat.format(user.getDate())
					.toString();
			if (simpleDate.equals(currentSimpleDate)) {
				user.setDate(new Date());
				return user;
			}
			if (locale.getLanguage().toString()
					.equals(Locale.ENGLISH.toString())) {
				SimpleDateFormat df = new SimpleDateFormat(
						LocaleArgs.EN_TIME_FORMAT);

				Date date = df.parse(simpleDate);
				user.setDate(date);

			}
			if (locale.getLanguage().toString()
					.equals(LocaleArgs.RU_LANG_PARAM)) {
				SimpleDateFormat df = new SimpleDateFormat(
						LocaleArgs.RU_TIME_FORMAT);
				Date date = df.parse(simpleDate);
				user.setDate(date);

			}
		}
		return user;

	}

	public static String simpleDate(Date date, Locale locale) {
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, locale);
		String simpleDate = df.format(date).toString();
		return simpleDate;

	}
}
