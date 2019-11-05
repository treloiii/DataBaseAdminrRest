package trellloii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class Table {
    private String name,schemaName;
    private Connection connection;
    private Map<String, List<String>> table=new LinkedHashMap<>();
    Table(String name,String schemaName) throws SQLException {
        this.name=name;
        this.schemaName=schemaName;

        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();
        PreparedStatement statement=connection.prepareStatement("SELECT * FROM "+schemaName+"."+name+";");
        ResultSet rs=statement.executeQuery();
        for(int i=1;i<rs.getMetaData().getColumnCount()+1;i++){
            table.put(rs.getMetaData().getColumnName(i), new ArrayList<>());

        }

        while (rs.next()){
            for(int i=1;i<rs.getMetaData().getColumnCount()+1;i++){
                table.get(rs.getMetaData().getColumnName(i)).add(rs.getString(i));
                table.replace(rs.getMetaData().getColumnName(i),table.get(rs.getMetaData().getColumnName(i)));
            }
        }

//        table.put(name,Arrays.asList(schemaName, schemaName, schemaName));
//        table.put(name+"1", Arrays.asList(schemaName, schemaName, schemaName));
//        table.put(name+"2", Arrays.asList(schemaName, schemaName, schemaName));
    }

    public Map<String, List<String>> getTable() {
        return table;
    }
}
