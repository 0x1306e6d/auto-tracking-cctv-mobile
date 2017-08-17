package org.androidtown.auto_tracking_cctvapp.fcm;

import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

/**
 * Created by soeun on 2017-08-17.
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService{
    private  final static String TAG = "FCM_ID";

    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);

        sendRegistrationToServer(token);
    }

    private void sendRegistrationToServer(String token) {
//        OkHttpClient client = new OkHttpClient();
//        RequestBody body = new FormBody().Builder()
//                .add("Token", token)
//                .build();
    }


}
