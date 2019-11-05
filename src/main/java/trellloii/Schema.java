package trellloii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Schema {
    String name;
    private List<String> tables;
    private Connection connection;
    Schema(String name) throws SQLException {
        this.name=name;
        tables=new ArrayList<>();
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();
        PreparedStatement statement=connection.prepareStatement("SELECT table_name FROM information_schema.tables where table_schema='"+name+"';");
        ResultSet rs=statement.executeQuery();
        while (rs.next()){
            for(int i=1;i<rs.getMetaData().getColumnCount()+1;i++){
                tables.add(rs.getString(i));
            }
        }

    }

    public String getName() {
        return name;
    }

    public List<String> getTables() {
        return tables;
    }
}
