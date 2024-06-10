package com.example.dao;

import java.sql.*;

public abstract class DBconnect {
	protected Connection conn = null;
	protected Statement state = null;
	protected PreparedStatement prepared = null;
	protected ResultSet resultSet = null;
	private final String DRIVER = "org.sqlite.JDBC";
	private final String DB = "jdbc:sqlite:D:\\제로베이스\\데이터베이스\\sqlitedb\\wifidb.db";

	public DBconnect() {
		dbConnection();
	}


	public Connection dbConnection() {
		if (conn == null) {
			try {
				Class.forName(this.DRIVER);
				conn = DriverManager.getConnection(this.DB);
				System.out.println("SQLite DB connected Success");
			} catch (Exception e) {
				System.out.println("SQLite DB connected Fail");
			}
		}
		return conn;
	}


	public PreparedStatement prepared(String sql) {
		try {
			prepared = conn.prepareStatement(sql);
			System.out.println("PrepareStatement Create Success");
		} catch (SQLException e) {
			System.out.println("PrepareStatement Create Fail" + e);
		}
		return prepared;
	}


	public Statement state() {
		try {
			state = conn.createStatement();
			System.out.println("Statement Create Success");
		} catch (SQLException e) {
			System.out.println("Statement Create Fail" + e);
		}
		return state;
	}

	//crud 실패시 롤백
	public void rollback() {
		if (conn != null) {
			try {
				conn.rollback();
				System.out.println("Rollback Success");
			} catch (SQLException e) {
				System.out.println("Rollback Fail");
			}
		}
	}

	//SQLite 연결 해제
	public void connectionClose() {
		try {
			if (resultSet != null) {
				resultSet.close();
				resultSet = null;
			}

			if (prepared != null) {
				prepared.close();
				prepared = null;
			}

			if (state != null) {
				state.close();
				state = null;
			}

			if (conn != null) {
				conn.close();
				conn = null;
			}

			System.out.println("SQLite DB connectionClose Success");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQLite DB connectionClose Fail");
		}
	}
}	