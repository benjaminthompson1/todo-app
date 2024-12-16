FROM s390x/openjdk:17 AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM s390x/openjdk:17
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]