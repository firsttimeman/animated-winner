import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLiteTest {

    public static void main(String[] args) {
        // SQLite JDBC 드라이버 클래스를 로드합니다.
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            System.err.println("SQLite JDBC 드라이버를 찾을 수 없습니다.");
            e.printStackTrace();
            return;
        }

        // SQLite 데이터베이스에 연결합니다.
        Connection connection = null;
        try {
            // 데이터베이스 파일 경로를 설정합니다.
            String url = "jdbc:sqlite:D:\\제로베이스\\데이터베이스\\sqlitedb\\wifidb.db"; // 여기에 실제 데이터베이스 파일 경로를 입력하세요.

            // 연결을 생성합니다.
            connection = DriverManager.getConnection(url);

            System.out.println("SQLite 데이터베이스에 성공적으로 연결되었습니다.");

            // 쿼리를 실행하여 데이터를 가져옵니다.
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM WifiInfo"); // 여기에 테이블 이름을 입력하세요.

            // 결과를 출력합니다.
            while (resultSet.next()) {
                // 테이블의 각 행의 데이터를 가져옵니다.
                String column1Data = resultSet.getString("X_SWIFI_MGR_NO"); // 여기에 컬럼 이름을 입력하세요.
                String column2Data = resultSet.getString("X_SWIFI_WRDOFC"); // 여기에 컬럼 이름을 입력하세요.

                // 데이터를 출력합니다.
                System.out.println("X_SWIFI_MGR_NO 1: " + column1Data + ", X_SWIFI_WRDOFC 2: " + column2Data);
            }
        } catch (SQLException e) {
            System.err.println("SQLite 데이터베이스에 연결하는 중에 오류가 발생했습니다.");
            e.printStackTrace();
        } finally {
            // 연결을 닫습니다.
            if (connection != null) {
                try {
                    connection.close();
                    System.out.println("SQLite 연결이 닫혔습니다.");
                } catch (SQLException e) {
                    System.err.println("SQLite 연결을 닫는 중에 오류가 발생했습니다.");
                    e.printStackTrace();
                }
            }
        }
    }
}
