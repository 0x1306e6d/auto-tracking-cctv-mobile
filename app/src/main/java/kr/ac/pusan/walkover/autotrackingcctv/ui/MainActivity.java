package kr.ac.pusan.walkover.autotrackingcctv.ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import kr.ac.pusan.walkover.autotrackingcctv.AutoTrackingCCTVConstants;
import kr.ac.pusan.walkover.autotrackingcctv.R;
import kr.ac.pusan.walkover.autotrackingcctv.ui.adapter.CameraListRecyclerAdapter;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mRecycler;
    private RecyclerView.Adapter mRecyclerAdapter;

    private String mIpAddress;
    private int mPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        setupToolbar();
        setupRecycler();

        getExtra();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d(TAG, "onStop() called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy() called");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();

        if (itemId == R.id.main_menu_action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void setupToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);

        if (getSupportActionBar() != null) {
            // TODO: 2017-08-30 configure support action bar
        }
    }

    private void setupRecycler() {
        mRecycler = (RecyclerView) findViewById(R.id.main_recycler);
        mRecycler.setHasFixedSize(true);
        mRecycler.setLayoutManager(new LinearLayoutManager(this));

        mRecyclerAdapter = new CameraListRecyclerAdapter();
        mRecycler.setAdapter(mRecyclerAdapter);
    }

    private void getExtra() {
        mIpAddress = getIntent().getStringExtra(AutoTrackingCCTVConstants.IP_ADDRESS_KEY);
        mPort = getIntent().getIntExtra(AutoTrackingCCTVConstants.PORT_KEY, AutoTrackingCCTVConstants.DEFAULT_PORT);
        Log.d(TAG, "getExtra() called. ipAddress=" + mIpAddress + ", port=" + mPort);
    }
}
