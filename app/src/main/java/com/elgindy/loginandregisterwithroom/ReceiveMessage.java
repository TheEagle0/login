package com.elgindy.loginandregisterwithroom;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class ReceiveMessage extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
        final Bundle bundle = intent.getExtras();
        try {
            if (bundle != null) {
                final Object[] pdusObj = (Object[]) bundle.get("pdus");

                if (pdusObj != null) {
                    for (Object o : pdusObj) {

                        SmsMessage currentMessage = SmsMessage.createFromPdu((byte[]) o);
                        String senderNum = currentMessage.getDisplayOriginatingAddress();
                        String message = currentMessage.getDisplayMessageBody();
                        Toast.makeText(context, response(message), Toast.LENGTH_SHORT).show();
                    }
                }
            }

        } catch (Exception e) {
            Log.e("SmsReceiver", "Exception smsReceiver" + e);

        }
    }

    private String response(String msg) {
        if (msg.startsWith("Y"))
            return "yummy";
        else if (msg.startsWith("T"))
            return "Table";
        else return "Thank you";
    }
}

