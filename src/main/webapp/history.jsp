<%@ page import="com.example.dao.HistoryService" %>
<%@ page import="com.example.dto.HistoryDTO" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>히스토리 목록</title>
    <style>
        body {
            font-family: Arial, sans-serif;
        }
        h1 {
            text-align: center;
        }
        #menu {
            text-align: center;
            margin-bottom: 20px;
        }
        #menu a {
            margin: 0 10px;
            text-decoration: none;
            color: #000;
        }
        table {
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
            margin: 0 auto;
        }
        th, td {
            border: 1px solid #ddd;
            text-align: center;
            font-size: 14px;
            padding: 8px;
        }
        tr:nth-child(odd) {
            background-color: #f2f2f2;
        }
        tr:hover {
            background-color: #ddd;
        }
        th {
            padding-top: 12px;
            padding-bottom: 12px;
            background-color: #04AA6D;
            color: white;
        }
        .no-data {
            text-align: center;
            color: #999;
        }
    </style>
</head>
<body>

<h1>위치 히스토리 목록</h1>

<div id="menu">
    <a href="main.jsp">홈</a>
    <a href="history.jsp">위치 히스토리 목록</a>
    <a href="wifitotal.jsp">Open API 와이파이 정보 가져오기</a>
</div>

<%
    HistoryService history = new HistoryService();
    List<HistoryDTO> list = history.loadHistory();
    String id = request.getParameter("id");
    if (id != null) {
        history.deleteHistory(id);
    }
%>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>X좌표</th>
        <th>Y좌표</th>
        <th>조회일자</th>
        <th>비고</th>
    </tr>
    </thead>
    <tbody>
    <% if (list.size() != 0) {
        for (HistoryDTO historyDTO : list) { %>
    <tr>
        <td><%= historyDTO.getID() %></td>
        <td><%= historyDTO.getLAT() %></td>
        <td><%= historyDTO.getLNT() %></td>
        <td><%= historyDTO.getHISTORY_DATE() %></td>
        <td>
            <form action="history.jsp" method="get">
                <input type="hidden" name="id" value="<%= historyDTO.getID() %>">
                <button type="submit">삭제</button>
            </form>
        </td>
    </tr>
    <% }
    } else { %>
    <tr>
        <td colspan="5" class="no-data">위치 조회한 내역이 없습니다.</td>
    </tr>
    <% } %>
    </tbody>
</table>

</body>
</html>
