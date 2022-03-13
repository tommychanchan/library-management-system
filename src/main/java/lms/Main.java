package lms;

import java.sql.*;

public class Main {
    public static String PROGRAM_NAME = "Library Management System";
    public static MainGUI mainGUI;
    public static PreSettingGUI preSettingGUI;
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://{host}/lms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useAffectedRows=true";
    public static Connection conn = null;
    public static FakeTime fakeTime;
    public static final String REPORT_FOLDER = "reports";
    public static final int MAX_BOOKS_BORROW = 8;
    public static final int MAX_DAYS_BORROW = 14;
    public static final double DEBT_EACH_DAY = 1.5;
    
    public static void main(String[] args) {
        fakeTime = new FakeTime();
        mainGUI = new MainGUI();
        preSettingGUI = new PreSettingGUI();
        
        preSettingGUI.setVisible(true);
    }
}
