package io.digitalreactor.analytics.microservices.reports.services.trend;

import io.digitalreactor.analytics.microservices.reports.services.trend.dto.Action;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class VisitAnalyzerTest {

    private VisitAnalyzer visitAnalyzer;
    private final int twoWeek = 14;

    @Before
    public void setUp() throws Exception {
        visitAnalyzer = new VisitAnalyzer();
    }

    @Test
    public void handle_emptyListWithVisits_ReportWithActionInsufficientData() {
        List<Integer> emptyList = new ArrayList<Integer>();

        final VisitAnalyzer.Report report = visitAnalyzer.handle(emptyList);

        assertThat(report.action, is(equalTo(Action.INSUFFICIENT_DATA)));
    }

    @Test
    public void handle_visitsTwoWeekWithIncreasingTrend_ReportWithActionIncreasing() {
        List<Integer> increasingTrend = increasingVisits();

        final VisitAnalyzer.Report report = visitAnalyzer.handle(increasingTrend);

        assertThat(report.action, is(equalTo(Action.INCREASING)));
    }

    @Test
    public void handle_visitsTwoWeekWithIncreasingTrend_ReportWithDelta() {
        List<Integer> increasingTrend = increasingVisits();

        final VisitAnalyzer.Report report = visitAnalyzer.handle(increasingTrend);

        assertThat(report.delta, is(equalTo(49)));
        assertThat(report.percent, is(equalTo(233)));
    }

    @Test
    public void handle_visitsTwoWeekWithDecreasingTrend_ReportWithActionDecreasing() {
        List<Integer> decreasingTrend = decreasingVisits();

        final VisitAnalyzer.Report report = visitAnalyzer.handle(decreasingTrend);

        assertThat(report.action, is(equalTo(Action.DECREASING)));
    }

    @Test
    public void handle_visitsTwoWeekWithDecreasingTrend_ReportWithActionDelta() {
        List<Integer> decreasingTrend = decreasingVisits();

        final VisitAnalyzer.Report report = visitAnalyzer.handle(decreasingTrend);

        assertThat(report.delta, is(equalTo(-49)));
        assertThat(report.percent, is(equalTo(175)));
    }

    @Test
    public void handle_visitsTwoWeekWithUnaUnalteredTrend_ReportWithActionUnaltered() {
        List<Integer> unalteredTrend = unalteredVisits();

        final VisitAnalyzer.Report report = visitAnalyzer.handle(unalteredTrend);

        assertThat(report.action, is(equalTo(Action.UNALTERED)));
    }

    @Test
    public void handle_visitsTwoWeekWithUnaUnalteredTrend_ReportWithDelta() {
        List<Integer> unalteredTrend = unalteredVisits();

        final VisitAnalyzer.Report report = visitAnalyzer.handle(unalteredTrend);

        assertThat(report.delta, is(equalTo(0)));
        assertThat(report.percent, is(equalTo(0)));
    }

    private List<Integer> decreasingVisits() {
        List<Integer> decreasingTrend = new ArrayList<Integer>();
        for (int i = 0; i < twoWeek; i++) {
            decreasingTrend.add(twoWeek - i);
        }
        return decreasingTrend;
    }

    private List<Integer> unalteredVisits() {
        List<Integer> unalteredTrend = new ArrayList<Integer>();
        for (int i = 0; i < twoWeek; i++) {
            unalteredTrend.add(0);
        }
        return unalteredTrend;
    }

    private List<Integer> increasingVisits() {
        List<Integer> increasingTrend = new ArrayList<Integer>();
        for (int i = 0; i < twoWeek; i++) {
            increasingTrend.add(i);
        }
        return increasingTrend;
    }

}