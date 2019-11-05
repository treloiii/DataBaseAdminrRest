package trellloii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Arrays;

public class CreateTable {
    private String name;
    private String schema_name;
    private TableRow[] fields;
    private Connection connection;
    private String success;
    public CreateTable(String name, TableRow[] fields){
        this.name = name;
        this.fields = fields;
    }

    public void create() throws SQLException {
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();
        System.out.println(this.Query());
        PreparedStatement ps=connection.prepareStatement(this.Query());
        try {
            success = String.valueOf(ps.executeUpdate());
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
            success=e.getMessage();
        }
    }
    public String Query(){
        StringBuilder query= new StringBuilder("CREATE TABLE " +schema_name+"."+ name + "(");
        String primary_key="";
        for(int i=0;i<fields.length;i++){
            query.append(fields[i].getName()).append(" ").append(fields[i].getType()).append("(").append(fields[i].length_val).append(") ");
            if(fields[i].isAutoIncriment)
                query.append("AUTO_INCREMENT");
            if(fields[i].isPrimaryKey)
                primary_key=fields[i].name;
            query.append(",");
        }
        query.append("PRIMARY KEY(").append(primary_key).append("));");
        return query.toString();
    }

    public String getSuccess() {
        return success;
    }
}
