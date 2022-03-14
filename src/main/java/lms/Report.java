package lms;

public class Report {
    private String desc;
    private String filename;
    private String[] columnNames;
    
    public Report() {}
    
    public Report(String desc, String filename, String[] columnNames) {
        this.desc = desc;
        this.filename = filename;
        this.columnNames = columnNames;
    }
    
    public String getDesc() {
        return desc;
    }
    
    public String getFilename() {
        return filename;
    }
    
    public String[] getColumnNames() {
        return columnNames;
    }
}
