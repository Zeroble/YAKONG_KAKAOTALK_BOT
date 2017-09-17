//package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;
//
//import java.util.Calendar;
//
///**
// * Created by dlals on 2017-09-07.
// */
//
//public class TimeTable {
//    public void send4TimeTable(String today){
//
//    }
//    public String getWeek() {
//        switch (Calendar.getInstance().get(Calendar.DAY_OF_WEEK)) {
//            case 1:
//                return "일";
//            case 2:
//                return "월";
//            case 3:
//                return "화";
//            case 4:
//                return "수";
//            case 5:
//                return "목";
//            case 6:
//                return "금";
//            case 7:
//                return "토";
//        }
//        return "ERROR";
//    }
//    public void TimeTable() {
//        if(session.message.contains("4")){
//            if(session.message.contains("월"))
//                new TimeTable().send4TimeTable("월");
//            else if(session.message.contains("화"))
//                new TimeTable().send4TimeTable("화");
//            else if(session.message.contains("수"))
//                new TimeTable().send4TimeTable("수");
//            else if(session.message.contains("목"))
//                new TimeTable().send4TimeTable("목");
//            else if(session.message.contains("금"))
//                new TimeTable().send4TimeTable("금");
//            else{
//                switch (getWeek()){
//                    case "월":
//                        new TimeTable().send4TimeTable("월");
//                        break;
//                    case "화":
//                        new TimeTable().send4TimeTable("화");
//                        break;
//                    case "수":
//                        new TimeTable().send4TimeTable("수");
//                        break;
//                    case "목":
//                        new TimeTable().send4TimeTable("목");
//                        break;
//                    case "금":
//                        new TimeTable().send4TimeTable("금");
//                        break;
//                    default:
//                        send("ERROR");
//                }
//            }
//        }
//        else if(session.message.contains("5")){
//        }
//        else if (session.message.contains("6")){
//        }
//        else
//            send("입력 예시) 5반 오늘 시간표/5 수요일 시간표");
//    }
//}
