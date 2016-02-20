[![Build Status](https://travis-ci.org/DigitalReactor-io/report-service.svg?branch=master)](https://travis-ci.org/DigitalReactor-io/report-service)

Адрес для JSON-запросов
---------------
/trend/visits/date/{date}
Дата относительно которой будет создан отчёт.

##Запуск контейнера
docker run -d -p 8090:8090 --name dr_reports direact/wcl:digitalreactor-report.services