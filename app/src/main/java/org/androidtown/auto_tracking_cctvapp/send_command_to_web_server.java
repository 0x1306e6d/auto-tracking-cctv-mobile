package org.androidtown.auto_tracking_cctvapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;

import org.androidtown.auto_tracking_cctvapp.retrofit.RetrofitService;
import org.androidtown.auto_tracking_cctvapp.retrofit.direction_message;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by soeun on 2017-07-17.
 */

public class send_command_to_web_server extends Activity {

    ImageButton up_btn, left_btn, right_btn, down_btn;

    direction_message DirectionMessage; //retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_camera_play);

        //ImageButton Setting
        up_btn = (ImageButton) findViewById(R.id.up_button);
        left_btn = (ImageButton) findViewById(R.id.left_button);
        right_btn = (ImageButton) findViewById(R.id.right_button);
        down_btn = (ImageButton) findViewById(R.id.down_button);

        //ImageButton Event
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("up");  //서버에 "up" 보내기
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("left");  //서버에 "left" 보내기
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("right");  //서버에 "right" 보내기
            }
        });

        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("down");  //서버에 "down" 보내기
            }
        });
    }

    public void send_command(String direction) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://localhost:3000/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);

        Call<List<direction_message>> call = service.get_direction_message(direction);
        /*
        call.enqueue(new Callback<org.androidtown.auto_tracking_cctvapp.retrofit.direction_message>() {
            @Override
            public void onResponse(Call<direction_message> call, Response<direction_message> response) {
                if(response.isSuccessful()) {
                    DirectionMessage = response.body();
                }
            }

            @Override
            public void onFailure(Call<direction_message> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
        */
        Log.i("Sended Message", direction);
    }
}
