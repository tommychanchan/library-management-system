package lms;

import java.text.*;
import java.util.*;

public class FakeTime {
    private SimpleDateFormat dtf; // e.g. yyyy-MM-dd HH:mm:ss
    private java.sql.Date fakeDatetime;
    
    public FakeTime() {
        fakeDatetime = null;
    }
    
    public void resetFakeTime() {
        fakeDatetime = null;
    }
    
    public java.sql.Date getDate() {
        return java.sql.Date.valueOf(formatDate());
    }
    
    public String formatDate() {
        dtf = new SimpleDateFormat("yyyy-MM-dd");
        return (fakeDatetime == null ? dtf.format(new java.sql.Date(Calendar.getInstance().getTime().getTime())) : dtf.format(fakeDatetime));
    }
    
    public String formatTime() {
        dtf = new SimpleDateFormat("HH:mm:ss");
        return dtf.format(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
    }
    
    public String formatDateTime() {
        return formatDate() + " " + formatTime();
    }
    
    public String formatTimeForFile() {
        return formatTime().replaceAll(":", "");
    }
    
    public String formatDateTimeForFile() {
        return formatDate() + "_" + formatTime().replaceAll(":", "");
    }
    
    public boolean setFakeTime(String date) {
        try {
            fakeDatetime = java.sql.Date.valueOf(date);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
