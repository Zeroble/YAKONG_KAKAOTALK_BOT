package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;

import org.hyunjun.school.School;
import org.hyunjun.school.SchoolException;
import org.hyunjun.school.SchoolSchedule;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dlals on 2017-09-20.
 */

public class getSchoolScadule {
    final String SUNRIN_HIGHSCHOLL_CODE = "B100000658";//B100000658
    School api = new School(School.Type.HIGH, School.Region.SEOUL, SUNRIN_HIGHSCHOLL_CODE);
    class getDate{
        int DD;
        int MM;
        int YYYY;
        getDate(){
            long now = System.currentTimeMillis();
            Date date = new Date(now);
            YYYY = Integer.parseInt(new SimpleDateFormat("y").format(date));
            MM = Integer.parseInt(new SimpleDateFormat("M").format(date));
            DD = Integer.parseInt(new SimpleDateFormat("d").format(date));
        }
    }
    public void sendSchoolScadule() throws SchoolException {
        getDate today = new getDate();
        try {
            List<SchoolSchedule> schedule = api.getMonthlySchedule(today.YYYY, today.MM);
            String message = "";
            for (int i=0;i<schedule.size();i++)
                message+=today.YYYY+"-"+today.MM+"-"+(i+1)+"\n"+schedule.get(i).schedule.replace("&amp;","&")+"\n\n";
            Listener.send(message);
        } catch (Exception e) {
            Listener.send("ERROR");
        }
    }
}
