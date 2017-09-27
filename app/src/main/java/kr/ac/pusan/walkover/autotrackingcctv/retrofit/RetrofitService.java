package kr.ac.pusan.walkover.autotrackingcctv.retrofit;

import java.util.List;

import kr.ac.pusan.walkover.autotrackingcctv.model.ResponseModel;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by soeun on 2017-07-17.
 */

//http://hsue.tistory.com/20

public interface RetrofitService {
    @POST("camera/{id}/move")
    Call<ResponseModel> get_direction_message(@Path("id") long cameraId, @Body Direction direction);

    @POST("camera/{id}/mode")
    Call<ResponseModel> get_mode_message(@Path("id") long cameraId, @Body Mode mode);

    @POST("token")
    Call<ResponseModel> get_token_message(@Body fcm_Token token);


//    @POST("token")
//    Call<List<token_message>> get_token_message(@Body Mode mode);
    /*
    @GET("move/{direction}")
    Call<List<direction_message>> get_direction_message(
            @Path("direction") String direction
    );
    */
/*
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .build();
*/
}

