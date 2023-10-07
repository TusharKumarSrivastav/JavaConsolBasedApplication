package com.fossil.carrent.serviceimpl;
import java.io.IOException;
import java.sql.*;
import java.util.Scanner;

import com.fossil.carrent.daoimpl.DataBaseImp;
import com.fossil.carrent.dao.*;
import com.fossil.carrental.service.SearchVehicle;
/**
 * This class is used for Seach the vehicle
 * @author Tushar
 *
 */
public class SearchVehicleImp implements SearchVehicle {
	/**
	 * This method is used to search the vehicle .Which is present for Rent
	 * @throws SQLException
	 * @throws IOException
	 */
	public static void searchVehicle() throws SQLException, IOException {
		Scanner sc = new Scanner(System.in); 
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		System.out.println("Enter the Registration Number For Seraching");
		String rn=sc.next();
		String qry="select* from btm.vehicle where RegistrationNumber=?";
		pstmt=DataBaseImp.getConnectDatabase(qry);
		pstmt.setString(1,rn);
		rs=pstmt.executeQuery();
		if(rs.next()) {
			String cat=rs.getString(1);              //Retrieve the data  from buffer and cursor memory from data base
			String fueltype=rs.getString(2);
			String reg=rs.getString(3);
			float milage=rs.getFloat(4);
			float rent =rs.getFloat(5);
			System.out.println("Catagray :"+ cat+"\nFuel Type:"+ fueltype+"\nRegistraton Number:"+reg+"\nMilage:"+milage+"\nRentPerDay:"+rent);
		}
		else {
			System.out.println("This Registration Number Vehicle is not Register");
		}
        rs.close();
        pstmt.close();


	}

}
