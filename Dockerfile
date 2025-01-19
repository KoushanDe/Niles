# Use Amazon Corretto 21 as the base image
FROM amazoncorretto:21

# Set the working directory in the container
WORKDIR /app

# Add the application's JAR file to the container
# Copy target/*.jar to the container
ARG JAR_FILE=target/niles-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} niles.jar

# Expose the port your Spring Boot app uses (default is 8080)
EXPOSE 8080

# Set the startup command to run the JAR file
ENTRYPOINT ["java", "-jar", "niles.jar"]