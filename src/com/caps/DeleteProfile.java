package com.caps;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.Scanner;

public class DeleteProfile {
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
			System.out.println("Enter the password");
			String passwd = in.nextLine();

			String sql = "delete from students_info where sid=?  and password=?";
			System.out.println(sql);

			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, id);
			pstmt.setString(2, passwd );
			int count = pstmt.executeUpdate();
			
			/*
			 * 4. Process the results
			 */

			if(count > 0) {
				System.out.println("Data Deleted");
			}else {
				System.out.println("Not available");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}//end of main

}