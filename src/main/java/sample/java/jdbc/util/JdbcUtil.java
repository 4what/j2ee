package sample.java.jdbc.util;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.*;

public class JdbcUtil {
	/**
	 * @return
	 */
	public static Connection getConnection() {
		// url

		// for MSSQL
		//String driver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
		//String url = "jdbc:sqlserver://localhost:1433;databaseName=db";
		//String username = "sa";
		//String password = "sa";

		// for MySQL
		String driver = "com.mysql.jdbc.Driver";
		String url = "jdbc:mysql://localhost:3306/db";
		String username = "root";
		String password = "";

		// for JUnit
		try {
			Class.forName(driver);
			return DriverManager.getConnection(url, username, password);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}


/*
		// jndi
		String name = "jdbc/ds";

		try {
			Context initialContext = new InitialContext();
			Context ctx = (Context) initialContext.lookup("java:comp/env");

			DataSource ds = (DataSource) ctx.lookup(name);
			return ds.getConnection();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (NamingException e) {
			e.printStackTrace();
		}
*/

		return null;
	}

	/**
	 * @param rs
	 */
	public static void close(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param stmt
	 */
	public static void close(Statement stmt) {
		if (stmt != null) {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param pstmt
	 */
	public static void close(PreparedStatement pstmt) {
		if (pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * @param conn
	 */
	public static void close(Connection conn) {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}
