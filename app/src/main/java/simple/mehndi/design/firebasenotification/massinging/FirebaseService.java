package simple.mehndi.design.firebasenotification.massinging;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;

import java.util.Random;

import simple.mehndi.design.firebasenotification.MainActivity;
import simple.mehndi.design.firebasenotification.R;

public class FirebaseService extends FirebaseMessagingService {
    private final String CHANNEL_ID = "channel_id";

    @Override
    public void onMessageReceived(@NonNull RemoteMessage remoteMessage) {
        Intent intent = new Intent(this, MainActivity.class);
        NotificationManager manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        int notificationId = new Random().nextInt();
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            createNotificationChanner(manager);
        }
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        PendingIntent intent1 = PendingIntent.getActivities(this, 0, new Intent[]{intent}, PendingIntent.FLAG_ONE_SHOT);
        Notification notification = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            notification = new Notification.Builder(this, CHANNEL_ID)
                    .setContentTitle(remoteMessage.getData().get("title"))

                    .setContentText(remoteMessage.getData().get("message"))
                    .setContentIntent(intent1)
                    .setAutoCancel(true)
                    .setSmallIcon(R.drawable.ic_launcher_foreground)
                    .build();
        }
        manager.notify(notificationId, notification);

    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChanner(NotificationManager manager) {

        NotificationChannel channel = new NotificationChannel(CHANNEL_ID, "channelname", NotificationManager.IMPORTANCE_HIGH);
        channel.setDescription("MY Description");
        channel.enableLights(true);
        channel.setLightColor(android.R.color.white);
        manager.createNotificationChannel(channel);



    }
}
