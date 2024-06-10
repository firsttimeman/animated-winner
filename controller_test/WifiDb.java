package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import dto.WifiVari;

public class WifiDb {

    private static final String DB_URL = "jdbc:sqlite:D:\\제로베이스\\데이터베이스\\sqlitedb\\wifidb.db";

    public WifiDb() {

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

    public void insert(WifiVari wifi) {
        String sql = "INSERT INTO wifi_info(X_SWIFI_MGR_NO, X_SWIFI_WRDOFC, X_SWIFI_MAIN_NM, " +
                "X_SWIFI_ADRES1, X_SWIFI_ADRES2, X_SWIFI_INSTL_FLOOR, X_SWIFI_INSTL_TY, X_SWIFI_INSTL_MBY, " +
                "X_SWIFI_SVC_SE, X_SWIFI_CMCWR, X_SWIFI_CNSTC_YEAR, X_SWIFI_INOUT_DOOR, X_SWIFI_REMARS3, LAT, LNT, WORK_DTTM) " +
                "VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

        try (Connection conn = this.connect(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, wifi.getX_SWIFI_MGR_NO());
            pstmt.setString(2, wifi.getX_SWIFI_WRDOFC());
            pstmt.setString(3, wifi.getX_SWIFI_MAIN_NM());
            pstmt.setString(4, wifi.getX_SWIFI_ADRES1());
            pstmt.setString(5, wifi.getX_SWIFI_ADRES2());
            pstmt.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
            pstmt.setString(7, wifi.getX_SWIFI_INSTL_TY());
            pstmt.setString(8, wifi.getX_SWIFI_INSTL_MBY());
            pstmt.setString(9, wifi.getX_SWIFI_SVC_SE());
            pstmt.setString(10, wifi.getX_SWIFI_CMCWR());
            pstmt.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
            pstmt.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
            pstmt.setString(13, wifi.getX_SWIFI_REMARS3());
            pstmt.setDouble(14, wifi.getLAT());
            pstmt.setDouble(15, wifi.getLNT());
            pstmt.setString(16, wifi.getWORK_DTTM());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }




}
