package com.fossil.carrent.serviceimpl;
import java.io.*;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

import com.fossil.carrent.util.EncapsulatedClass;

import com.fossil.carrent.daoimpl.DataBaseImp;
import com.fossil.carrental.service.AddVehicle;
/**
 * 
 * @author Tushar
 *This class is responsible to add vehicle
 */
public class AddVehicleImp implements AddVehicle {
	//Add Vehicle Method For adding vehicle 
	/**
	 * This method is use to add the vehicle .
	 * This method called by Main Classes
	 * @throws IOException
	 * @throws SQLException
	 */
	public static void addVehicle() throws IOException, SQLException  {
		Scanner sc = new Scanner(System.in); 
		PreparedStatement pstmt=null;
		int batchSize=20;
		String qry="insert into btm.vehicle values(?,?,?,?,?)";
		System.out.println("ADD VEHICLE");

		com.fossil.carrent.util.EncapsulatedClass e=new com.fossil.carrent.util.EncapsulatedClass();
		//Adding vehicle by csv file
		boolean whileloop=true;
		while(whileloop) {
			try {
			System.out.println("Select option"+"\n"+"1.You want to insert data from CSV file"+"\n"+"2. You want to insert individual data");
			String userresponse=sc.next();
			int userresponse1=0;
			try {
				 userresponse1=Integer.parseInt(userresponse)	;
			}
			catch(Exception e1) {
				System.out.println("Invalid input plz input again");
			}
			if(userresponse1==1) {
				System.out.println("Enter the file path");
				String filepath=sc.next();

				pstmt=DataBaseImp.getConnectDatabase(qry);
				BufferedReader lineReader=new BufferedReader(new FileReader(filepath));
				String lineText=null;
				int count=0;
				lineReader.readLine();
				while((lineText=lineReader.readLine())!=null) {
					String data[]=lineText.split(",");
					e.setVehiclecategory(data[0]);
					e.setFueltype(data[1]);
					e.setRegistrationnumber(data[2]);
					e.setMilage(Float.parseFloat(data[3]));
					e.setDailyrent(Float.parseFloat(data[4]));
					pstmt.setString(1,e.getVehiclecategory()); 
					pstmt.setString(2,e.getFueltype());
					pstmt.setString(3,e.getRegistrationnumber() );
					pstmt.setFloat(4,e.getMilage());
					pstmt.setFloat(5,e.getDailyrent());
					pstmt.executeUpdate();
					if(count%batchSize==0)
						pstmt.executeBatch();
				}
				lineReader.close();
				pstmt.executeBatch();

				System.out.println("Data has been successfully inserted");
				whileloop=false;


			}
			// adding vehicle by individual one by one vehicle
			else if(userresponse1==2) {
				boolean checkresponse=true;
				while(checkresponse) {
					System.out.println("Vehicle catagary \n1. SUV \n2. SEDAN ");
					int response1=sc.nextInt();
					if(response1==1) {
						e.setVehiclecategory("SUV");
						checkresponse=false;
					}
					else if(response1==2) {
						e.setVehiclecategory("SEDAN");
						checkresponse=false;
					}
					else
						System.err.println("Response is not matched plz enter the again ");
				}
				
				boolean whileloop1=true;
				while(whileloop1) {
					System.out.println("Fuel Type \n1.Petrol \n2.Deisel\n3.EV");
					int response2=sc.nextInt();
					if(response2==1) {
						e.setFueltype("Petrol");
						whileloop1=false;;
					}
					else if(response2==2) {
						e.setFueltype("Diesel");
						whileloop1=false;;
					}
					else if(response2==3) {
						e.setFueltype("EV");
						whileloop1=false;;
					}
					else
						System.err.println("Response is not matched plz enter again!!!");
				}


				System.out.println("Registartion Nmber");
				e.setRegistrationnumber(sc.next());
				System.out.println("Milage in Km/hour");
				e.setMilage(sc.nextFloat());
				System.out.println("Daily Rent Charge in Rs");
				e.setDailyrent(sc.nextFloat());
				pstmt= DataBaseImp.getConnectDatabase(qry);
				pstmt.setString(1,e.getVehiclecategory());
				pstmt.setString(2,e.getFueltype());
				pstmt.setString(3,e.getRegistrationnumber());
				pstmt.setFloat(4,e.getMilage());
				pstmt.setFloat(5,e.getDailyrent());
				pstmt.executeUpdate();
				System.out.println("Succssesfully Add Vehicle");
				whileloop=false;

			}
			else {
				System.err.println("Your response is not matched plz enter again");
			}
			}
			catch(InputMismatchException e2) {
				System.out.println("Enter the valid input");
			}

		}
		pstmt.close();
	}

}
