package com.caps;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

/*
 	use capsv4_db;
 	
	delimiter &
	CREATE PROCEDURE getAllStudents()
		BEGIN
			SELECT * FROM students_info;
		END&
	delimiter ;
*/
public class StoredProcedureExample {
	public static void main(String[] args) {
		Connection con = null;
		CallableStatement cstmt = null;
		ResultSet rs = null;
		
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
			con = DriverManager.getConnection(dbUrl,"root","root"); //1st version of getConnection

			System.out.println("Connected...");
			
			/*
			 * 3. Issue the SQL query via connection
			 */
			String sql = "call getAllStudents()";

			cstmt = con.prepareCall(sql);
			boolean state = cstmt.execute();
			int coutn = 0;
			if(state) {
				rs = cstmt.getResultSet();
				while(rs.next()){
					int regno = rs.getInt("sid");
					String firstname = rs.getString("firstname");
					String lastname = rs.getString("lastname");
					String gender = rs.getString("gender");
					String passwd = rs.getString("password");
					String type = rs.getString("type");

					System.out.println(regno);
					System.out.println(firstname);
					System.out.println(lastname);
					System.out.println(gender);
					System.out.println(passwd);
					System.out.println(type);
					System.out.println("*********************");
				}
			}else {
				coutn = cstmt.getUpdateCount();
				if(coutn > 0) {
					System.out.println("success");
				}else {
					System.out.println("failed");
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}