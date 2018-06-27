package com.modoo.cg.limit;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class minutes {
	
	SimpleDateFormat sf;
	Date today;

	
	public String getTime() {
		
		String time = "";
		
		long now = System.currentTimeMillis();
		
		today = new Date(now);
		
		today.setMinutes(today.getMinutes()+2);
		
		sf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		
		time = sf.format(today);
		
		return time;
	}
	
	public boolean equalsTime(String time) throws ParseException {
		
		today = new Date();
		
		long now = System.currentTimeMillis();
		
		Date today2 = new Date(now);
		
		
		sf = new SimpleDateFormat("yyyy-MM-dd a hh:mm:ss");
		
		
		String end =sf.format(today2);
		
		Date t1 = sf.parse(time);
		
		Date t2 = sf.parse(end);
		
		
		
		//t1이 t2보다 이후 일때 true, 아니면 false ;
		
		return t2.after(t1);
	}
	
}
