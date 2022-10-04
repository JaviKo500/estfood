FROM openjdk:8-jdk-slim
COPY "./target/estfood-0.0.1-SNAPSHOT.jar" "app.jar"
EXPOSE 8084
ENTRYPOINT ["java","-jar","app.jar"] 