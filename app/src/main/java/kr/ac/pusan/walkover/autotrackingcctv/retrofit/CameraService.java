package kr.ac.pusan.walkover.autotrackingcctv.retrofit;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CameraService {
    @GET("cameras")
    Observable<List<CameraResponse>> cameraList();

    @GET("camera/{id}")
    Observable<CameraResponse> camera(@Path("id") int cameraId);
}
