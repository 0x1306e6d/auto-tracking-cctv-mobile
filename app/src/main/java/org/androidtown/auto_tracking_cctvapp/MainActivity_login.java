package org.androidtown.auto_tracking_cctvapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity_login extends AppCompatActivity {

    Button connect_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_server_connect);

        //Button Setting
        connect_btn = (Button)findViewById(R.id.server_connect_btn);

        //Button Event
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(
                        getApplicationContext(),
                        send_command_to_web_server.class);
                startActivity(intent);
            }
        });

    }
}
