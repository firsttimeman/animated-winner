
<%@ page import="java.util.List" %>
<%@ page import="com.example.dto.HistoryDTO" %>
<%@ page import="com.example.dao.HistoryService" %>
<%@ page import="com.example.dao.WifiService" %>
<%@ page import="com.example.dto.WifiDTO" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%
    request.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
<head>
    <title>와이파이 정보 구하기</title>

    <style>
        #link-list {
            margin-bottom: 20px;
        }

        #form-list {
            margin-bottom: 10px;
        }

        #table-list {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        #table-list td, #table-list th {
            border: 1px solid #ddd;
            text-align: center;
            font-size: 14px;
            padding: 8px;
        }

        #table-list tr:nth-child(odd) {
            background-color: #f2f2f2;
        }

        #table-list tr:hover {
            background-color: #ddd;
        }

        #table-list th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            color: white;
        }
    </style>
</head>
<body>
<h1>와이파이 정보 구하기</h1>

<div id="link-list">
    <a href="main.jsp">홈</a>
    <a href="history.jsp">위치 히스토리 목록</a>
    <a href="wifitotal.jsp">Open API 와이파이 정보 가져오기</a>
</div>

<form method="get" action="main.jsp" id="form-list">
    <label>
        LAT: <input type="text" id="lat" name="lat" value="0.0">
    </label>
    <label>
        LNT: <input type="text" id="lnt" name="lnt" value="0.0">
    </label>
    <input type="button" value="내 위치 가져오기" onclick="getLocation();">
    <input type="submit" value="근처 WIFI 정보 보기">
</form>

<table id="table-list">
    <thead>
    <tr>
        <th>거리(Km)</th>
        <th>관리번호</th>
        <th>자치구</th>
        <th>와이파이명</th>
        <th>도로명주소</th>
        <th>상세주소</th>
        <th>설치위치(층)</th>
        <th>설치유형</th>
        <th>설치기관</th>
        <th>서비스구분</th>
        <th>망종류</th>
        <th>설치년도</th>
        <th>실내외구분</th>
        <th>WIFI접속환경</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>작업일자</th>
    </tr>
    </thead>
    <tbody>
    <%
        String lat = request.getParameter("lat");
        String lnt = request.getParameter("lnt");

        double latValue = 0.0;
        double lntValue = 0.0;

        if (lat != null && !lat.isEmpty()) {
            try {
                latValue = Double.parseDouble(lat);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (lnt != null && !lnt.isEmpty()) {
            try {
                lntValue = Double.parseDouble(lnt);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }

        if (latValue == 0.0 && lntValue == 0.0) {
    %>
    <tr>
        <td colspan="17">위치 정보를 입력한 후에 조회해 주세요.</td>
    </tr>
    <%
    } else {
        HistoryDTO historyDto = new HistoryDTO();
        historyDto.setLAT(latValue);
        historyDto.setLNT(lntValue);

        HistoryService historyDao = new HistoryService();
        historyDao.insertHistory(historyDto);

        WifiService wifiDao = new WifiService();
        List<WifiDTO> wifiDaoList = wifiDao.nearTwenty(lntValue, latValue);

        for (WifiDTO item : wifiDaoList) {
    %>
    <tr>
        <td>
            <%= item.getDistance() %>
        </td>
        <td>
            <%= item.getX_SWIFI_MGR_NO() %>
        </td>
        <td>
            <%= item.getX_SWIFI_WRDOFC() %>
        </td>
        <td>
            <%= item.getX_SWIFI_MAIN_NM() %>
        </td>
        <td>
            <%= item.getX_SWIFI_ADRES1() %>
        </td>
        <td>
            <%= item.getX_SWIFI_ADRES2() %>
        </td>
        <td>
            <%= item.getX_SWIFI_INSTL_FLOOR() %>
        </td>
        <td>
            <%= item.getX_SWIFI_INSTL_TY() %>
        </td>
        <td>
            <%= item.getX_SWIFI_INSTL_MBY() %>
        </td>
        <td>
            <%= item.getX_SWIFI_SVC_SE() %>
        </td>
        <td>
            <%= item.getX_SWIFI_CMCWR() %>
        </td>
        <td>
            <%= item.getX_SWIFI_CNSTC_YEAR() %>
        </td>
        <td>
            <%= item.getX_SWIFI_INOUT_DOOR() %>
        </td>
        <td>
            <%= item.getX_SWIFI_REMARS3() %>
        </td>
        <td>
            <%= item.getLNT() %>
        </td>
        <td>
            <%= item.getLAT() %>
        </td>
        <td>
            <%= item.getWORK_DTTM() %>
        </td>
    </tr>
    <%
            }
        }
    %>
    </tbody>
</table>

<script>
    const params = new URLSearchParams(window.location.search)
    const lnt = params.get("lnt")
    const lat = params.get("lat")

    if (lnt) {
        const lntElement = document.getElementById("lnt")
        lntElement.setAttribute("value", lnt)
    }

    if (lat) {
        const latElement = document.getElementById("lat")
        latElement.setAttribute("value", lat)
    }

    function getLocation() {
        if (navigator.geolocation) {
            navigator.geolocation.getCurrentPosition(showPosition);
        } else {
            alert("이 브라우저는 위치 정보를 지원하지 않습니다.");
        }
    }

    function showPosition(position) {
        const lat = position.coords.latitude;
        const lnt = position.coords.longitude;

        document.getElementById("lat").value = lat;
        document.getElementById("lnt").value = lnt;
    }
</script>
</body>
</html>
