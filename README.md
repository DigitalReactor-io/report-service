DigitalReactor.io/report
=========
**Latest production deployment**
[![Build Status](https://travis-ci.org/DigitalReactor-io/report-service.svg?branch=master)](https://travis-ci.org/DigitalReactor-io/report-service)

## JSON-requests
### Trend Report
The trend report provides information about changes visits during one month.
The report contains:
* visits during 30 days;
* value of changed conversion during last week;
* trend change reason.

**URL for request** : /trend/visits/date/{actualDate}

***Request***  
For request you required to have two credentials - yandex access token and counter id. Theese credentials you must put in headers with content-type.
```headers
Access-token: (string)
Counter-id: (int)
Content-Type: application/json;charset=UTF-8
```
***Response***

```json
{
  "visit" : -46,
  "percent" : 10.0,
  "action" : "DECREASING | INCREASING | UNALTERED | INSUFFICIENT_DATA",
  "metrics" : [ {
    "number" : 60,
    "date" : "2015-10-30",
    "dayType" : "WEEKDAY"
  }, {
    "number" : 26,
    "date" : "2015-10-31",
    "dayType" : "HOLIDAY"
  }, ... ],
  "reason" : "На посещаемость может влиять сезонность, отключение рекламного канала или снижение видимости  сайта в поисковой выдачи."
}

```

## How to run
docker run -d -p 8090:8090 --name dr_reports direact/wcl:digitalreactor-report.services
