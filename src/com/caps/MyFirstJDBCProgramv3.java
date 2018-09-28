package com.caps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class MyFirstJDBCProgramv3 {
	public static void main(String[] args) {
		

		Connection con = null;
		Statement stmt = null;
		ResultSet rs = null;		
		try {
			/*
			 * 1. Load the Driver
			 */
			Class.forName("com.mysql.jdbc.Driver");
			
			/*
			 * 2. Get the DB Connection via Driver
			 * 3rd version of getConnection
			 * */
			String dbUrl="jdbc:mysql://localhost:3306/capsv4_db";
			Scanner in = new Scanner(System.in);
			System.out.println("enter the username:");
						String user = in.nextLine();
	System.out.println("enter the password:");
						String passwd = in.nextLine();
			con = DriverManager.getConnection(dbUrl,user,passwd);
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "select * from students_info";

			stmt = con.createStatement();
			rs = stmt.executeQuery(sql);

			/*
			 * 4. Process the results
			 */

			while(rs.next()){
				int regno = rs.getInt("sid");
				String firstname = rs.getString("firstname");
				String lastname = rs.getString("lastname");
				String gender = rs.getString("gender");
				passwd = rs.getString("password");
				String type = rs.getString("type");

				System.out.println(regno);
				System.out.println(firstname);
				System.out.println(lastname);
				System.out.println(gender);
				System.out.println(passwd);
				System.out.println(type);
				System.out.println("--------");
			}
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}

}
