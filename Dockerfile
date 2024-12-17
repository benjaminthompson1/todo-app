FROM s390x/ibmjava:jre as builder
WORKDIR /app
RUN apt-get update && \
    apt-get install -y maven
COPY . .
RUN mvn clean package -DskipTests

FROM s390x/ibmjava:jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]