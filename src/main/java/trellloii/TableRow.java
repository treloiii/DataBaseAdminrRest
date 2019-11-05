package trellloii;

public class TableRow {
    String name;
    String type;
    String length_val;
    boolean isPrimaryKey;
    boolean isAutoIncriment;

    public TableRow(String name, String type, String length_val, boolean isPrimaryKey, boolean isAutoIncrement) {
        this.name = name;
        this.type = type;
        this.length_val = length_val;
        this.isPrimaryKey = isPrimaryKey;
        this.isAutoIncriment = isAutoIncrement;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public String getLength_val() {
        return length_val;
    }

    public boolean isPrimaryKey() {
        return isPrimaryKey;
    }

    public boolean isAutoIncriment() {
        return isAutoIncriment;
    }
}
