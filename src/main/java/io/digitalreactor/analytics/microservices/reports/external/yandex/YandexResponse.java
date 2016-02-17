package io.digitalreactor.analytics.microservices.reports.external.yandex;

import java.util.List;

public class YandexResponse {

    /**
     * see https://tech.yandex.ru/metrika/doc/api2/api_v1/intro-docpage/
     */
    private List<DataElement> data;

    public YandexResponse(List<DataElement> data) {
        this.data = data;
    }

    public List<DataElement> getData() {
        return data;
    }

    public void setData(List<DataElement> data) {
        this.data = data;
    }

    //TODO[St.Maxim] отрефакторить
    public List<Integer> getMetrics() {
        return getData().get(0).getMetrics().get(0);
    }
}
