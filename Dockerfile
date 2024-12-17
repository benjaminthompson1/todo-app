# Dockerfile
FROM s390x/ibmjava:jre as builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM s390x/ibmjava:jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]