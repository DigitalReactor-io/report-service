package io.digitalreactor.analytics.microservices.reports.external.yandex;

import java.util.List;

public class DataElement {
    private List<List<Integer>> metrics;

    public DataElement(List<List<Integer>> metrics) {
        this.metrics = metrics;
    }

    public List<List<Integer>> getMetrics() {
        return metrics;
    }

    public void setMetrics(List<List<Integer>> metrics) {
        this.metrics = metrics;
    }
}
