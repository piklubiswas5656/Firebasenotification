package simple.mehndi.design.firebasenotification;

import static simple.mehndi.design.firebasenotification.CONSTANTS.TOPIC;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import simple.mehndi.design.firebasenotification.modal.NotificationData;
import simple.mehndi.design.firebasenotification.modal.PushNotification;

public class MainActivity extends AppCompatActivity {
private EditText title,message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        title=(EditText) findViewById(R.id.text1);
        message=(EditText) findViewById(R.id.text23);
        FirebaseMessaging.getInstance().subscribeToTopic(TOPIC);

    }

    public void send(View view) {
        String titleText =title.getText().toString();
        String messageText=message.getText().toString();
        if(!titleText.isEmpty() && !messageText.isEmpty()){
            PushNotification notification=new PushNotification(new NotificationData(titleText,messageText),TOPIC);

            sendNotification(notification);
        }
    }

    private void sendNotification(PushNotification notification) {

      Call<PushNotification> call=ApiUtility.getclint().sendnotification(
                notification
        );
      call.enqueue(new Callback<PushNotification>() {
          @Override
          public void onResponse(Call<PushNotification> call, Response<PushNotification> response) {
              if(response.isSuccessful()){
                  Toast.makeText(MainActivity.this,"SUCCESSSFUL",Toast.LENGTH_SHORT);
              }else {

              }

          }

          @Override
          public void onFailure(Call<PushNotification> call, Throwable t) {
              Toast.makeText(MainActivity.this,t.getMessage(),Toast.LENGTH_SHORT);
          }
      });
    }


}