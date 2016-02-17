FROM java:8u66-jdk

COPY report.services-0.1.jar /app.jar

CMD java -server -Xmx128m -XX:+UseG1GC -XX:MinHeapFreeRatio=5 -XX:MaxHeapFreeRatio=15  -jar /app.jar

EXPOSE 8090