package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * Created by dlals on 2017-06-23.
 */

public class Listener extends NotificationListenerService {
    Context context;
    Session session = new Session();
    final static String BOT_NAME = "야꿍봇";
    int today = 0;
    String todayGupsic="";
    @Override
    public void onNotificationPosted(StatusBarNotification sbn) {
        super.onNotificationPosted(sbn);

        if (sbn.getPackageName().equals("com.kakao.talk")) {
            Notification.WearableExtender wearableExtender = new Notification.WearableExtender(sbn.getNotification());
            for (Notification.Action act : wearableExtender.getActions())
                if (act.getRemoteInputs() != null && act.getRemoteInputs().length > 0) {//String title = statusBarNotification.getNotification().extras.getString("android.title");
                    context = getApplicationContext();

                    session.session = act;
                    session.message = sbn.getNotification().extras.get("android.text").toString();
                    session.sender = sbn.getNotification().extras.getString("android.title");
                    session.room = act.title.toString().replace("답장 (", "").replace(")", "");
                    Log.i("asd", "message : " + session.message + " nsender : " + session.sender + " nroom : " + session.room + " nsession : " + session);
                    if (session.message.contains("노무현"))
                        send("살아있다");
                    if (session.message.contains("우유")) {
                        send("핥짝핥짝");
                        send("츄룹츄륩");
                    }
                    if (session.message.contains("하스스톤")) {
                        send("갓겜");
                        send("인정");
                    }
                    if (session.message.contains("산책"))
                        send("히익,,오늘도 그 강력하고 두꺼운 목줄로 저를,,");
                    if (session.message.contains("어디에"))
                        send("국정원 지하벙커!");
                    if (session.message.contains("!가위") || session.message.contains("!바위") || session.message.contains("!보") || session.message.contains("!보자기"))
                        getRspResult();
                    if (session.message.contains("섹스") || session.message.contains("성관계"))
                        send("중성화당해서 그런거 몰라아파요....");
                    if (session.message.contains("아파") || session.message.contains("아프다")) {
                        send("아픈곳 핥아드릴께요");
                        send("핥핥");
                    }
                    if (session.message.contains("잘했어"))
                        send("히힛 쓰다듬어줘.집사");
                    if (session.message.contains("쓰담"))
                        send("읏,,♥ (집사의 손 너무기분좋아,,)");
                    if (session.message.contains("힘들다"))
                        send("으으,,너만 괜찮다면 내 꼬리를,,");
                    if (session.message.contains("히오스"))
                        send("♚♚히어로즈 오브 더 스☆톰♚♚가입시$$전원 카드팩☜☜뒷면100%증정※ ♜월드오브 워크래프트♜펫 무료증정￥ 특정조건 §§디아블로3§§★공허의유산★초상화획득기회@@ 즉시이동 http://kr.battle.net/heroes/ko/ ♚♚히어로즈 오브 더 스☆톰♚♚가입시$$전원 카드팩☜☜뒷면100%증정※ ♜월드오브 워크래프트♜펫 무료증정￥ 특정조건 §§디아블로3§§★공허의유산★초상화획득기회@@ 즉시이동http://kr.battle.net/heroes/ko/");
                    if (session.message.contains("애니추천"))
                        send("요스가노소라,,,읍,,");
                    if (session.message.contains("자자") || session.message.contains("잠"))
                        send("ㅈ..집사 오늘은 같이자자..?");
                    if (session.message.contains("심심"))
                        send("심심하다면 내가 놀아줄수도있다고..?집사");
                    if (session.message.contains("미뇽"))
                        send("미뇽\nミニリュウ 미니류\tNo.147\nDratin\n\n타입 : 드래곤\t분류 : 드래곤 포켓몬\n특성 : 탈피\t\t숨겨진 특성 : 이상한 비늘\nLV.100 경험치량 : 1,250,000\n\n키 : 1.8m\t몸무게 : 3.3kg\n\n" + "포획률 : 45\t성비 : 1:1\n부화 걸음수 : 10,240걸음");
                    if (session.message.contains("급식"))
                        getGupsic();
                }
            stopSelf();
        }
    }

    public void send(String massage) {
        Intent sendIntent = new Intent();
        Bundle msg = new Bundle();
        for (RemoteInput inputable : session.session.getRemoteInputs())
            msg.putCharSequence(inputable.getResultKey(), massage);
        RemoteInput.addResultsToIntent(session.session.getRemoteInputs(), sendIntent, msg);

        try {
            session.session.actionIntent.send(context, 0, sendIntent);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public class Session {
        Notification.Action session;
        String message;
        String sender;
        String room;
    }

    public void getGupsic() {
        long now = System.currentTimeMillis(); // 1970년 1월 1일부터 몇 밀리세컨드가 지났는지를 반환함
        Date date = new Date(now);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd");//형식 지정
        final String getTime = simpleDateFormat.format(date);
        if(Integer.parseInt(getTime) != today){
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Document doc;
                    Elements links;
                    try {
                        doc = Jsoup.connect("http://www.sunrint.hs.kr/index.do#none").get();
                        links = doc.select("#index_board_mlsv_03_195699 > div > div > div > div > ul > li:nth-child(1) > dl > dd > p.menu");
                        send(links.text());
                        todayGupsic = links.text();
                        today = Integer.parseInt(getTime);
                        Log.i("new","new");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).run();
        }
        else
            send(todayGupsic);
    }

    public void getRspResult() {
        int botReslt = (int) (Math.random() * 3);
        Log.i("TAG", "getRspResult : " + botReslt);
        if (session.message.contains("가위")) {
            if (botReslt == 0)
                send(BOT_NAME + " (이)는 가위를 냈다. 결과는 비김!");
            else if (botReslt == 1)
                send(BOT_NAME + " (이)는 바위를 냈다. 결과는" + BOT_NAME + "이 이김!");
            else if (botReslt == 2)
                send(BOT_NAME + " (이)는 보를 냈다. 결과는" + BOT_NAME + "이 짐!");
        } else if (session.message.contains("바위")) {
            if (botReslt == 0)
                send(BOT_NAME + " (이)는 가위를 냈다. 결과는" + BOT_NAME + "이 짐!");
            else if (botReslt == 1)
                send(BOT_NAME + " (이)는 바위를 냈다. 결과는 비김!");
            else if (botReslt == 2)
                send(BOT_NAME + " (이)는 보를 냈다. 결과는" + BOT_NAME + "이 이김!");
        } else if (session.message.contains("보") || session.message.contains("보자기")) {
            if (botReslt == 0)
                send(BOT_NAME + " (이)는 가위를 냈다. 결과는" + BOT_NAME + "이 이김!");
            else if (botReslt == 1)
                send(BOT_NAME + " (이)는 바위를 냈다. 결과는" + BOT_NAME + "이 짐!");
            else if (botReslt == 2)
                send(BOT_NAME + " (이)는 보를 냈다. 결과는 비김!");
        } else
            send("오류");
    }
}
