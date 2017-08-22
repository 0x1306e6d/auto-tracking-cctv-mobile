package org.androidtown.auto_tracking_cctvapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

import org.androidtown.auto_tracking_cctvapp.server_connect.server_ip_port_camera;

public class MainActivity_login extends AppCompatActivity {

    EditText ip_address_edit_text, port_num_edit_text;
    Button connect_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_server_connect);

        //fcm
        //FirebaseMessaging.getInstance().subscribeToTopic("news");
        //FirebaseMessaging.getInstance().
        FirebaseInstanceId.getInstance().getToken();

        //Setting
        ip_address_edit_text = (EditText)findViewById(R.id.server_ip_address);
        port_num_edit_text = (EditText)findViewById(R.id.server_port_num);
        connect_btn = (Button)findViewById(R.id.server_connect_btn);

        //Button Event
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                server_ip_port_camera server_ip_port_camera = new server_ip_port_camera();
                server_ip_port_camera.set_ip_address(ip_address_edit_text.getText().toString());
                server_ip_port_camera.set_port_num(port_num_edit_text.getText().toString());

                Intent intent = new Intent(
                        getApplicationContext(),
                        camera_select.class);
                intent.putExtra("server_ip_port_camera", server_ip_port_camera);
                startActivity(intent);
            }
        });

    }
}
