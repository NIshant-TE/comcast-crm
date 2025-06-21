package com.comcast.crm.generic.fwebdriverutility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility {

public int getRandomNumber() {

Random random = new Random();
int randomNumber = random.nextInt(5000);
return randomNumber;
}

public String getSystemDateYYYYDDMM() {

Date dateObj = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
String date = sdf.format(dateObj);
System.out.println(date);
return date;
}

public String getRequiredDateYYYYDDMM(int days) {
Date dateObj = new Date();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
sdf.format(dateObj);

Calendar cal = sdf.getCalendar();
cal.add(Calendar.DAY_OF_MONTH, 30);
String reqDate = sdf.format(cal.getTime());
System.out.println("R:"+reqDate);
return reqDate;

}
}
