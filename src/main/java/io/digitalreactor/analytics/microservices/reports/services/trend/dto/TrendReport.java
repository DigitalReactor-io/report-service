package io.digitalreactor.analytics.microservices.reports.services.trend.dto;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TrendReport {

    private int visit;
    private int percent;
    private Action action;
    private List<Visit> metrics;
    private String reason;

    public TrendReport(int visit, int percent, Action action, List<Visit> metrics, String reason) {
        this.visit = visit;
        this.percent = percent;
        this.action = action;
        this.metrics = metrics;
        this.reason = reason;
    }

    public int getVisit() {
        return visit;
    }

    public double getPercent() {
        return percent;
    }

    public Action getAction() {
        return action;
    }

    public List<Visit> getMetrics() {
        return metrics;
    }

    public String getReason() {
        return reason;
    }

    public static List<Visit> visitsListWithDay(List<Integer> visits, LocalDate startTime) {

        List<Visit> result = new ArrayList<Visit>();
        LocalDate pointerDate = startTime;

        for (int visit : visits) {
            result.add(new Visit(
                    visit,
                    pointerDate.toString(),
                    isHoliday(pointerDate) ? Visit.DayType.HOLIDAY : Visit.DayType.WEEKDAY
            ));
            pointerDate = pointerDate.plusDays(1);
        }

        return result;
    }

    private static boolean isHoliday(LocalDate date) {
        return date.getDayOfWeek() == DayOfWeek.SATURDAY || date.getDayOfWeek() == DayOfWeek.SUNDAY;
    }
}
