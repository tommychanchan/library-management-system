package lms;

import java.io.*;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.*;
import javax.swing.*;
import javax.swing.table.*;

public class Utils {
    public static String exportCSV(String fn, JTable table) {
        OutputStreamWriter writer = null;
        String filename = fn + "_" + Main.fakeTime.formatDateTimeForFile() + ".csv";
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
    
    public static String[] publisherChoices() {
        Statement stmt = null;
        ArrayList<String> choices = new ArrayList<>();
        choices.add("");
        try{
            stmt = Main.conn.createStatement();
            String sql = "select distinct publisher from bookinfo order by publisher";
            ResultSet rs = stmt.executeQuery(sql);
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
    
    public static boolean isValidHKID(String id) {
        String hkid = id.toUpperCase();
        int n = hkid.length();
        if (n == 8 && !Pattern.matches("^.\\d{7}$", id)) {
            return false;
        }
        if (n == 9 && !Pattern.matches("^..\\d{7}$", id)) {
            return false;
        }
        return (n == 8 || n == 9) && (hkid.equals(generateHKID(hkid.substring(0, n-1))));
    }
    
    public static String generateHKID(String id) {
        String hkid = id.toUpperCase();
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
        int n = isbn.length();
        return (n == 10 || n == 13) && (isbn.equals(generateISBN(isbn.substring(0, n-1))));
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
