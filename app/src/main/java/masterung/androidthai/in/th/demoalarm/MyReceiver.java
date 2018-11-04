package masterung.androidthai.in.th.demoalarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class MyReceiver extends BroadcastReceiver {


    @Override
    public void onReceive(Context context, Intent intent) {
        String messageString = intent.getStringExtra("Message");
        Intent intent1 = new Intent(context, ShowNotiActivity.class);
        intent1.putExtra("Message", messageString);
        intent1.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(intent1);

    }
}   // Main Class
