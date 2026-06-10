package flowbank.dao;

import flowbank.configuracao.Config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            return DriverManager.getConnection(
                Config.getProperty("db.url"),
                Config.getProperty("db.user"),
                Config.getProperty("db.password")
            );
        } catch (ClassNotFoundException e) {
            throw new SQLException("Driver do banco de dados não encontrado.", e);
        }
    }
}
