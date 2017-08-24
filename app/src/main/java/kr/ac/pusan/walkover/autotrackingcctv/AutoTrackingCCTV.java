package kr.ac.pusan.walkover.autotrackingcctv;

import android.app.Application;
import android.util.Log;

public class AutoTrackingCCTV extends Application {

    private static final String TAG = AutoTrackingCCTV.class.getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate() called");
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        Log.d(TAG, "onTerminate() called");
    }
}
