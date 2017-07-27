package org.androidtown.auto_tracking_cctvapp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import org.androidtown.auto_tracking_cctvapp.retrofit.Direction;
import org.androidtown.auto_tracking_cctvapp.retrofit.RetrofitService;
import org.androidtown.auto_tracking_cctvapp.retrofit.direction_message;
import org.androidtown.auto_tracking_cctvapp.server_connect.server_ip_port_camera;

import java.io.ByteArrayOutputStream;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by soeun on 2017-07-17.
 */

public class send_command_to_web_server extends Activity {

    ImageView image_view;
    ImageButton up_btn, left_btn, right_btn, down_btn;

    server_ip_port_camera server_ip_port_camera; //server info object(ip address, port num)
    String ip_address, port_num;
    Integer camera_num; //selected camera number

    direction_message directionmessageMessage; //retrofit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_camera_play);

        //get server ip_address, port num
        Intent intent = getIntent();
        server_ip_port_camera = (server_ip_port_camera)intent.getSerializableExtra("server_ip_port_camera");
        ip_address = server_ip_port_camera.get_ip_address();
        port_num = server_ip_port_camera.get_port_num();
        camera_num = server_ip_port_camera.get_camera_num();

        //Setting
        image_view = (ImageView) findViewById(R.id.ImageView);

        up_btn = (ImageButton) findViewById(R.id.up_button);
        left_btn = (ImageButton) findViewById(R.id.left_button);
        right_btn = (ImageButton) findViewById(R.id.right_button);
        down_btn = (ImageButton) findViewById(R.id.down_button);


        //test for encoding image to base64 string
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ryan);
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] imageBytes1 = baos.toByteArray();
        String imageString = Base64.encodeToString(imageBytes1, Base64.DEFAULT);

        //test for decoding base64 string to image
        byte[] imageBytes2 = Base64.decode(imageString, Base64.DEFAULT);
        Bitmap decodedImage = BitmapFactory.decodeByteArray(imageBytes2, 0, imageBytes2.length);
        image_view.setImageBitmap(decodedImage);


        //ImageButton Event
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("UP");  //서버에 "up" 보내기
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("LEFT");  //서버에 "left" 보내기
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("RIGHT");  //서버에 "right" 보내기
            }
        });

        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_command("DOWN");  //서버에 "down" 보내기
            }
        });
    }

    //retrofit http
    public void send_command(String direction) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://" + ip_address + ":" + port_num +"/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        RetrofitService service = retrofit.create(RetrofitService.class);

        Direction dir = new Direction(direction);
        Call<List<direction_message>> call = service.get_direction_message(dir);

        call.enqueue(new Callback<List<direction_message>>() {
            @Override
            public void onResponse(Call<List<direction_message>> call, Response<List<direction_message>> response) {

            }

            @Override
            public void onFailure(Call<List<direction_message>> call, Throwable t) {

            }
        });

        //Log.d("SEND", "send_command() returned: " + call.request());
        //Log.d("EXCUTED", "send_command() returned: " + call.isExecuted());
        //Log.d("CANCELED", "send_command() returned: " + call.isCanceled());
        //Log.i("Sended Message", direction);
    }
}
