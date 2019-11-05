package trellloii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Update {
    private Connection connection;
    private String success;
    Update(String schemaName,String where,String value,String table,String col) throws SQLException {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();
        PreparedStatement ps=connection.prepareStatement(String.format("UPDATE %s.%s SET %s='%s' WHERE id=%s",schemaName,table,col,value,where));
        success= String.valueOf(!ps.execute());
    }

    public String getSuccess() {
        return success;
    }
}
