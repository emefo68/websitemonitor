FROM eclipse-temurin:21-jdk

WORKDIR /app

COPY WebsiteMonitor/src /app/src

RUN mkdir -p /app/bin

RUN javac -d /app/bin -cp /app/src $(find /app/src -name "*.java")

ENTRYPOINT ["java", "-cp", "/app/bin", "Main"]