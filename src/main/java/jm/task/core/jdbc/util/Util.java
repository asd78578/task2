package jm.task.core.jdbc.util;

//import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
//    private static final String DB_DRIVER = "org.h2.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/mydbtest?serverTimezone=UTC";
    private static final String USERNAME = "root05";
    private static final String PASSWORD = "1593575Alav+";

    public Connection getConnection() {
        //Class.forName()
        Connection connection = null;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            if (!connection.isClosed()) {
                //System.out.println("We are connected!");
                System.out.println(connection.getTransactionIsolation());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return connection;
    }

}












//        try {
//            Class.forName(DB_DRIVER);
//            connection = DriverManager.getConnection(DB_URL, DB_USERNAME, DB_PASSWORD);
//            System.out.println("Connection implementations");
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//            System.out.println("ERROR Connection");
//        }
//        return connection;