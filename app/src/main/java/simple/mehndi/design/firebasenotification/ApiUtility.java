package simple.mehndi.design.firebasenotification;

import static simple.mehndi.design.firebasenotification.CONSTANTS.BASE_URL;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtility {
   private static Retrofit retrofit = null;

    public static Apiinterface getclint() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }

        return retrofit.create(Apiinterface.class);
    }
}
