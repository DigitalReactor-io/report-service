package io.digitalreactor.analytics.microservices.reports.external.yandex;

import retrofit.RestAdapter;
import java.time.LocalDate;
import java.util.List;

public class MetricsProvider {

    private final String endpoint = "https://api-metrika.yandex.ru";
    private YandexApi yandexApi;

    public MetricsProvider() {
        RestAdapter retrofit = new RestAdapter.Builder().setEndpoint(endpoint).build();
        yandexApi = retrofit.create(YandexApi.class);
    }

    public List<Integer> getVisits(int counterId, String accessToken, LocalDate startDate, LocalDate endDate) {
        YandexResponse response = yandexApi.get(counterId, accessToken, startDate.toString(), endDate.toString(), "ym:s:visits", "day");

        return response.getMetrics();
    }
}
