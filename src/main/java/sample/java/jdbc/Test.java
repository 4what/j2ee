package sample.java.jdbc;

import sample.java.jdbc.domain.Pojo;
import sample.java.jdbc.util.JdbcUtil;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Test {
	private Connection connection = JdbcUtil.getConnection();

	private Statement stmt;
	private PreparedStatement pstmt;

	private ResultSet rs;

	@org.junit.Test
	public void create() {
		try {
			Pojo pojo = new Pojo();

			pojo.setDate(new Date());

			String sql = "INSERT INTO pojo (date) VALUES ";

			//connection.setAutoCommit(false);

/*
			// Statement
			stmt = connection.createStatement();

			stmt.executeUpdate(
				sql + "('"
					+ new Timestamp(pojo.getDate().getTime())
					+ "')",
				Statement.RETURN_GENERATED_KEYS
			);

			rs = stmt.getGeneratedKeys();
*/

			// PreparedStatement
			pstmt = connection.prepareStatement(
				sql + "(?)",
				Statement.RETURN_GENERATED_KEYS
			);

			pstmt.setTimestamp(1, new Timestamp(pojo.getDate().getTime()));

			pstmt.executeUpdate();

			rs = pstmt.getGeneratedKeys();

			// id
			if (rs.next()) {
				int id = rs.getInt(rs.getRow());

				pojo.setId(id);

				System.out.println("pojo: " + pojo);
			}

			//connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();

/*
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException ex) {
					ex.printStackTrace();
				}
			}
*/
		} finally {
/*
			try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}
*/

			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
			JdbcUtil.close(connection);
		}
	}

	@org.junit.Test
	public void list() {
		try {
			List<Pojo> list = new ArrayList<>();

			String sql = "SELECT * FROM pojo";

/*
			// Statement
			stmt = connection.createStatement();
			rs = stmt.executeQuery(sql);
*/

			// PreparedStatement
			pstmt = connection.prepareStatement(sql);
			rs = pstmt.executeQuery();

			while (rs.next()) {
				Pojo pojo = new Pojo();

				pojo.setId(rs.getInt("id"));
				pojo.setDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(rs.getString("date")));

				list.add(pojo);
			}

			System.out.println("list: " + list);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			JdbcUtil.close(rs);
			JdbcUtil.close(pstmt);
			JdbcUtil.close(stmt);
			JdbcUtil.close(connection);
		}
	}
}
