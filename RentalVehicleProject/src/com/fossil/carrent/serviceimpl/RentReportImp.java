package com.fossil.carrent.serviceimpl;
import java.io.IOException;

import java.sql.*;
import java.util.Scanner;

import com.fossil.carrent.daoimpl.DataBaseImp;
import com.fossil.carrent.daoimpl.DataBaseImp;
import com.fossil.carrental.service.RentReport;
/**
 * This class is used for generate the RentReport
 * @author Tushar
 *
 */
public class RentReportImp implements RentReport {
	/**
	 * This is method is use to generate the rent Report between two dates
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void rentReport() throws SQLException, IOException {
		Scanner sc = new Scanner(System.in); 
		ResultSet rs=null;
		System.out.println("Rent Report");
		System.out.println("enter the from date for rent report (dd mm yyyy)");
		int d1=sc.nextInt();
		int m1=sc.nextInt();
		int y1=sc.nextInt();
		System.out.println("enter the till date for rent report (dd mm yyyy)");
		int d2=sc.nextInt();
		int m2=sc.nextInt();
		int y2=sc.nextInt();
		String date1=y1+"-"+m1+"-"+d1;
		String date2=y2+"-"+m2+"-"+d2;
		String qry="select RegistrationNumber,totalrent from btm.rentvehicle where ?<=fromdate and ?>=tilldate";
	        PreparedStatement pstmt=DataBaseImp.getConnectDatabase(qry);
			pstmt.setDate(1,Date.valueOf(date1));     //set date for placeholder //string date covert to date datatype
			pstmt.setDate(2,Date.valueOf(date2));
			rs=pstmt.executeQuery();
			System.out.println("Rent Report at date :"+date1 +" to "+date2);
			while(rs.next()) {
				String registrationnumber=rs.getString(1);
				float totalrent=rs.getFloat(2);
				System.out.println(registrationnumber+"    "+totalrent);
			}
			
			
		
	}
}
