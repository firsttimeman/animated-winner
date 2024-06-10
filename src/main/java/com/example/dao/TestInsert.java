package com.example.dao;

import com.example.dto.WifiDTO;

public class TestInsert {

    public static void main(String[] args) {
        WifiService wifiService = new WifiService();
        WifiDTO wifiDTO = new WifiDTO();
        wifiDTO.setX_SWIFI_MGR_NO("TEST001");
        wifiDTO.setX_SWIFI_WRDOFC("구");
        wifiDTO.setX_SWIFI_MAIN_NM("테스트와이파이");
        wifiDTO.setX_SWIFI_ADRES1("도로명주소");
        wifiDTO.setX_SWIFI_ADRES2("상세주소");
        wifiDTO.setX_SWIFI_INSTL_FLOOR("1층");
        wifiDTO.setX_SWIFI_INSTL_TY("유형");
        wifiDTO.setX_SWIFI_INSTL_MBY("설치기관");
        wifiDTO.setX_SWIFI_SVC_SE("서비스구분");
        wifiDTO.setX_SWIFI_CMCWR("망종류");
        wifiDTO.setX_SWIFI_CNSTC_YEAR("2023");
        wifiDTO.setX_SWIFI_INOUT_DOOR("실내");
        wifiDTO.setX_SWIFI_REMARS3("와이파이환경");
        wifiDTO.setLAT(37.5665);
        wifiDTO.setLNT(126.9780);
        wifiDTO.setWORK_DTTM("2023-01-01");

        wifiService.insert(wifiDTO);
    }
}
