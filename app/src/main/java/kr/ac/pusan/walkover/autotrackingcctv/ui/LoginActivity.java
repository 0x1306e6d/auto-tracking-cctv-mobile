package kr.ac.pusan.walkover.autotrackingcctv.ui;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import kr.ac.pusan.walkover.autotrackingcctv.AutoTrackingCCTVConstants;
import kr.ac.pusan.walkover.autotrackingcctv.R;

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = LoginActivity.class.getSimpleName();

    private TextInputEditText mIpAddress;
    private Button mConnectButton;
//    SharedPreferences mPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        mPref = getApplicationContext().getSharedPreferences("Token_Pref",MODE_PRIVATE);

        mIpAddress = (TextInputEditText) findViewById(R.id.login_text_ip_address);
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
        if (ipAddress.length() <= 0) {
            Toast.makeText(getApplicationContext(), "PLEASE INPUT IP ADDRESS", Toast.LENGTH_SHORT).show();
        } else {
            Log.d(TAG, "onConnectButtonClicked() called. ipAddress=" + ipAddress );

//            Log.d(TAG, "Shared Preference new Token :"+ mPref.getString("newToken",""));

            Intent intent = new Intent(this, MainActivity.class);
            intent.putExtra(AutoTrackingCCTVConstants.IP_ADDRESS_KEY, ipAddress);
            startActivity(intent);

            finish();
        }
    }
}
