package trellloii;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

public class UpdateParamTable {
    QueryObject qo;
    private Connection connection;
    private String success;
    UpdateParamTable(QueryObject qo) throws SQLException {
        this.qo=qo;
        AnnotationConfigApplicationContext context=new AnnotationConfigApplicationContext(BasicAuthConfiguration.class);
        DatabaseConnection c=context.getBean("databaseInst",DatabaseConnection.class);
        connection=c.getConnection();

        PreparedStatement ps;
        if(qo.getAction().equals("UPDATE")) {
            ps = connection.prepareStatement(this.UpdateTable());
            System.out.println(this.UpdateTable());
        }
        else {
            ps = connection.prepareStatement(this.deleteRow());
            System.out.println(this.deleteRow());
        }
        try {
            success = String.valueOf(ps.executeUpdate());
        }
        catch (SQLIntegrityConstraintViolationException e){
            System.out.println(e.getMessage());
            success=e.getMessage();
        }
    }

    public String UpdateTable(){
        StringBuilder query=new StringBuilder("UPDATE "+this.qo.getSchema()+"."+this.qo.getTable()+" SET ");
        for(int i=0;i<this.qo.getCols().length;i++){
            query.append(this.qo.getCols()[i]).append("='").append(this.qo.getValues()[i]).append("', ");
        }
        query.deleteCharAt(query.length()-2);
        query.append("WHERE ").append(this.qo.getPrimary_key()).append("=").append(this.qo.getPrimary_key_val()).append(";");
        return query.toString();
    }

    public String deleteRow(){
        StringBuilder query=new StringBuilder("DELETE FROM "+this.qo.getSchema()+"."+this.qo.getTable()+
                " WHERE "+qo.getPrimary_key()+"="+qo.getPrimary_key_val());
        return  query.toString();
    }

    public String getSuccess() {
        return success;
    }
}
