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
        new Report("所有未還欠書的客戶", "all_owing_book_customers", new String[] {
            "HKID", "姓名", "欠書數量"
        }),
        new Report("所有未還罰款的客戶", "all_outstanding_fines_customers", new String[] {
            "HKID", "姓名", "未還罰款"
        }),
        new Report("全部借書記錄", "all_borrow_record", new String[] {
            "HKID", "ISBN", "借書日期", "到期日", "還書日期"
        }),
        new Report("客戶遲還書機率", "customer_late_probability", new String[] {
            "HKID", "姓名", "借書數量", "遲還書數量", "遲還書機率", "平均遲還日數"
        }),
        new Report("客戶類型遲還書機率", "customer_type_late_probability", new String[] {
            "客戶類型", "人數", "借書數量", "遲還書數量", "遲還書機率"
        }),
        new Report("最受歡迎圖書", "popular_books", new String[] {
            "ISBN", "書名", "借出數量（男）", "借出數量（女）", "借出數量合計"
        }),
        new Report("最受歡迎圖書（30日内）", "popular_books_30days", new String[] {
            "ISBN", "書名", "30日内借出數量"
        }),
        new Report("出版社的書的數量", "publisher_number_of_books", new String[] {
            "出版社", "書的數量"
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
