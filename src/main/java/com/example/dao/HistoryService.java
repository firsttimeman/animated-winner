package com.example.dao;


import com.example.dto.HistoryDTO;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HistoryService extends DBconnect {

    public List<HistoryDTO> loadHistory() throws SQLException {

        List<HistoryDTO> list = new ArrayList<>();

        String sql = " SELECT * FROM history ";
        dbConnection();
        state();

        resultSet = state.executeQuery(sql);

        while (resultSet.next()) {
            HistoryDTO data = new HistoryDTO();
            data.setID(resultSet.getInt("ID"));
            data.setLAT(resultSet.getDouble("LAT"));
            data.setLNT(resultSet.getDouble("LNT"));
            data.setHISTORY_DATE(resultSet.getString("HISTORY_DATE"));

            list.add(data);
        }

        connectionClose();
        return list;
    }


    public void insertHistory(HistoryDTO data) throws SQLException {

         String sql = " INSERT INTO history (LAT, LNT, HISTORY_DATE) "
                +  " VALUES (?, ?, ?) ";

        dbConnection();
        prepared = prepared(sql);

        prepared.setDouble(1, data.getLAT());
        prepared.setDouble(2, data.getLNT());
        prepared.setString(3, LocalDateTime.now().toString());

        prepared.executeUpdate();

    }


    public void deleteHistory(String id) throws SQLException {

             String sql = " DELETE FROM history "
                    +  " WHERE HISTORY_ID = " + id;

            dbConnection();
            prepared = prepared(sql);
            prepared.executeUpdate();


            connectionClose();


    }


}
