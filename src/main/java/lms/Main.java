package lms;


public class Main {
    public static String PROGRAM_NAME = "Library Management System";
    public static MainGUI mainGUI;
    public static PreSettingGUI preSettingGUI;
    
    
    
    public static void main(String[] args) {
        mainGUI = new MainGUI();
        preSettingGUI = new PreSettingGUI();
        
        preSettingGUI.setVisible(true);
    }
}
