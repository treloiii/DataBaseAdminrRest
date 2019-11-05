package trellloii;

public class QueryObject {
    String action;
    String table;
    String schema;
    String cols[];
    String values[];
    String primary_key;
    String primary_key_val;


//    public QueryObject(String action, String table, String[] cols, String[] values, String primary_key, String primary_key_val) {
//        this.action = action;
//        this.table = table;
//        this.cols = cols;
//        this.values = values;
//        this.primary_key = primary_key;
//        this.primary_key_val = primary_key_val;
//    }


    public String getSchema() {
        return schema;
    }

    public String getAction() {
        return action;
    }

    public String getTable() {
        return table;
    }

    public String[] getCols() {
        return cols;
    }

    public String[] getValues() {
        return values;
    }

    public String getPrimary_key() {
        return primary_key;
    }

    public String getPrimary_key_val() {
        return primary_key_val;
    }
}
