package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;

import android.app.Notification;
import android.app.PendingIntent;
import android.app.RemoteInput;
import android.content.ClipData;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.service.notification.NotificationListenerService;
import android.service.notification.StatusBarNotification;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.GsonConverterFactory;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.http.GET;


/**
 * Created by dlals on 2017-06-23.
 */

public class Listener extends NotificationListenerService {
    static Context context;
    static Session session = new Session();
//    static int cnt = 0;

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

                    boolean check = true;
                    Log.i("asd", "message : " + session.message + " sender : " + session.sender + " nroom : " + session.room + " nsession : " + session);
                    if(session.message.contains("미뇽")) {
                        if (session.message.contains("/")) {
                            if (session.message.contains("내일급식") || session.message.contains("내일 급식")) {
                                new getGupsic().getTomorrowLunch();
                                check = false;
                            } else if (session.message.contains("급식")) {
                                if (session.message.contains("1") || session.message.contains("2") || session.message.contains("3") || session.message.contains("4") || session.message.contains("5") || session.message.contains("6") || session.message.contains("7") || session.message.contains("8") || session.message.contains("9") || session.message.contains("0"))
                                    new getGupsic().getTodayLunchByDay(Integer.parseInt(session.message.replaceAll("[^0-9]", "")));
                                else if (session.message.contains("모든급식") || session.message.contains("전체급식"))
                                    new getGupsic().getAllLunch();
                                else
                                    new getGupsic().getTodayLunch();
                                check = false;
                            }

                            if (session.message.contains("내일석식") || session.message.contains("내일 석식")) {
                                new getGupsic().getTomorrowDinner();
                                check = false;
                            } else if (session.message.contains("석식")) {
                                if (session.message.contains("1") || session.message.contains("2") || session.message.contains("3") || session.message.contains("4") || session.message.contains("5") || session.message.contains("6") || session.message.contains("7") || session.message.contains("8") || session.message.contains("9") || session.message.contains("0"))
                                    new getGupsic().getTodayDinnerByDay(Integer.parseInt(session.message.replaceAll("[^0-9]", "")));
                                else
                                    new getGupsic().getTodayDinner();

                                check = false;
                            }
                            if (session.message.contains("가위") || session.message.contains("바위") || session.message.contains("보") || session.message.contains("보자기")) {
                                new RockScissorsPaperService().getRspResult(session.message);
                                check = false;
                            }
//                   if(session.message.contains("!메시지추가"))
//                        addUserMessage();
                            if (session.message.contains("한강온도")) {
                                getHangangTemp();
                                check = false;
                            }
                            if (session.message.contains("시간표")) ;
                            //TimeTable();
//                    if (session.message.contains("학사일정"))
//                        try {
//                            new getSchoolScadule().sendSchoolScadule();
//                        } catch (SchoolException e) {
//                            e.printStackTrace();
//                        }
                            if (session.message.contains("!HELP") || session.message.contains("!도움") || session.message.contains("야꿍") || session.message.contains("#HELP")) {
                                send("급식커멘드\n급식/석식 - 오늘급식/석식 출력\n내일급식/내일석식 - 내일급식/석식 출력\n[특정일] 급식/석식 - 해당 날자의 급식/석식 출력\nex) 24 급식-->24일 급식");
                                check = false;
                            }
                        } else if (session.message.contains("급식"))
                            send("/급식 명령어를 이용해 주세요.");
                        else {
                            if (session.message.contains("노무현")) {
                                send("살아있다");
                                check = false;
                            }
                            if (session.message.contains("우유")) {
                                send("핥짝핥짝");
                                send("츄룹츄륩");
                                check = false;
                            }
                            if (session.message.contains("하스스톤")) {
                                send("갓겜");
                                send("인정");
                                check = false;
                            }
                            if (session.message.contains("산책")) {
                                send("히익,,오늘도 그 강력하고 두꺼운 목줄로 저를,,");
                                check = false;
                            }
                            if (session.message.contains("어디에")) {
                                send("국정원 지하벙커!");
                                check = false;
                            }
                            if (session.message.contains("섹스") || session.message.contains("성관계")) {
                                send("중성화당해서 그런거 몰라요....");
                                check = false;
                            }
                            if (session.message.contains("아파") || session.message.contains("아프다")) {
                                send("아픈곳 핥아드릴께요");
                                send("핥핥");
                                check = false;
                            }
                            if (session.message.contains("잘했어")) {
                                send("히힛 쓰다듬어줘.집사");
                                check = false;
                            }
                            if (session.message.contains("쓰담")) {
                                send("읏,,♥ (집사의 손 너무기분좋아,,)");
                                check = false;
                            }
                            if (session.message.contains("힘들다")) {
                                send("으으..너만 괜찮다면 내 꼬리를..");
                                check = false;
                            }
                            if (session.message.contains("히오스")) {
                                send("♚♚히어로즈 오브 더 스☆톰♚♚가입시$$전원 카드팩☜☜뒷면100%증정※ ♜월드오브 워크래프트♜펫 무료증정￥ 특정조건 §§디아블로3§§★공허의유산★초상화획득기회@@ 즉시이동 http://kr.battle.net/heroes/ko/ ♚♚히어로즈 오브 더 스☆톰♚♚가입시$$전원 카드팩☜☜뒷면100%증정※ ♜월드오브 워크래프트♜펫 무료증정￥ 특정조건 §§디아블로3§§★공허의유산★초상화획득기회@@ 즉시이동http://kr.battle.net/heroes/ko/");
                                check = false;
                            }
                            if (session.message.contains("애니추천")) {
                                send("요스가노소라,,,읍,,");
                                check = false;
                            }
                            if (session.message.contains("자자") || session.message.contains("잠")) {
                                send("ㅈ..집사 오늘은 같이자자..?");
                                check = false;
                            }
                            if (session.message.contains("심심")) {
                                send("심심하다면 내가 놀아줄수도있다고..?집사");
                                check = false;
                            }
//                            if (session.message.contains("미뇽")) {
//                                send("미뇽\nミニリュウ 미니류\tNo.147\nDratin\n\n타입 : 드래곤\t분류 : 드래곤 포켓몬\n특성 : 탈피\t\t숨겨진 특성 : 이상한 비늘\nLV.100 경험치량 : 1,250,000\n\n키 : 1.8m\t몸무게 : 3.3kg\n\n" + "포획률 : 45\t성비 : 1:1\n부화 걸음수 : 10,240걸음");
//                                check = false;
//                            }

                        }

                        if (check) {
                            Log.i("asdf", "send2server()");
                            send2server();
                        }
                    }
                }
            stopSelf();
        }
    }

    public static void send(String massage) {
        Intent sendIntent = new Intent();
        Bundle msg = new Bundle();
        for (RemoteInput inputable : session.session.getRemoteInputs())
            msg.putCharSequence(inputable.getResultKey(), massage);
        RemoteInput.addResultsToIntent(session.session.getRemoteInputs(), sendIntent, msg);

        try {
            session.session.actionIntent.send(context, 0, sendIntent);
            Log.i("send() complete",massage);
        } catch (PendingIntent.CanceledException e) {
            e.printStackTrace();
        }
    }

    public interface HangangService {
        @GET("/")
        Call<getResult> items();
    }

    public void getHangangTemp() {
        Log.i("NOTI", "한강온도");
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://hangang.dkserver.wo.tc")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        HangangService service = retrofit.create(HangangService.class);

        Call<getResult> items = (Call<getResult>) service.items();
        items.enqueue(new Callback<getResult>() {
            @Override
            public void onResponse(Call<getResult> call, Response<getResult> response) {
                send("현재 한강온도는 " + response.body().getTemp() + "°C 입니다.\nlast update : " + response.body().getTime());
            }

            List<ClipData.Item> list;

            @Override
            public void onFailure(Call<getResult> call, Throwable throwable) {
                send("정보를 불러오는데 실패하였습니다.");
            }
        });
    }

    void send2server() {
        MainActivity.PS_RE apiRequest = MainActivity.PS_RE.retrofit.create(MainActivity.PS_RE.class);
        Call<ResponseBody> call = apiRequest.test(session.message);
        call.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                String responseStr = "";
                JSONObject json;
                try {
                    responseStr = response.body().string();
                    Log.i("TAG", responseStr);
                } catch (IOException e) {
                    e.printStackTrace();
                }

                try {
                    json = new JSONObject(responseStr);
                    if (json.getInt("result") == 1) {
                        send(json.getString("content"));
                    } else {
                        //아무일도 없음(요청보냈는데 딱히 응답할내용없음)
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
            }
        });
    }
}
