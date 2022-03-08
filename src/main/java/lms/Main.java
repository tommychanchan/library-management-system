package lms;

import java.sql.*;


public class Main {
    public static String PROGRAM_NAME = "Library Management System";
    public static MainGUI mainGUI;
    public static PreSettingGUI preSettingGUI;
    public static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/lms?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    
    
    public static void main(String[] args) {
        mainGUI = new MainGUI();
        preSettingGUI = new PreSettingGUI();
        
        preSettingGUI.setVisible(true);
        
        Connection conn = null;
        Statement stmt = null;
        try{
            Class.forName(JDBC_DRIVER);
            conn = DriverManager.getConnection(DB_URL, "s207885", "123");
            stmt = conn.createStatement();
            String sql = "select * from bookinfo";
            ResultSet rs = stmt.executeQuery(sql);
            while(rs.next()){
                String isbn = rs.getString("ISBN");
                String title = rs.getString("title");
                System.out.print("ISBN: " + isbn + "\n");
                System.out.print("Title: " + title + "\n");
            }
            rs.close();
            stmt.close();
            conn.close();
        }catch(SQLException se){
            se.printStackTrace();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
            try{
                if (conn != null) conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
    }
}
