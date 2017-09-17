package com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot;

import android.util.Log;

import static com.sup3rd3v3l0p3r.teamvetor.zer0ble_kakaotalk_bot.MainActivity.BOT_NAME;


/**
 * Created by dlals on 2017-09-18.
 */

public class RockScissorsPaperService {
    public void getRspResult(String message) {
        int botReslt = (int) (Math.random() * 3);
        Log.i("TAG", "getRspResult : " + botReslt);
        if (message.contains("가위")) {
            if (botReslt == 0) {
                Listener.send(BOT_NAME + " (이)는 가위를 냈다. 결과는 비김!");
            } else if (botReslt == 1)
                Listener.send(BOT_NAME + " (이)는 바위를 냈다. 결과는" + BOT_NAME + "이 이김!");
            else if (botReslt == 2)
                Listener.send(BOT_NAME + " (이)는 보를 냈다. 결과는" + BOT_NAME + "이 짐!");
        } else if (message.contains("바위")) {
            if (botReslt == 0)
                Listener.send(BOT_NAME + " (이)는 가위를 냈다. 결과는" + BOT_NAME + "이 짐!");
            else if (botReslt == 1)
                Listener.send(BOT_NAME + " (이)는 바위를 냈다. 결과는 비김!");
            else if (botReslt == 2)
                Listener.send(BOT_NAME + " (이)는 보를 냈다. 결과는" + BOT_NAME + "이 이김!");
        } else if (message.contains("보") || message.contains("보자기")) {
            if (botReslt == 0)
                Listener.send(BOT_NAME + " (이)는 가위를 냈다. 결과는" + BOT_NAME + "이 이김!");
            else if (botReslt == 1)
                Listener.send(BOT_NAME + " (이)는 바위를 냈다. 결과는" + BOT_NAME + "이 짐!");
            else if (botReslt == 2)
                Listener.send(BOT_NAME + " (이)는 보를 냈다. 결과는 비김!");
        } else
            Listener.send("오류");
    }

}
