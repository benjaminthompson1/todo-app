FROM s390x/ibmjava:8-sdk as builder
WORKDIR /app

# Install Maven
RUN apt-get update && \
    apt-get install -y wget && \
    wget https://archive.apache.org/dist/maven/maven-3/3.6.3/binaries/apache-maven-3.6.3-bin.tar.gz && \
    tar xzf apache-maven-3.6.3-bin.tar.gz -C /opt && \
    ln -s /opt/apache-maven-3.6.3/bin/mvn /usr/local/bin/mvn && \
    rm apache-maven-3.6.3-bin.tar.gz

COPY . .
RUN mvn clean package -DskipTests

FROM s390x/ibmjava:8-jre
WORKDIR /app
COPY --from=builder /app/target/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","app.jar"]