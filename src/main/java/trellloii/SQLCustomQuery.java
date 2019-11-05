package trellloii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SQLCustomQuery {
    String query;
    Connection connection;
    String success;
    public SQLCustomQuery(String query) {
        this.query = query;
        execute(query);
    }


    public void execute(String query){
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();
        try {
            PreparedStatement ps=connection.prepareStatement(query);
            ps.execute();
            success="1";
        }
        catch (Exception e){
            success=e.getMessage();
        }
    }

    public String getSuccess() {
        return success;
    }
}
