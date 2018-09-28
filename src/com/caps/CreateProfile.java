package com.caps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class CreateProfile {
	public static void main(String[] args) {
		Connection con = null;
		PreparedStatement pstmt = null;

		try {
			/*
			 * 1. Load the Driver
			 */
			Class.forName("com.mysql.jdbc.Driver");

			/*
			 * 2. Get the DB Connection via Driver
			 */
			
			String dbUrl="jdbc:mysql://localhost:3306/capsv4_db";

			//3rd version of getConnnecton()
			con = DriverManager.getConnection(dbUrl,"root","root"); 

			System.out.println("Connected...");

			/*
			 * 3. Issue the SQL query via connection
			 */
			Scanner in = new Scanner(System.in);
			System.out.println("id:");
			int id = Integer.parseInt(in.nextLine());
			System.out.println("First Name: ");
			String fname= in.nextLine();
			System.out.println("LastName: ");
			String lname = in.nextLine();
			System.out.println("Gender: ");
			String gender = in.nextLine();
			System.out.println("Password: ");
			String password=in.nextLine();
			System.out.println("Type: ");
			String type = in.nextLine();
			String sql = "insert into students_info values(?,?,?,?,?,?)";
			System.out.println(sql);

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2,fname);
			pstmt.setString(3, lname);
			pstmt.setString(4, gender);
			pstmt.setString(5, password);
			pstmt.setString(6, type);
			
			int count = pstmt.executeUpdate();	
			
			/*
			 * 4. Process the results
			 */

			if(count > 0) {
				System.out.println("Data Inserted");
			}else {
				System.out.println("Failed");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of main

}