package org.androidtown.auto_tracking_cctvapp.retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created by soeun on 2017-07-17.
 */

//http://hsue.tistory.com/20

public interface RetrofitService {
    @GET("move/{direction}")
    Call<List<direction_message>> get_direction_message(
            @Path("direction") String direction
    );
/*
    public static final Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://localhost:3000/")
            .build();
*/
}

