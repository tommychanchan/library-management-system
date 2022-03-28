package lms;

import java.io.*;
import java.nio.file.*;
import java.sql.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;

public class Utils {
    private static final SimpleDateFormat toStringDtf = new SimpleDateFormat("yyyy-MM-dd");
    
    public static long daysDifference(java.sql.Date d1, java.sql.Date d2) {
        return TimeUnit.DAYS.convert(d2.getTime() - d1.getTime(), TimeUnit.MILLISECONDS);
    }
    
    public static java.sql.Date addDays(java.sql.Date date, int days) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DATE, days);
        return new java.sql.Date(c.getTimeInMillis());
    }
    
    public static String toString(java.sql.Date date) {
        return toStringDtf.format(date);
    }
    
    public static int tableColumnNameToIndex(JTable table, String name) {
        for (int i=0, n = table.getColumnCount(); i < n; i++) {
            if (table.getColumnName(i).equals(name)) {
                return i;
            }
        }
        return -1;
    }
    
    public static String exportCSV(String fn, JTable table) {
        OutputStreamWriter writer = null;
        String folder = Main.REPORT_FOLDER + "/" + Main.fakeTime.formatDate();
        try {
            Files.createDirectories(Paths.get(folder));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        String filename = folder + "/" + fn + "_" + Main.fakeTime.formatTimeForFile() + ".csv";
        try {
            File file = new File(filename);
            writer = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
            DefaultTableModel tableModel = (DefaultTableModel) table.getModel();
            for (int j = 0, nn = tableModel.getColumnCount(); j < nn; j++) {
                writer.write((j == 0 ? "" : ",") + formatCSV(table.getColumnName(j)));
            }
            writer.write("\r\n");
            for (int i = 0, n = tableModel.getRowCount(); i < n; i++) {
                for (int j = 0, nn = tableModel.getColumnCount(); j < nn; j++) {
                    writer.write((j == 0 ? "" : ",") + formatCSV(table.getValueAt(i, j).toString()));
                }
                writer.write("\r\n");
            }
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
            return null;
        } catch (UnsupportedEncodingException ex) {
            ex.printStackTrace();
            return null;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                writer.flush();
                writer.close();
            } catch (IOException ex) {
                ex.printStackTrace();
                return null;
            }
        }
        return filename;
    }
    
    private static String formatCSV(String s) {
        return (s.contains(",") || s.contains("\"") ? "\"" + s.replaceAll("\"", "\"\"").trim() + "\"" : s.trim());
    }
    
    public static String[] reportChoices() {
        String[] choices = new String[Main.REPORT_DATA.length];
        for (int i = 0, n = Main.REPORT_DATA.length; i < n; i++) {
            choices[i] = Main.REPORT_DATA[i].getDesc();
        }
        return choices;
    }
    
    public static String[] publisherChoices() {
        PreparedStatement stmt = null;
        ArrayList<String> choices = new ArrayList<>();
        
        // a default choice: nothing
        choices.add("");
        
        try{
            stmt = Main.conn.prepareStatement("select distinct publisher from bookinfo order by publisher");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                choices.add(rs.getString("publisher"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        return choices.toArray(new String[0]);
    }
    
    public static String[] userTypeChoices() {
        PreparedStatement stmt = null;
        ArrayList<String> choices = new ArrayList<>();
        
        // a default choice: nothing
        choices.add("");
        
        try{
            stmt = Main.conn.prepareStatement("select distinct type_name from usertype order by type_name");
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                choices.add(rs.getString("type_name"));
            }
            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try{
                if (stmt != null) stmt.close();
            }catch(SQLException se2){}
        }
        return choices.toArray(new String[0]);
    }
    
    public static boolean isDouble(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isInt(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    
    public static boolean isValidHKID(String hkid) {
        hkid = hkid.toUpperCase();
        return Pattern.matches("^[A-Z]{1,2}\\d{6}[0-9A]$", hkid) && hkid.equals(generateHKID(hkid.substring(0, hkid.length()-1)));
    }
    
    public static String generateHKID(String hkid) {
        hkid = hkid.toUpperCase();
        int temp = 0;
        String digit;
        if (hkid.length() == 7) {
            // 8 digit HKID
            temp += 324 + (hkid.charAt(0) - 55) * 8;
            for (int i = 1, n = hkid.length(); i < n; i++) {
                temp += Integer.valueOf(hkid.substring(i, i+1)) * (8 - i);
            }
            temp = (11 - (temp % 11)) % 11;
            digit = (temp == 10 ? "A" : Integer.toString(temp));
        } else {
            // 9 digit HKID
            temp += (hkid.charAt(0) - 55) * 9 + (hkid.charAt(1) - 55) * 8;
            for (int i = 2, n = hkid.length(); i < n; i++) {
                temp += Integer.valueOf(hkid.substring(i, i+1)) * (9 - i);
            }
            temp = (11 - (temp % 11)) % 11;
            digit = (temp == 10 ? "A" : Integer.toString(temp));
        }
        return hkid + digit;
    }
    
    public static boolean isValidISBN(String isbn) {
        isbn = isbn.toUpperCase();
        return (Pattern.matches("^\\d{9}[0-9X]$", isbn) || Pattern.matches("^\\d{13}$", isbn)) && (isbn.equals(generateISBN(isbn.substring(0, isbn.length()-1))));
    }

    public static String generateISBN(String isbn) {
        int temp = 0;
        String digit;
        if (isbn.length() == 9) {
            // 10 digit ISBN
            for (int i = 0, n = isbn.length(); i < n; i++) {
                temp += Integer.valueOf(isbn.substring(i, i+1)) * (10 - i);
            }
            temp = (11 - (temp % 11)) % 11;
            digit = (temp == 10 ? "X" : Integer.toString(temp));
        } else {
            // 13 digit ISBN
            for (int i = 0, n = isbn.length(); i < n; i++) {
                temp += Integer.valueOf(isbn.substring(i, i+1)) * (i % 2 == 0 ? 1 : 3);
            }
            temp = (10 - (temp % 10)) % 10;
            digit = Integer.toString(temp);
        }
        return isbn + digit;
    }
}
