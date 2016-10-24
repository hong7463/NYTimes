package com.haisenhong.nytimes.ui.mvp.main;

import android.content.Context;
import android.transition.Explode;
import android.util.Log;

import com.haisenhong.nytimes.R;
import com.haisenhong.nytimes.data.responses.ArticleSearchResponse;
import com.haisenhong.nytimes.network.MyApiEndpointInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hison7463 on 10/22/16.
 */

public class MainPresenter {

    private final String TAG = MainPresenter.class.getSimpleName();

    private Context context;
    private Retrofit retrofit;

    public MainPresenter(Context context) {
        this.context = context;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.search_url))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public void fetchMoreBooks(int page, String query, String beginDate, String sortOrder, String newsDesk) {
        MyApiEndpointInterface apiService = retrofit.create(MyApiEndpointInterface.class);
        Log.d(TAG, page + " " + query + " " + beginDate + " " + sortOrder + " " + newsDesk);
        Call<ArticleSearchResponse> call = apiService.getArticle(context.getString(R.string.apikey), query, page, beginDate, sortOrder, newsDesk);
        call.enqueue(new Callback<ArticleSearchResponse>() {
            @Override
            public void onResponse(Call<ArticleSearchResponse> call, Response<ArticleSearchResponse> response) {
                Log.d(TAG, response.code() + "");
                Log.d(TAG, response.toString());
                if(response.code() != 200) {
                    call.clone().enqueue(this);
                    return;
                }
                ((MainActivity)context).showResults(response.body());
            }

            @Override
            public void onFailure(Call<ArticleSearchResponse> call, Throwable t) {
                Log.d(TAG, "fail");
            }
        });
    }

}
