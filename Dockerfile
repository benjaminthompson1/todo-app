# Add .dockerignore
FROM s390x/ibmjava:8.0.6.25-sdk AS builder
WORKDIR /app

# Cache Maven dependencies
COPY pom.xml .
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz && \
    tar xzf apache-maven-3.6.3-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.6.3/bin/mvn /usr/local/bin/mvn && \
    rm apache-maven-3.6.3-bin.tar.gz && \
    mvn dependency:go-offline

COPY src/ src/
RUN mvn clean package -DskipTests

FROM s390x/ibmjava:8.0.6.25-jre
WORKDIR /app

RUN addgroup --system spring && adduser --system spring --ingroup spring
USER spring:spring

COPY --from=builder /app/target/*.jar app.jar

EXPOSE 8080
HEALTHCHECK --interval=30s --timeout=3s CMD curl -f http://localhost:8080/actuator/health || exit 1

ENTRYPOINT ["java","-jar","app.jar"]