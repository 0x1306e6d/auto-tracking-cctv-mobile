package kr.ac.pusan.walkover.autotrackingcctv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import kr.ac.pusan.walkover.autotrackingcctv.R;
import kr.ac.pusan.walkover.autotrackingcctv.camera_select;
import kr.ac.pusan.walkover.autotrackingcctv.server_connect.LoginResult;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private TextInputEditText mIpAddress;
    private TextInputEditText mPort;
    private Button mConnectButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mIpAddress = (TextInputEditText) findViewById(R.id.login_text_ip_address);
        mPort = (TextInputEditText) findViewById(R.id.login_text_port);
        mConnectButton = (Button) findViewById(R.id.login_button_connect);
        mConnectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onConnectButtonClicked();
            }
        });
    }

    private void onConnectButtonClicked() {
        String ipAddress = mIpAddress.getText().toString();
        String port = mPort.getText().toString(); // TODO: 2017-08-24 parseInt
        Log.d(TAG, "onConnectButtonClicked() called. ipAddress=" + ipAddress + ", port=" + port);

        // TODO: 2017-08-24 Rename LoginResult to ????
        LoginResult loginResult = new LoginResult();
        loginResult.set_ip_address(ipAddress);
        loginResult.set_port_num(port);

        Intent intent = new Intent(this, camera_select.class);
        intent.putExtra("LoginResult", loginResult);
        startActivity(intent);
        finish();
    }
}
