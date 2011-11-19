package com.rais.manager;

import java.util.Calendar;

import com.rais.manager.controller.Polls;

/**
 * @author Donato Galo
 * 
 */
public class RaisManagerMain {

	private static Calendar currentDate;

	// --------------------------------------------------------------------------------

	private RaisManagerMain() {
		/* empty */
	}

	// --------------------------------------------------------------------------------

	/**
	 * The work of this function is close/open the polls every friday at 11:59pm
	 * 
	 */
	@SuppressWarnings("deprecation")
	public static void main(String[] args) {

		currentDate = Calendar.getInstance();

		if (currentDate.getTime().getDay() != Calendar.FRIDAY) {
			return;
		}

		Polls.closeNoAnsweredPolls(currentDate);
		Polls.createPolls(currentDate);

	}

	// --------------------------------------------------------------------------------

}
