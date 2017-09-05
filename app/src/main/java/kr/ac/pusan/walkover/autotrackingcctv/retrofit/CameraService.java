package kr.ac.pusan.walkover.autotrackingcctv.retrofit;

import java.util.List;

import io.reactivex.Observable;
import kr.ac.pusan.walkover.autotrackingcctv.model.CameraModel;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CameraService {
    @GET("cameras")
    Observable<List<CameraModel>> cameraList();

    @GET("camera/{id}")
    Observable<CameraModel> camera(@Path("id") int cameraId);
}
