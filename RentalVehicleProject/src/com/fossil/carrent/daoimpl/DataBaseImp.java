package com.fossil.carrent.daoimpl;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.fossil.carrent.dao.DataBase;
import com.mysql.*;
/**
 * This class is responsible to database connectivity
 * @author Tushar
 *
 */
public class DataBaseImp implements DataBase {
	/**
	 * This method is use for connectivity to database server  
	 * @param SQL Query
	 * @return PrepareStatement Interface
	 * @throws IOException
	 */
	public static PreparedStatement getConnectDatabase(String qry) throws IOException {
		Connection con=null;
		PreparedStatement pstmt=null;
		try {
			String filepath="./src/config.properties";
			Properties p=new Properties();
			FileInputStream fis=new FileInputStream(filepath);
			p.load(fis);
			Class.forName(p.getProperty("fqcn"));
			con=DriverManager.getConnection(p.getProperty("url"),p.getProperty("username"),p.getProperty("passwoard"));
			pstmt=con.prepareStatement(qry);
			
		} catch (ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return pstmt;
	}
	
	
}
