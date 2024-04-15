# Use an official OpenJDK 17 image as a parent image
FROM openjdk:17-oracle

# Set the working directory in the container
WORKDIR /app

# Copy any jar file from the target directory into the container at /app
# Here, the jar is renamed to UtilisateursService.jar for clarity
COPY target/*.jar /app/RevenueMicroservice-0.0.1-SNAPSHOT.jar

# Make port 4050 available to the world outside this container
EXPOSE 4047

# Run the jar file, specifying the renamed jar
ENTRYPOINT ["java", "-jar", "/app/RevenueMicroservice-0.0.1-SNAPSHOT.jar"]