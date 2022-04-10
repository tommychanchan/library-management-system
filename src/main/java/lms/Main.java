package lms;

import java.sql.*;
import java.util.*;

public class Main {
    public static String PROGRAM_NAME = "Library Management System";
    public static MainGUI mainGUI;
    public static PreSettingGUI preSettingGUI;
    public static final String JDBC_DRIVER = "org.mariadb.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://{host}/lms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC&useAffectedRows=true";
    public static Connection conn = null;
    public static FakeTime fakeTime;
    public static final String REPORT_FOLDER = "reports";
    public static final Report[] REPORT_DATA = {
        new Report("所有未還欠書的客戶", "all_owing_customers", new String[] {
            "HKID", "姓名", "欠書數量"
        }),
        new Report("全部借書記錄", "all_borrow", new String[] {
            "HKID", "ISBN", "借書日期", "到期日", "還書日期"
        }),
        new Report("客戶遲還書機率", "customer_late_probability", new String[] {
            "HKID", "借書數量", "遲還書數量", "遲還書機率"
        })
    };
    public static ArrayList<Book> borrowPageBooks = new ArrayList<>();
    
    public static void main(String[] args) {
        fakeTime = new FakeTime();
        mainGUI = new MainGUI();
        preSettingGUI = new PreSettingGUI();
        
        preSettingGUI.setVisible(true);
    }
}
