package io.digitalreactor.analytics.microservices.reports.external.yandex;

import retrofit.http.GET;
import retrofit.http.Query;

public interface YandexApi {
    @GET("/stat/v1/data/bytime")
    YandexResponse get(
            @Query("id") int counterId,
            @Query("oauth_token") String token,
            @Query("date1") String startDate,
            @Query("date2") String endDate,
            @Query("metrics") String metrics,
            @Query("group") String group
    );
}
