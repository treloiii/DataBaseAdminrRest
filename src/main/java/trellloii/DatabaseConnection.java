package trellloii;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@Component("databaseInst")
public class DatabaseConnection {
    private Connection connection;
    DatabaseConnection() throws SQLException {
        //this.connection= DriverManager.getConnection("jdbc:mysql://std-mysql.ist.mospolytech.ru:3306/std_763?useLegacyDatetimeCode=false&serverTimezone=UTC","std_763", "b4uld103");
        //this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "b4ULD103_");
        this.connection= DriverManager.getConnection("jdbc:mysql://localhost:3306?useLegacyDatetimeCode=false&serverTimezone=UTC","root", "b4uld103");
    }

    public Connection getConnection() {
        return connection;
    }
}
