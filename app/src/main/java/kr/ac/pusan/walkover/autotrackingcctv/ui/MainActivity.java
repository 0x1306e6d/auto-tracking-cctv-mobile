package kr.ac.pusan.walkover.autotrackingcctv.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.List;

import io.reactivex.functions.Consumer;
import kr.ac.pusan.walkover.autotrackingcctv.AutoTrackingCCTVConstants;
import kr.ac.pusan.walkover.autotrackingcctv.R;
import kr.ac.pusan.walkover.autotrackingcctv.model.CameraModel;
import kr.ac.pusan.walkover.autotrackingcctv.retrofit.CameraService;
import kr.ac.pusan.walkover.autotrackingcctv.selected_camera_play;
import kr.ac.pusan.walkover.autotrackingcctv.ui.adapter.CameraListRecyclerAdapter;
import kr.ac.pusan.walkover.autotrackingcctv.ui.adapter.OnCameraCardClickedListener;
import kr.ac.pusan.walkover.autotrackingcctv.ui.adapter.OnCameraCardLongClickedListener;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private RecyclerView mCameraListRecycler;
    private CameraListRecyclerAdapter mCameraListRecyclerAdapter;

    private String mIpAddress;
    private int mPort;

    private Retrofit mRetrofit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate() called with: savedInstanceState = [" + savedInstanceState + "]");

        mIpAddress = getIntent().getStringExtra(AutoTrackingCCTVConstants.IP_ADDRESS_KEY);
        mPort = getIntent().getIntExtra(AutoTrackingCCTVConstants.HTTP_PORT_KEY, AutoTrackingCCTVConstants.HTTP_PORT);
        Log.d(TAG, "ipAddress = [" + mIpAddress + "], port = [" + mPort + "]");

        setupToolbar();
        setupRecycler();
        setupRetrofit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d(TAG, "onStart() called");

        loadCameraListFromGateway();
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
        mCameraListRecycler = (RecyclerView) findViewById(R.id.main_recycler);
        mCameraListRecycler.setHasFixedSize(true);
        mCameraListRecycler.setLayoutManager(new LinearLayoutManager(this));

        mCameraListRecyclerAdapter = new CameraListRecyclerAdapter(
                new OnCameraCardClickedListener() {
                    @Override
                    public void onClicked(View view, long cameraId) {
                        onCameraCardClicked(view, cameraId);
                    }
                },
                new OnCameraCardLongClickedListener() {
                    @Override
                    public boolean onLongClicked(View view, long cameraId) {
                        return onCameraCardLongClicked(view, cameraId);
                    }
                }
        );
        mCameraListRecycler.setAdapter(mCameraListRecyclerAdapter);
    }

    private void setupRetrofit() {
        String baseUrl = "http://" + mIpAddress + ":" + mPort + "/";
        mRetrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private void loadCameraListFromGateway() {
        CameraService cameraService = mRetrofit.create(CameraService.class);
        cameraService.cameraList()
                .subscribe(new Consumer<List<CameraModel>>() {
                    @Override
                    public void accept(List<CameraModel> cameraModels) throws Exception {
                        mCameraListRecyclerAdapter.changeDataSet(cameraModels);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                mCameraListRecyclerAdapter.notifyDataSetChanged();
                            }
                        });
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "Failed to load camera list from gateway.", throwable);
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                Snackbar.make(mCameraListRecycler, "Loading camera list is failed.", Snackbar.LENGTH_SHORT)
                                        .show();
                            }
                        });
                    }
                });
    }

    private void onCameraCardClicked(View view, long cameraId) {
        Intent intent = new Intent(this, selected_camera_play.class);
        intent.putExtra(AutoTrackingCCTVConstants.IP_ADDRESS_KEY, mIpAddress);
        intent.putExtra(AutoTrackingCCTVConstants.HTTP_PORT_KEY, mPort);
        intent.putExtra(AutoTrackingCCTVConstants.CAMERA_ID_KEY, cameraId);
        startActivity(intent);
    }

    private boolean onCameraCardLongClicked(View view, long cameraId) {
        return true;
    }
}
