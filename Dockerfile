FROM ibmjava:17-sdk-s390x AS builder
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM ibmjava:17-jre-s390x
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]