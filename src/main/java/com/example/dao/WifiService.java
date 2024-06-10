package com.example.dao;

import com.example.dto.WifiDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class WifiService  extends DBconnect {


    public void insert(WifiDTO wifi) {


        try {
            String sql = " INSERT INTO WifiInfo "
                    +  " VALUES "
                    +  " ( "
                    +  " ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?"
                    +  " ) ";

            dbConnection();
            prepared = prepared(sql);

            prepared.setString(1, wifi.getX_SWIFI_MGR_NO());
            prepared.setString(2, wifi.getX_SWIFI_WRDOFC());
            prepared.setString(3, wifi.getX_SWIFI_MAIN_NM());
            prepared.setString(4, wifi.getX_SWIFI_ADRES1());
            prepared.setString(5, wifi.getX_SWIFI_ADRES2());
            prepared.setString(6, wifi.getX_SWIFI_INSTL_FLOOR());
            prepared.setString(7, wifi.getX_SWIFI_INSTL_TY());
            prepared.setString(8, wifi.getX_SWIFI_INSTL_MBY());
            prepared.setString(9, wifi.getX_SWIFI_SVC_SE());
            prepared.setString(10, wifi.getX_SWIFI_CMCWR());
            prepared.setString(11, wifi.getX_SWIFI_CNSTC_YEAR());
            prepared.setString(12, wifi.getX_SWIFI_INOUT_DOOR());
            prepared.setString(13, wifi.getX_SWIFI_REMARS3());
            prepared.setDouble(14, wifi.getLNT());
            prepared.setDouble(15, wifi.getLAT());
            prepared.setString(16, wifi.getWORK_DTTM());

            prepared.executeUpdate();
        } catch (Exception e) {
            System.out.println("save fail");
            rollback();
        } finally {
            connectionClose();
        }


    }


    public List<WifiDTO> nearTwenty(double x, double y) throws SQLException {

        List<WifiDTO> list = new ArrayList<>();

        try {
            String sql = " SELECT *, "
                    + " round(6371*acos(cos(radians(?))*cos(radians(LAT))*cos(radians(LNT)-radians(?))+sin(radians(?))*sin(radians(LAT))),4) as DISTANCE"
                    + " FROM WifiInfo "
                    + " ORDER BY DISTANCE ASC "
                    + " LIMIT 20 ";

            dbConnection();
            prepared = prepared(sql);
            prepared.setDouble(1, x);
            prepared.setDouble(2, y);
            prepared.setDouble(3, x);
            resultSet = prepared.executeQuery();


            while (resultSet.next()) {
                WifiDTO wifi = new WifiDTO();
                wifi.setDistance(resultSet.getDouble("distance"));
                wifi.setX_SWIFI_MGR_NO(resultSet.getString("X_SWIFI_MGR_NO"));
                wifi.setX_SWIFI_WRDOFC(resultSet.getString("X_SWIFI_WRDOFC"));
                wifi.setX_SWIFI_MAIN_NM(resultSet.getString("X_SWIFI_MAIN_NM"));
                wifi.setX_SWIFI_ADRES1(resultSet.getString("X_SWIFI_ADRES1"));
                wifi.setX_SWIFI_ADRES2(resultSet.getString("X_SWIFI_ADRES2"));
                wifi.setX_SWIFI_INSTL_FLOOR(resultSet.getString("X_SWIFI_INSTL_FLOOR"));
                wifi.setX_SWIFI_INSTL_TY(resultSet.getString("X_SWIFI_INSTL_TY"));
                wifi.setX_SWIFI_INSTL_MBY(resultSet.getString("X_SWIFI_INSTL_MBY"));
                wifi.setX_SWIFI_SVC_SE(resultSet.getString("X_SWIFI_SVC_SE"));
                wifi.setX_SWIFI_CMCWR(resultSet.getString("X_SWIFI_CMCWR"));
                wifi.setX_SWIFI_CNSTC_YEAR(resultSet.getString("X_SWIFI_CNSTC_YEAR"));
                wifi.setX_SWIFI_INOUT_DOOR(resultSet.getString("X_SWIFI_INOUT_DOOR"));
                wifi.setX_SWIFI_REMARS3(resultSet.getString("X_SWIFI_REMARS3"));
                wifi.setLAT(resultSet.getDouble("LAT"));
                wifi.setLNT(resultSet.getDouble("LNT"));
                wifi.setWORK_DTTM(resultSet.getString("WORK_DTTM"));

                list.add(wifi);
            }
        }catch (Exception e) {
            System.out.println("nearBy fail");
            rollback();
        } finally {
            connectionClose();
        }






        return list;

    }

}
