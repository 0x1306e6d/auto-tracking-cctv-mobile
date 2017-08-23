package kr.ac.pusan.walkover.autotrackingcctv;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import kr.ac.pusan.walkover.autotrackingcctv.server_connect.LoginResult;


/**
 * Created by soeun on 2017-07-23.
 */
public class camera_select extends AppCompatActivity {

    ArrayAdapter camera_list_adapter;
    ListView listview;
    static final String[] camera_list = {"CAMERA 1", "CAMERA 2"};

    LoginResult server_ip_port_camera; //server info object(ip address, port num, camera num)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.camera_select);

        camera_list_adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_single_choice, camera_list);
        listview = (ListView) findViewById(R.id.camera_list_view);
        listview.setAdapter(camera_list_adapter);

        Intent intent = getIntent();
        server_ip_port_camera = (LoginResult) intent.getSerializableExtra("LoginResult");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Integer selected_camera_num = position;
                server_ip_port_camera.set_camera_num(selected_camera_num);

                Intent intent = new Intent(
                        getApplicationContext(),
                        selected_camera_play.class);
                intent.putExtra("LoginResult", server_ip_port_camera);
                startActivity(intent);

            }
        });
    }
}
