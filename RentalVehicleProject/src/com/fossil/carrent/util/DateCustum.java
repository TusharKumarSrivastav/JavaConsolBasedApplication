package com.fossil.carrent.util;
/**
 * This class is use Date Related operation .
 * Count numbers of days between two dates
 * @author Tushar
 *
 */
public class DateCustum {           //DateCustum class for using numbers of the days between two dates
  int dd;
  int mm;
  int yy;
  int month[]= {0,31,28,31,30,31,30,31,31,30,31,30,31};
  public DateCustum(int dd,int mm,int yy) {
	  this.dd=dd;
	  this.mm=mm;
	  this.yy=yy;
	  if(yy%4==0&&yy%100!=0||yy%400==0) 
		  month[2]=29;
	  
  }
  /**
   * This is method use to count numbers of days between two dates
   * @return Number of Days Between two dates
   */
public int nosOfDays() {
	  int y=yy-1;
	  int days=y*365+y/4-y/100+y/400;
	  for(int i=0;i<mm;i++) 
		  days=days+month[i];
	  days=days+dd;
	  return days;
  }
}
