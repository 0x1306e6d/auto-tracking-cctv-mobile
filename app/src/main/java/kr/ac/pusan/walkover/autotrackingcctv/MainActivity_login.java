package kr.ac.pusan.walkover.autotrackingcctv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.iid.FirebaseInstanceId;

import kr.ac.pusan.walkover.autotrackingcctv.server_connect.LoginResult;

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
        ip_address_edit_text = (EditText) findViewById(R.id.server_ip_address);
        port_num_edit_text = (EditText) findViewById(R.id.server_port_num);
        connect_btn = (Button) findViewById(R.id.server_connect_btn);

        //Button Event
        connect_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginResult LoginResult = new LoginResult();
                LoginResult.set_ip_address(ip_address_edit_text.getText().toString());
                LoginResult.set_port_num(port_num_edit_text.getText().toString());

                Intent intent = new Intent(
                        getApplicationContext(),
                        camera_select.class);
                intent.putExtra("LoginResult", LoginResult);
                startActivity(intent);
            }
        });

    }
}
