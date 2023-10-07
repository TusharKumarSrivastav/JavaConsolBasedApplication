package com.fossil.carrent.util;

import org.junit.Test;

/**
 * This class is used for Checking different type of validation
 * @author user
 *
 */
public class Validation {      
	/**
	 * This is method is used for date validation
	 * @param user enter days
	 * @param user enter month
	 * @param user enter year
	 * @return true/False 
	 */

	public static boolean dateValidation(int d,int m,int y) {      
		if(d<1 || d>31 || m<1 || m>12 || y<1)
			return false;
		else if((m==4 ||m==6||m==9||m==11 )&& d>30)
			return false;
		else if(m==2 && d>29)
			return false;
		else if(m==2&&(y%400==0 || y%4==0 && y%100!=0)==false&&d>28)
			return false;
		else
			return true;
	}
	/**
	 * This method is used for validation of email
	 * @param emailid
	 * @return true/false
	 */
	
	 public static boolean emailValidation(String emailid) {                          //This method is using for checking the validation of Email Id
		boolean rs2= emailid.matches( "^[A-Za-z0-9+_.-]+@(.+)$");
		return rs2;
	}
	/**
	 * This method is used to check mobile number validation
	 * @param mobileNumber
	 * @return true/false
	 */
	public static boolean mobileLength(long mobileNumber) {
		
		int count=0;
		do {
			count++;
			mobileNumber=mobileNumber/10;
			
		}while(mobileNumber!=0);
		return count==10;
	}
	
}
