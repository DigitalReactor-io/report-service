package io.digitalreactor.analytics.microservices.reports.services.trend;

import io.digitalreactor.analytics.microservices.reports.services.trend.dto.Action;
import javafx.util.Pair;

import java.util.List;

public class VisitAnalyzer {

    private final int week = 7;

    public class Report {
        public Action action = Action.INSUFFICIENT_DATA;
        public int delta = 0;
        public int percent = 0;
    }

    public Report handle(List<Integer> visits) {
        final Report report = new Report();

        if (visits.size() >= 2 * week) {
            final int[] deltaAndPercent = deviationFromPreviousWeek(visits);
            report.delta = deltaAndPercent[0];
            report.percent = deltaAndPercent[1];
            report.action = trendChanges(report.delta);
        }

        return report;
    }

    private Action trendChanges(int delta) {

        Action action = Action.DECREASING;

        if (delta == 0) {
            action = Action.UNALTERED;
        } else if (delta > 0) {
            action = Action.INCREASING;
        }

        return action;
    }

    private int[] deviationFromPreviousWeek(List<Integer> visits) {
        int currentWeek = 0;
        int lastWeek = 0;

        //TODO[St.Maxim] отрефакторить, тут потерен контект dayOfWeek и visits.size()-dayOfWeek
        for (int dayOfWeek = 1; dayOfWeek <= 7; dayOfWeek++) {
            currentWeek += visits.get(visits.size() - dayOfWeek);
            lastWeek += visits.get(visits.size() - week - dayOfWeek);
        }

        final int delta = currentWeek - lastWeek;

        int percent = 0;

        if (currentWeek > lastWeek) {
            percent = (int) ((((double) currentWeek / (double) lastWeek) - 1.0) * 100);
        }

        if (currentWeek < lastWeek) {
            percent = (int) ((((double) lastWeek / (double) currentWeek) - 1.0) * 100);
        }

        /*

        if(lastWeek != 0 && currentWeek / lastWeek){

        }

       /* percent = delta < 0 ? currentWeek / lastWeek : lastWeek / currentWeek;*/

        return new int[]{delta, percent};
    }

}
