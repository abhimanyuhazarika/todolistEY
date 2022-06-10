package com.ey.todolist.controller.util;
import java.util.Date;
import java.util.List;

import com.joestelmach.natty.*;

public class NaturalLanguageParser {

	public static Date parseStringAsDate(String userText) {
		Parser parser = new Parser();
		List<DateGroup> groups = parser.parse(userText);
		List<Date> dates = null;
		for(DateGroup group:groups) {
		  dates = group.getDates();
		}
		return dates==null? null:dates.get(0);
	}

}
