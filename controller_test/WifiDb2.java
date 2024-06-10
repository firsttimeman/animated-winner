package controller_test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import dto.WifiVari;

public class WifiDb {


	private static final String DB_URL = "jdbc:sqlite:wifidb.db";

	public WifiDb() {
        // 테이블이 존재하지 않으면 생성
        String sql = "CREATE TABLE IF NOT EXISTS wifi_info (" +
                "distance REAL, " +
                "X_SWIFI_MGR_NO TEXT, " +
                "X_SWIFI_WRDOFC TEXT, " +
                "X_SWIFI_MAIN_NM TEXT, " +
                "X_SWIFI_ADRES1 TEXT, " +
                "X_SWIFI_ADRES2 TEXT, " +
                "X_SWIFI_INSTL_FLOOR TEXT, " +
                "X_SWIFI_INSTL_TY TEXT, " +
                "X_SWIFI_INSTL_MBY TEXT, " +
                "X_SWIFI_SVC_SE TEXT, " +
                "X_SWIFI_CMCWR TEXT, " +
                "X_SWIFI_CNSTC_YEAR TEXT, " +
                "X_SWIFI_INOUT_DOOR TEXT, " +
                "X_SWIFI_REMARS3 TEXT, " +
                "LAT REAL, " +
                "LNT REAL, " +
                "WORK_DTTM TEXT" +
                ");";

        try (Connection conn = this.connect();
        	PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.execute();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

	  private Connection connect() {
	        Connection conn = null;
	        try {
	            conn = DriverManager.getConnection(DB_URL);
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	        return conn;
	    }


	  public void saveInfo(List<WifiVari> wifiList) {
	        String sql = "INSERT INTO wifi_info(distance, X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
	                "X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, " +
	                "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
	                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

	        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
	            for (WifiVari wifi : wifiList) {
	                pstmt.setDouble(1, wifi.getDistance());
	                pstmt.setString(2, wifi.getX_SWIFI_MGR_NO());
	                pstmt.setString(3, wifi.getX_SWIFI_WRDOFC());
	                pstmt.setString(4, wifi.getX_SWIFI_MAIN_NM());
	                pstmt.setString(5, wifi.getX_SWIFI_ADRES1());
	                pstmt.setString(6, wifi.getX_SWIFI_ADRES2());
	                pstmt.setString(7, wifi.getX_SWIFI_INSTL_FLOOR());
	                pstmt.setString(8, wifi.getX_SWIFI_INSTL_TY());
	                pstmt.setString(9, wifi.getX_SWIFI_INSTL_MBY());
	                pstmt.setString(10, wifi.getX_SWIFI_SVC_SE());
	                pstmt.setString(11, wifi.getX_SWIFI_CMCWR());
	                pstmt.setString(12, wifi.getX_SWIFI_CNSTC_YEAR());
	                pstmt.setString(13, wifi.getX_SWIFI_INOUT_DOOR());
	                pstmt.setString(14, wifi.getX_SWIFI_REMARS3());
	                pstmt.setDouble(15, wifi.getLAT());
	                pstmt.setDouble(16, wifi.getLNT());
	                pstmt.setString(17, wifi.getWORK_DTTM());
	                pstmt.addBatch();
	            }
	            pstmt.executeBatch();
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }
	    }


}
