package com.fossil.carrent.controller;
import java.io.IOException;
import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import org.junit.Test;
import com.fossil.carrent.serviceimpl.AddVehicleImp;
import com.fossil.carrent.serviceimpl.RentReportImp;
import com.fossil.carrent.serviceimpl.RentVehicleImp;
import com.fossil.carrent.serviceimpl.SearchVehicleImp;
import com.fossil.carrental.service.AddVehicle;
import com.fossil.carrental.service.RentReport;
import com.fossil.carrental.service.RentVehicle;
import com.fossil.carrental.service.SearchVehicle;
/**
 * This class is main class.This class is responsible of HOME Page
 * @author Tushar
 * 
 */

public class Main {
	@Test  
	public static void main(String[] args) throws SQLException, IOException   {
		Scanner sc=new Scanner(System.in);
		Connection con=null;
		PreparedStatement pstmt=null;
		ResultSet rs=null;
		System.out.println("Choose the one option");
		boolean loop=true;
		while(loop) {
			
			System.out.println("1. Add Vehicle \n2. Search Page \n3. Rent Vehicle \n4. Report Rental Details \n5. Exit");
			
			String userresponse=sc.next();
			int n=0;
			try {
				n=Integer.parseInt(userresponse);
			}
			catch(Exception e){
				System.err.println("Input is not valid plz input again!!!\n");
			}
			finally {
				if(rs!=null)
					rs.close();
				if(pstmt!=null)
					pstmt.close();
				if(con!=null)
					con.close();
			}
			switch(n) {
			case 1:AddVehicleImp.addVehicle();
			break;
			case 2:SearchVehicleImp.searchVehicle();
			break;
			case 3:RentVehicleImp.rentVehicle();
			break;
			case 4:RentReportImp.rentReport();
			break;
			
			
			}
			if(n==5) {
				System.out.println("Successfully exit");
				break;
			}
			
		
		}
		
		}
	}