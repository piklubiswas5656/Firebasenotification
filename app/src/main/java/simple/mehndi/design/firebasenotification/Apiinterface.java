package simple.mehndi.design.firebasenotification;

import static simple.mehndi.design.firebasenotification.CONSTANTS.CONTENT_TYPE;
import static simple.mehndi.design.firebasenotification.CONSTANTS.SERVER_KEY;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import simple.mehndi.design.firebasenotification.modal.PushNotification;

public interface Apiinterface {

    @Headers({"Authorization:key="+SERVER_KEY,"Content-Type:"+CONTENT_TYPE})
    @POST("fcm/send")
    Call<PushNotification> sendnotification(
            @Body PushNotification notification
    );
}
