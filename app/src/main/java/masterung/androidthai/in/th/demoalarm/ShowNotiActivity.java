package masterung.androidthai.in.th.demoalarm;


import android.app.Notification;
import android.app.NotificationManager;
import android.media.RingtoneManager;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class ShowNotiActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_noti);

        showNotification();

    }

    private void showNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(ShowNotiActivity.this);
        builder.setSmallIcon(R.drawable.ic_action_noti);
        builder.setTicker("Ticker Notification");
        builder.setContentTitle("Title Notification");
        builder.setContentText("Text Notification");
        builder.setWhen(System.currentTimeMillis());
        builder.setAutoCancel(false);

        Uri uri = RingtoneManager.getDefaultUri(Notification.DEFAULT_SOUND);
        builder.setSound(uri);

        android.app.Notification notification = builder.build();
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(1000, notification);

    }
}
