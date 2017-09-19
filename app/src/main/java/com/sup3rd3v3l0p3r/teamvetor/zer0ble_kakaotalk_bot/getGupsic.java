package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;

import org.hyunjun.school.School;
import org.hyunjun.school.SchoolException;
import org.hyunjun.school.SchoolMenu;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by dlals on 2017-09-18.
 */

public class getGupsic {
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
    public void getTodayLunch() {
        getDate today = new getDate();
        getLunch(today.YYYY,today.MM,today.DD);
    }

    public void getTomorrowLunch() {
        getDate today = new getDate();
        getLunch(today.YYYY,today.MM,today.DD+1);
    }
    public void getTodayDinner() {
        getDate today = new getDate();
        getDinner(today.YYYY,today.MM,today.DD);
    }
    public void getTodayLunchByDay(int DD) {
        getDate today = new getDate();
        getLunch(today.YYYY,today.MM,DD);
    }
    public void getTodayDinnerByDay(int DD) {
        getDate today = new getDate();
        getDinner(today.YYYY,today.MM,DD);
    }
    public void getTomorrowDinner() {
        getDate today = new getDate();
        getDinner(today.YYYY,today.MM,today.DD+1);
    }
    public void getLunch(int YYYY,int MM,int DD){
        try {
            List<SchoolMenu> menu = api.getMonthlyMenu(YYYY, MM);
            Listener.send(YYYY+"-"+MM+"-"+DD+"\n"+menu.get(DD-1).lunch.replace("&amp;",","));
        } catch (SchoolException e) {
            Listener.send("ERROR");
            e.printStackTrace();
        }
    }
    public void getDinner(int YYYY,int MM,int DD){
        try {
            List<SchoolMenu> menu = api.getMonthlyMenu(YYYY, MM);
            Listener.send(YYYY+"-"+MM+"-"+DD+"\n"+menu.get(DD-1).dinner.replace("&amp;","&"));
        } catch (SchoolException e) {
            Listener.send("ERROR");
            e.printStackTrace();
        }
    }

    public void getAllLunch(){
        getDate today = new getDate();
        try {
            List<SchoolMenu> menu = api.getMonthlyMenu(today.YYYY,today.MM);
            String message = "";
            for (int i=0;i<menu.size();i++)
                message+=today.YYYY+"-"+today.MM+"-"+(i+1)+"\n"+menu.get(i).lunch.replace("&amp;","&")+"\n\n";
            Listener.send(message);
        } catch (SchoolException e) {
            Listener.send("ERROR");
            e.printStackTrace();
        }
    }

}
