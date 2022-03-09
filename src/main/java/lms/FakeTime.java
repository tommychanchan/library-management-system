package lms;

import java.time.format.*;
import java.time.*;

public class FakeTime {
    private DateTimeFormatter dtf; // e.g. yyyy/MM/dd HH:mm:ss
    private LocalDateTime fakeDatetime;
    
    public FakeTime() {
        fakeDatetime = null;
    }
    
    public String getDate() {
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        return (fakeDatetime == null ? dtf.format(LocalDateTime.now()) : dtf.format(fakeDatetime));
    }
    
    public String getTime() {
        dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        return dtf.format(LocalDateTime.now());
    }
    
    public String getDateTime() {
        dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        return (fakeDatetime == null ? dtf.format(LocalDateTime.now()) : dtf.format(fakeDatetime));
    }
}
