package trellloii;
import com.google.gson.Gson;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.tomcat.util.json.JSONParser;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONObject;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Base64;

import static javafx.scene.input.KeyCode.S;

@CrossOrigin
@RestController
public class MainController {


    @RequestMapping("/")
    public MainInfo MainInfo() throws SQLException {
        return new MainInfo();
    }
    @RequestMapping("/schema")
    public Schema getSchema(@RequestParam(value="name")String name) throws SQLException {
        return new Schema(name);
    }
    @RequestMapping("/table")
    public Table getTable(@RequestParam(value="name")String name,@RequestParam(value="schemaName")String schemaName) throws SQLException {
        return new Table(name,schemaName);
    }
    @RequestMapping("/update/field")
    public Update update(@RequestParam(value="where")String where,
                         @RequestParam(value="value")String value,
                         @RequestParam(value="table")String table,
                         @RequestParam(value="col")String col,
                         @RequestParam(value="schemaName")String schemaName) throws SQLException {
        return new Update(schemaName,where,value,table,col);
    }

    @RequestMapping("/test")
    public UpdateParamTable test(@RequestBody String json) throws SQLException {
        Gson gson=new Gson();
        QueryObject qo=gson.fromJson(json,QueryObject.class);
        System.out.println(json);
        return new UpdateParamTable(qo);
        //return new QueryObject(qo.getAction(),qo.getTable(),qo.getCols(),qo.getValues(),qo.getPrimary_key(),qo.getPrimary_key_val());
        // return new QueryObject("POST","TABLE",new String[]{"1","2"},new String[]{"11","22"},"id","2");
    }
    @RequestMapping("/testTable")
    public CreateTable createTable(@RequestBody String json) throws SQLException {
        Gson gson=new Gson();
        CreateTable qo=gson.fromJson(json,CreateTable.class);
        System.out.println(gson.toJson(qo));
        qo.create();
        return qo;
        //return new UpdateParamTable(qo);
        //return new QueryObject(qo.getAction(),qo.getTable(),qo.getCols(),qo.getValues(),qo.getPrimary_key(),qo.getPrimary_key_val());
        // return new QueryObject("POST","TABLE",new String[]{"1","2"},new String[]{"11","22"},"id","2");
    }
    @RequestMapping("/insert")
    public InsertToTable testInsert(@RequestBody String json) throws SQLException {
        Gson gson=new Gson();
        InsertToTable qo=gson.fromJson(json,InsertToTable.class);
        System.out.println(gson.toJson(qo));
        qo.Insert();
        return qo;
    }
    @RequestMapping("/delete")
    public SQLCustomQuery deleteTable(@RequestParam(value = "schemaName") String schemaName,
                                     @RequestParam(value = "tableName") String tableName) throws SQLException {

        return new SQLCustomQuery("DROP TABLE "+schemaName+"."+tableName);
    }
    @RequestMapping("/newSchema")
    public SQLCustomQuery addNewSchema(@RequestParam(value="schemaName") String schemaName){
        return new SQLCustomQuery("CREATE SCHEMA IF NOT EXISTS "+schemaName);
    }
    @RequestMapping("/loginn")
    public boolean login(@RequestBody User user) {
        return user.getUserName().equals(DigestUtils.md5Hex("ADMIN")) && user.getPassword().equals(DigestUtils.md5Hex("root"));
    }
    @RequestMapping("/user")
    public Principal user(HttpServletRequest request) {
        String authToken = request.getHeader("Authorization").substring("Basic".length()).trim();
        //  String authToken= auth.substring("Basic".length()).trim();
        System.out.println(authToken);

        return () ->  new String(Base64.getDecoder()
                .decode(authToken)).split(":")[0];
    }
    @RequestMapping("/testJS")
    public String testJS(){
        Gson gson=new Gson();
        return gson.toJson("{\"suka\":\"blyat\"}");
    }


}
