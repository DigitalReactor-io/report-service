package io.digitalreactor.analytics.microservices.reports.rest;

import io.digitalreactor.analytics.microservices.reports.external.yandex.MetricsProvider;
import io.digitalreactor.analytics.microservices.reports.services.trend.TrendVerdictService;
import io.digitalreactor.analytics.microservices.reports.services.trend.dto.TrendReport;
import io.digitalreactor.analytics.microservices.reports.services.trend.VisitAnalyzer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping(
        value = "/trend",
        consumes = MediaType.APPLICATION_JSON_UTF8_VALUE,
        produces = MediaType.APPLICATION_JSON_UTF8_VALUE
)
public class TrendReportResource {

    private MetricsProvider metricsProvider;
    private VisitAnalyzer visitAnalyzer;
    private TrendVerdictService trendVerdictService;

    public TrendReportResource() {
        metricsProvider = new MetricsProvider();
        visitAnalyzer = new VisitAnalyzer();
        trendVerdictService = new TrendVerdictService();

    }

    @RequestMapping(value = "/visits/date/{actualDate}", method = RequestMethod.GET)
    public TrendReport reportAboutVisitsTrend(
            @RequestHeader(value = "Access-token") String accessToken,
            @RequestHeader(value = "Counter-id") int counterId,
            @PathVariable(value = "actualDate") String actualDate
    ) {

        LocalDate endDate = LocalDate.parse(actualDate).minusDays(1);
        LocalDate startDate = endDate.minusDays(30);

        final List<Integer> visits = metricsProvider.getVisits(counterId, accessToken, startDate, endDate);
        final VisitAnalyzer.Report report = visitAnalyzer.handle(visits);
        final String verdict = trendVerdictService.getVerdict();

        return new TrendReport(
                report.delta,
                report.percent,
                report.action,
                TrendReport.visitsListWithDay(visits, startDate),
                verdict
        );


    }
}
