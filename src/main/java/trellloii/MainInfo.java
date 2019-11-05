package trellloii;

import org.hibernate.validator.constraints.EAN;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MainInfo {

    private String database="sql";
    private String serverIp="localhost";
    private String info;
    private List<String> schemas;
    private Connection connection;


    MainInfo() throws SQLException {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();
        PreparedStatement ps=connection.prepareStatement("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA;");
        ResultSet rs=ps.executeQuery();
        schemas=new ArrayList<>();
        while (rs.next()){
            for(int i=1;i<rs.getMetaData().getColumnCount()+1;i++)
                schemas.add(rs.getString(i));
        }
       // schemas=new Schemas(data);


    }

    public List<String> getSchemas() {
        return schemas;
    }

    public String getDatabase() {
        return database;
    }

    public String getServerIp() {
        return serverIp;
    }

    public String getInfo() {
        return info;
    }
}
