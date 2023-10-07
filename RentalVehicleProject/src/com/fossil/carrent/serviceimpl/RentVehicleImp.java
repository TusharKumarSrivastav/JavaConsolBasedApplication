package com.fossil.carrent.serviceimpl;
import java.io.IOException;

import java.sql.*;
import java.util.Scanner;

import com.fossil.carrent.daoimpl.DataBaseImp;

import com.fossil.carrent.util.DateCustum;
import com.fossil.carrent.util.DateNotValidException;
import com.fossil.carrent.util.Validation;
import com.fossil.carrental.service.RentVehicle;
/**
 * This class is used for Rent the Vehicle
 * @author Tushar
 *
 */
public class RentVehicleImp implements RentVehicle {
	/**
	 * This method is used to rent the vehicle
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void rentVehicle() throws SQLException, IOException {
		Scanner sc = new Scanner(System.in); 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		System.out.println("RENT VEHICLE");
		System.out.println("Enter the FIRSTNAME");
		String firstname=sc.next();
		String middlename=null;
		System.out.println("You want middle name Y/N");
		char userresponse=sc.next().charAt(0);                      //character enter by keyboard
		if(userresponse=='y'||userresponse=='Y') {
			System.out.println("Enter the MiddleName");
			middlename=sc.next();	
		}


		System.out.println("Enter the Last name");
		String lastname=sc.next();
		boolean checkemail=true;     //taking variable for checking email validation 
		String emailid=null;         //Take emailid as a variable
		while(checkemail) {
			System.out.println("Enter the Email Id");
			emailid=sc.next();
			if(Validation.emailValidation(emailid))     //call emailValidation Method to check validation of email
				checkemail=false;
			else {
				System.err.println("your emailid is invalid plz again");
				checkemail=true;
			}
		}

		boolean checkmobile =true;
		long phonenumber=0;

		while(checkmobile) {
			System.out.println("Enter the MobileNumber");
			phonenumber=sc.nextLong();
			if(Validation.mobileLength(phonenumber))
				checkmobile=false;
			else
				System.err.println("Mobile Number is invalid Plz again");
		}

		System.out.println("Enter the booking from date  (dd mm yyyy)");
		 int d1=sc.nextInt();
		 int m1=sc.nextInt();
		 int y1=sc.nextInt();
		if(!Validation.dateValidation(d1,m1,y1)){       //Calling the dateValidation method  for checking date valid or not
			throw new DateNotValidException();          // throw the DateNotValidException with the help of custom exception
		}
		System.out.println("Enter the booking till date  (dd mm yyyy)");
		int d2=sc.nextInt();
		int m2=sc.nextInt();
		int y2=sc.nextInt();
		if(!Validation.dateValidation(d2,m2,y2)){         //Calling the dateValidation method  for checking date valid or not
			throw new DateNotValidException();            // throw the DateNotValidException with the help of custom exception
		}

		String date1=y1+"-"+m1+"-"+d1;                  //convert date  integer from to string format 
		String date2=y2+"-"+m2+"-"+d2;

		System.out.println("RENT Vehicle option list ");    //here fetch unbooking vehicle at give date
		String qry1="select VehicleCategory,FuelType,v.RegistrationNumber,Milage,DailyRent from btm.vehicle v left join btm.rentvehicle rv on  v.RegistrationNumber=rv.RegistrationNumber where rv.firstname is null ";
		pstmt=DataBaseImp.getConnectDatabase(qry1);
		rs=pstmt.executeQuery();
		while(rs.next()) {
			System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4)+"  "+rs.getFloat(5));
			String qry2="select VehicleCategory,FuelType,v.RegistrationNumber,Milage,DailyRent from btm.vehicle v inner join btm.rentvehicle rv on v.RegistrationNumber=rv.RegistrationNumber and  (fromdate>? and fromdate>?) or (tilldate<? and tilldate<?)";
			pstmt=DataBaseImp.getConnectDatabase(qry2);
			pstmt.setDate(1,Date.valueOf(date1));
			pstmt.setDate(2,Date.valueOf(date2));
			pstmt.setDate(3,Date.valueOf(date1));
			pstmt.setDate(4,Date.valueOf(date2));
			rs=pstmt.executeQuery();
			while(rs.next()) {
				System.out.println(rs.getString(1)+"  "+rs.getString(2)+"  "+rs.getString(3)+"  "+rs.getFloat(4)+"  "+rs.getFloat(5));
			}




			System.out.println("YOU CAN SELECT ON BASIS OF REGISTRATION NUMBER");
			System.out.println("Enter the Registration Number of vehicle ");
			String registrationnumber=sc.next();
			DateCustum dt1=new DateCustum(d1,m1,y1);   
			DateCustum dt2=new DateCustum(d2,m2,y2);
			int days=dt2.nosOfDays()-dt1.nosOfDays();             // find out the nos of the days between two dates
			String qry3="select DailyRent from btm.vehicle where RegistrationNumber=?";
			String qry4="insert into  btm.rentvehicle values(?,?,?,?,?,?,?,?,?)";
			pstmt=DataBaseImp.getConnectDatabase(qry3);
			pstmt.setString(1,registrationnumber);
			rs=pstmt.executeQuery();
			if(rs.next()) {
				int rent=rs.getInt(1);        //Fetch daily rent that perticular given registration number
				int totalrent=rent*(days+1);
				System.out.println("Totel Rent : "+totalrent);
				pstmt=DataBaseImp.getConnectDatabase(qry4);
				pstmt.setString(1,firstname);
				pstmt.setString(2,middlename);	
				pstmt.setString(3,lastname);
				pstmt.setString(4,emailid);
				pstmt.setLong(5,phonenumber);
				pstmt.setString(6,registrationnumber);

				String sdate1=y1+"-"+m1+"-"+d1;
				String sdate2=y2+"-"+m2+"-"+d2;
				pstmt.setDate(7,Date.valueOf(sdate1));
				pstmt.setDate(8,Date.valueOf(sdate2));
				pstmt.setFloat(9,totalrent);
				pstmt.executeUpdate();
				System.out.println("SuccessFully Rented!!!");
			}

		}
	}
}

