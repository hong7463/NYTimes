package com.haisenhong.nytimes.network;

import com.haisenhong.nytimes.common.Constants;
import com.haisenhong.nytimes.data.responses.ArticleSearchResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by hison7463 on 10/22/16.
 */

public interface MyApiEndpointInterface {

    @GET("svc/search/v2/articlesearch.json?")
    Call<ArticleSearchResponse> getArticle(@Query(Constants.apikey) String apikey,
                                           @Query(Constants.query) String query,
                                           @Query(Constants.page) int page,
                                           @Query(Constants.beginDate) String beginDate,
                                           @Query(Constants.sort) String sortOrder,
                                           @Query(Constants.fquery) String fquery);

}
