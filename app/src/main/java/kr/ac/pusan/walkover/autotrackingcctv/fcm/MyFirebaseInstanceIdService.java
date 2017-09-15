package kr.ac.pusan.walkover.autotrackingcctv.fcm;

import android.content.SharedPreferences;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import java.io.File;
import java.util.List;

import kr.ac.pusan.walkover.autotrackingcctv.retrofit.RetrofitService;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.fcm_Token;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.fcm_token_message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by soeun on 2017-08-17.
 */
public class MyFirebaseInstanceIdService extends FirebaseInstanceIdService {
    private final static String TAG = "MyFirebaseIIdService";

    Retrofit retrofit;
    RetrofitService service;
    SharedPreferences mPref;
    SharedPreferences.Editor mEditor;

    @Override
    public void onTokenRefresh() {
        super.onTokenRefresh();
        String token = FirebaseInstanceId.getInstance().getToken();
        Log.d(TAG, "Refreshed token: " + token);

        mPref = getApplicationContext().getSharedPreferences("Token_Pref",MODE_WORLD_READABLE);
        mEditor = mPref.edit();
        mEditor.putString("newToken", token);
        mEditor.commit();

        mPref = getSharedPreferences("Token_Pref", 0);
        Log.d(TAG, "Shared Preference newToken: " + mPref.getString("newToken", ""));



//        sendRegistrationToServer(token);
    }

//    private void sendRegistrationToServer(String token) {
//        retrofit = new Retrofit.Builder()
//                .baseUrl("http://192.168.123.14:80/") //??
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//        service = retrofit.create(RetrofitService.class);
//
//        fcm_Token fcm_token = new fcm_Token(token);
//
//        Call<List<fcm_token_message>> call = service.get_token_message(fcm_token);
//
//        call.enqueue(new Callback<List<fcm_token_message>>() {
//            @Override
//            public void onResponse(Call<List<fcm_token_message>> call, Response<List<fcm_token_message>> response) {
//
//            }
//
//            @Override
//            public void onFailure(Call<List<fcm_token_message>> call, Throwable t) {
//
//            }
//        });
//    }

}
