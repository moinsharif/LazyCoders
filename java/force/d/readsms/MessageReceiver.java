package force.d.readsms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;

public class MessageReceiver extends BroadcastReceiver {

    private static MessageListener mListener;

    public static void bindListener(MessageListener listener) {
        mListener = listener;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        Bundle data = intent.getExtras();
        Object[] pdus = (Object[]) data.get("pdus");
        for (int i = 0; i < pdus.length; i++) {
            SmsMessage smsMessage = SmsMessage.createFromPdu((byte[]) pdus[i]);
            String message = "Sender : " + smsMessage.getDisplayOriginatingAddress()
//                    + "\nEmail From: " + smsMessage.getEmailFrom()
//                    + "\nEmal Body: " + smsMessage.getEmailBody()
                    + "\nDisplay message body: " + smsMessage.getDisplayMessageBody()
                    + "\nTime in millisecond: " + smsMessage.getTimestampMillis()
                    + "\nMessage: " + smsMessage.getMessageBody();
            mListener.messageReceived(message);
        }
    }
}