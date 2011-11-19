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

	public static void main(String[] args) {

		currentDate = Calendar.getInstance();

//		if (currentDate.getTime().getDay() != Calendar.SATURDAY) {
//			return;
//		}
//
//		Polls.closeNoAnsweredPolls(currentDate);
		Polls.createPolls(currentDate);

	}

	// --------------------------------------------------------------------------------

}
