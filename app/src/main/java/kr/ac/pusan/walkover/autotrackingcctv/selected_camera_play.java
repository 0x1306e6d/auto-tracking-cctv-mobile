package kr.ac.pusan.walkover.autotrackingcctv;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.List;

import kr.ac.pusan.walkover.autotrackingcctv.model.ResponseModel;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.Direction;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.Mode;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.RetrofitService;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.direction_message;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.fcm_Token;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.fcm_token_message;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.mode_message;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by soeun on 2017-07-17.
 */

public class selected_camera_play extends Activity { //implements SurfaceHolder.Callback {

    private static final String TAG = selected_camera_play.class.getSimpleName();

//    SharedPreferences mPref;
//    SharedPreferences.Editor mEditor;
//    String new_token, old_token;

    String str_recv;

    private Socket client_socket;
    private BufferedReader in;
    private OutputStreamWriter out;
    //private DataInputStream mInput;

    Switch mode_switch;
    ImageButton up_btn, left_btn, right_btn, down_btn;

    Retrofit retrofit;
    RetrofitService service;

    private ImageView mFrameImage;

    private String mIpAddress;
    private int mPort;
    private int tcpPort;
    private long mCameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.selected_camera_play);

        mFrameImage = findViewById(R.id.player_frame_image);

        //execute
        direction_button_setting(); //when push direction image_buttons

        mIpAddress = getIntent().getStringExtra(AutoTrackingCCTVConstants.IP_ADDRESS_KEY);
        mPort = getIntent().getIntExtra(AutoTrackingCCTVConstants.HTTP_PORT_KEY, AutoTrackingCCTVConstants.HTTP_PORT);
        tcpPort = getIntent().getIntExtra(AutoTrackingCCTVConstants.TCP_PORT_KEY, AutoTrackingCCTVConstants.TCP_PORT);
        mCameraId = getIntent().getLongExtra(AutoTrackingCCTVConstants.CAMERA_ID_KEY, -1);
        if (mCameraId == -1) {
            finish();
        }
        retrofit = new Retrofit.Builder()
                .baseUrl("http://" + mIpAddress + ":" + mPort + "/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(RetrofitService.class);


        mode_switch = (Switch) findViewById(R.id.mode_switch);
        mode_switch.setOnCheckedChangeListener(new Switch.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    Toast.makeText(getApplicationContext(), "자동모드", Toast.LENGTH_SHORT).show();
                    ImageButton_setEnable(false);
                    send_mode_command("AUTO");
                } else {
                    Toast.makeText(getApplicationContext(), "수동모드", Toast.LENGTH_SHORT).show();
                    ImageButton_setEnable(true);
                    send_mode_command("MANUAL");
                }
            }
        });



        Thread image_receive = new Thread() {
            public void run() {
                try {
                    client_socket = new Socket(mIpAddress, tcpPort);

                    in = new BufferedReader(new InputStreamReader(client_socket.getInputStream()));

                    ByteBuffer buffer = ByteBuffer.allocate(8);
                    buffer.order(ByteOrder.BIG_ENDIAN);
                    buffer.putLong(mCameraId);
                    client_socket.getOutputStream().write(buffer.array());
                    client_socket.getOutputStream().flush();

                    while (true) {
                        str_recv = in.readLine();  //(\n앞에까지 읽어서 return)
                        Log.d("CMA", "sended string: " + str_recv.length());

                        //decoding base64 string to image
                        byte[] image_bytes = Base64.decode(str_recv, Base64.DEFAULT);
                        final Bitmap decodedImage = BitmapFactory.decodeByteArray(image_bytes, 0, image_bytes.length);

                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mFrameImage.setImageBitmap(decodedImage);
                            }
                        });
                    }
                } catch (Exception e) {
                    Log.e("CAE", "run: Image Receive Error", e);
                }
            }
        };
        image_receive.start();


    }

    @Override
    protected void onStop() {
        super.onStop();
        try {
            Log.d("CMA", "Log : onStoppted");
            client_socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    //image_buttons_setting (...event)
    public void direction_button_setting() {
        //Setting
        up_btn = (ImageButton) findViewById(R.id.up_button);
        left_btn = (ImageButton) findViewById(R.id.left_button);
        right_btn = (ImageButton) findViewById(R.id.right_button);
        down_btn = (ImageButton) findViewById(R.id.down_button);

        ImageButton_setEnable(false); //처음 시작할떄 자동모드 : unable

        //ImageButton Event
        up_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_direction_command(1);  //서버에 "up" 보내기
            }
        });

        left_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_direction_command(4);  //서버에 "left" 보내기
            }
        });

        right_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_direction_command(8);  //서버에 "right" 보내기
            }
        });

        down_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                send_direction_command(2);  //서버에 "down" 보내기
            }
        });
    }

    //according to mode
    public void ImageButton_setEnable(boolean TF) {
        up_btn.setEnabled(TF);
        left_btn.setEnabled(TF);
        right_btn.setEnabled(TF);
        down_btn.setEnabled(TF);
    }

    //retrofit http (direction)
    public void send_direction_command(int direction) {
        Direction dir = new Direction(mCameraId, direction);

        Call<ResponseModel> call = service.get_direction_message(mCameraId, dir);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.d(TAG, "direction() called with: call = [" + call + "], response = [" + response + "]");
                Log.d(TAG, "direction(): " + response.body());
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
        //Log.d("SEND", "send_direction_command() returned: " + call.request());
        //Log.d("EXCUTED", "send_direction_command() returned: " + call.isExecuted());
        //Log.d("CANCELED", "send_direction_command() returned: " + call.isCanceled());
        //Log.i("Sended Message", direction);
    }

    //retrofit http (mode)
    public void send_mode_command(String mode) {
        Mode md = new Mode(mCameraId, mode);

        Call<ResponseModel> call = service.get_mode_message(mCameraId, md);

        call.enqueue(new Callback<ResponseModel>() {
            @Override
            public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                Log.d(TAG, "mode() called with: call = [" + call + "], response = [" + response + "]");
            }

            @Override
            public void onFailure(Call<ResponseModel> call, Throwable t) {
                Log.d(TAG, "onFailure() called with: call = [" + call + "], t = [" + t + "]");
            }
        });
    }

    }
