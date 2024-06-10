<%@ page import="com.example.Service.APIService" %>
<%@ page import="com.example.dao.WifiService" %>
<%@ page import="com.example.dto.WifiDTO" %>
<%@ page import="java.util.List" %>
<%@ page import="java.io.IOException" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>서울 공공 와이파이</title>
</head>
<body>
<h1>서울 공공 와이파이 데이터</h1>

<%
    APIService apiService = new APIService();
    WifiService wifiService = new WifiService();
    List<WifiDTO> datas = null;



    try {
        datas = apiService.loadAPI();
    } catch (IOException e) {
        e.printStackTrace();
    }

    if (datas != null) {
        for(WifiDTO data : datas) {
            wifiService.insert(data);
        }

    }

%>

<h1 style="text-align: center;"><%=datas != null ? datas.size() : 0%>개의 WIFI 정보를 정상적으로 저장하였습니다</h1>
<div style="text-align: center;">
    <a href="main.jsp">홈으로 가기</a>
</div>

</body>
</html>
