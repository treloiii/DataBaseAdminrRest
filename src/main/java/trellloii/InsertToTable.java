package trellloii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

public class InsertToTable {
    String name;
    String schema_name;
    String keys[];
    String[][] values;
    String success;
    Connection connection;
    public InsertToTable(String name, String schema_name,String keys[], String[][] values) {
        this.name = name;
        this.schema_name = schema_name;
        this.values = values;
        this.keys=keys;
    }

    public List<String> Query(){
        List<String> resArr=new ArrayList<>();
        StringBuilder query=new StringBuilder();
        for(int i=0;i<values.length;i++) {
            query.append("INSERT INTO ").append(schema_name).append(".").append(name).append("(");
            for (String col : keys) {
                query.append(col).append(",");
            }
            query.deleteCharAt(query.length() - 1);
            query.append(") VALUES (");
            for (int j=0;j<values[i].length;j++){
                query.append("'").append(values[i][j]).append("'").append(",");
            }
            query.deleteCharAt(query.length() - 1);
            query.append(");");
            resArr.add(query.toString());
            query.delete(0,query.length());
        }
        System.out.println(query);
        return resArr;
    }

    public void Insert() throws SQLException {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();
        for(String query:this.Query()) {
            PreparedStatement ps = connection.prepareStatement(query);
            try {
                success = String.valueOf(ps.executeUpdate());
            } catch (SQLIntegrityConstraintViolationException e) {
                System.out.println(e.getMessage());
                success = e.getMessage();
            }
        }
    }

    public String getSuccess() {
        return success;
    }
}
